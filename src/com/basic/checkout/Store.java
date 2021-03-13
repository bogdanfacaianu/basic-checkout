package com.basic.checkout;

import com.basic.checkout.checkout.ScanManager;
import com.basic.checkout.checkout.ScannedItem;
import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Collection;
import com.basic.checkout.stock.Stock;
import java.util.Optional;

public final class Store {

    private static Store instance;
    private final Stock stock;
    private final ScanManager scanManager;

    private Store() {
        this.stock = Stock.initialise();
        this.scanManager = new ScanManager();
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

    public Optional<ScannedItem> decorateScannedItem(String skuId, ScannedItem previousScan) {
        return stock.findItem(skuId).flatMap(stockItem -> scanManager.buildUpdatedScannedItem(stockItem, previousScan));
    }
}
