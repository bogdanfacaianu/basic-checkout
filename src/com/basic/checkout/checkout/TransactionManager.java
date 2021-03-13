package com.basic.checkout.checkout;

import com.basic.checkout.store.Store;
import com.basic.checkout.sku.ScannedItem;
import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import java.util.Collection;
import java.util.Optional;

public class TransactionManager implements StockTransaction{

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
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(skuId);
        Optional<ScannedItem> updatedScan = store.decorateScannedItem(skuId, lastScan.orElse(null));
        if (updatedScan.isPresent()) {
            shoppingBasket.scanItem(updatedScan.get());
            return true;
        }
        return false;
    }

}
