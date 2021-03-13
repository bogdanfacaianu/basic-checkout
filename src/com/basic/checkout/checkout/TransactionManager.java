package com.basic.checkout.checkout;

import com.basic.checkout.Store;
import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Collection;

public class TransactionManager {

    private final Store store;

    public TransactionManager() {
        this.store = Store.openShop();
    }

    public void loadStock(Collection<StockItem> skus) {
        store.loadStock(skus);
    }

    public void addStock(StockItem stockItem) {
        store.addStock(stockItem);
    }

    public void addStockOffer(String sku, Offer offer) {
        store.addStockOffer(sku, offer);
    }

    public boolean scanItem(ShoppingBasket basket, String sku) {
        return false;
    }

}
