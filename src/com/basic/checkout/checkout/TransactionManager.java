package com.basic.checkout.checkout;

import com.basic.checkout.store.Store;
import com.basic.checkout.sku.ScannedItem;
import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import java.util.Collection;
import java.util.Optional;

public class TransactionManager implements StockTransaction{

    private final ScanManager scanManager;

    public TransactionManager() {
        scanManager = new ScanManager();
    }

    @Override
    public void loadStock(Store store, Collection<StockItem> skus) {
        store.loadStock(skus);
    }

    @Override
    public void addStock(Store store, StockItem stockItem) {
        store.addStock(stockItem);
    }

    @Override
    public void addStockOffer(Store store, String sku, Offer offer) {
        store.addStockOffer(sku, offer);
    }

    @Override
    public boolean scanItem(Store store, ShoppingBasket shoppingBasket, String skuId) {
        return store.findItem(skuId).map(stockItem -> handleScannedItem(stockItem, shoppingBasket)).orElse(false);
    }

    private boolean handleScannedItem(StockItem stockItem, ShoppingBasket shoppingBasket) {
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(stockItem.getSkuId());
        Optional<ScannedItem> updatedScan = decorateScannedItem(stockItem, lastScan.orElse(null));
        if (updatedScan.isPresent()) {
            shoppingBasket.scanItem(updatedScan.get());
            return true;
        }
        return false;
    }

    private Optional<ScannedItem> decorateScannedItem(StockItem stockItem, ScannedItem previousScan) {
        return scanManager.buildUpdatedScannedItem(stockItem, previousScan);
    }

}
