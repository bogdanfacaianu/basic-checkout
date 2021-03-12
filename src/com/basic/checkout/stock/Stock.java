package com.basic.checkout.stock;

import com.basic.checkout.checkout.ScannedItem;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public final class Stock {

    private static Stock instance;
    private final Map<String, StockItem> stock;

    private Stock() {
        stock = new HashMap<>();
    }

    public static Stock initialise() {
        return instance == null ? new Stock() : instance;
    }

    public void loadBulkStock(Collection<StockItem> stock) {
        stock.forEach(this::addSku);
    }

    public void addSku(StockItem sku) {
        this.stock.put(sku.getSkuId(), sku);
    }

    public void addStockOffer(String skuId, Offer offer) {
        if (stock.containsKey(skuId)) {
            stock.get(skuId).applyOffer(offer);
        }
    }

    public boolean isStockEmpty() {
        return stock.isEmpty();
    }

    public Optional<Offer> getOfferForSku(String skuId) {
        if (this.stock.containsKey(skuId)) {
            return Optional.ofNullable(stock.get(skuId).getOffer());
        }
        return Optional.empty();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock1 = (Stock) o;
        return Objects.equals(stock, stock1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock);
    }

    public Optional<ScannedItem> decorateStockItem(String scannedSku, ScannedItem previousScan) {
        if (stock.containsKey(scannedSku)) {
            StockItem stockItem = this.stock.get(scannedSku);
            return buildUpdatedScannedItem(stockItem, previousScan);
        }
        return Optional.empty();
    }

    private Optional<ScannedItem> buildUpdatedScannedItem(StockItem stockItem, ScannedItem previousScan) {
        Offer stockOffer = stockItem.getOffer();
        ScannedItem scannedItem = new ScannedItem(stockItem.getSkuId(), stockItem.getPrice());
        if (previousScan != null) {
            scannedItem.setQuantity(previousScan.getQuantity() + 1);
            updateTotalCost(scannedItem, stockOffer);
        } else {
            scannedItem.setQuantity(1);
            scannedItem.setTotalCost(stockItem.getPrice());
        }
        return Optional.of(scannedItem);
    }

    private void updateTotalCost(ScannedItem scannedItem, Offer itemOffer) {
        if (itemOffer != null) {
            if (scannedItem.getQuantity() < itemOffer.getMultiplier()) {
                double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
                scannedItem.setTotalCost(totalCost);
            } else {
                applyTotalWithDiscount(scannedItem, itemOffer);
            }
        } else {
            double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
            scannedItem.setTotalCost(totalCost);
        }
    }

    private void applyTotalWithDiscount(ScannedItem scannedItem, Offer itemOffer) {
        double totalCost = ((scannedItem.getQuantity() / itemOffer.getMultiplier()) * itemOffer.getOfferPrice())
            + ((scannedItem.getQuantity() % itemOffer.getMultiplier()) * scannedItem.getPrice());

        scannedItem.setTotalCost(totalCost);
        scannedItem.setDiscounted(true);
    }
}