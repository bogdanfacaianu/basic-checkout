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
        stock.put(sku.getSkuId(), sku);
    }

    public Optional<StockItem> findItem(String skuId) {
        return Optional.ofNullable(stock.get(skuId));
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
        return Optional.ofNullable(stock.get(skuId).getOffer());
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
        Optional<StockItem> maybeStockItem = Optional.ofNullable(stock.get(scannedSku));
        if (maybeStockItem.isPresent()) {
            StockItem stockItem = maybeStockItem.get();
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
            applyTotalWithDiscountIfApplicable(scannedItem, itemOffer);
        } else {
            double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
            scannedItem.setTotalCost(totalCost);
        }
    }

    private void applyTotalWithDiscountIfApplicable(ScannedItem scannedItem, Offer itemOffer) {
        if (scannedItem.getQuantity() < itemOffer.getMultiplier()) {
            double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
            scannedItem.setTotalCost(totalCost);
        } else {
            scannedItem.setTotalCost(computeTotalCost(scannedItem, itemOffer));
            scannedItem.setDiscounted(true);
        }
    }

    private double computeTotalCost(ScannedItem scannedItem, Offer itemOffer) {
        double offerCost = ((double) (scannedItem.getQuantity() / itemOffer.getMultiplier())) * itemOffer.getOfferPrice();
        double nonOfferCost = (scannedItem.getQuantity() % itemOffer.getMultiplier()) * scannedItem.getPrice();
        return offerCost + nonOfferCost;
    }
}