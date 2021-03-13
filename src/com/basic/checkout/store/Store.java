package com.basic.checkout.store;

import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import java.util.Collection;
import com.basic.checkout.stock.Stock;
import java.util.Optional;

public final class Store {

    private static Store instance;
    private final Stock stock;

    private Store() {
        this.stock = Stock.initialise();
    }

    public static Store openShop() {
        return instance == null ? new Store() : instance;
    }

    public void loadStock(Collection<StockItem> skus) {
        stock.loadBulkStock(skus);
    }

    public void addStock(StockItem stockItem) {
        stock.addSku(stockItem);
    }

    public void addStockOffer(String sku, Offer offer) {
        stock.addStockOffer(sku, offer);
    }

    public boolean isStockEmpty() {
        return stock.isStockEmpty();
    }

    public Optional<StockItem> findItem(String skuId) {
        return stock.findItem(skuId);
    }
}
