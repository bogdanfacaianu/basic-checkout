package com.basic.checkout.stock;

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

    public void addSku(StockItem stockItem) {
        stock.put(stockItem.getSkuId(), stockItem);
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
        return findItem(skuId).map(StockItem::getOffer);
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
}