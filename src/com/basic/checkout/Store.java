package com.basic.checkout;

import com.basic.checkout.stock.Offer;
import java.util.Collection;
import com.basic.checkout.stock.Sku;
import com.basic.checkout.stock.Stock;

public class Store {

    private static Store instance;
    private final Stock stock;

    private Store() {
        this.stock = Stock.initialise();
    }

    public static Store openShop() {
        return instance == null ? new Store() : instance;
    }

    public void loadStock(Collection<Sku> skus) {
        stock.loadBulkStock(skus);
    }

    public void addStockOffer(String sku, Offer offer) {

    }
}
