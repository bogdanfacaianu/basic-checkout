package com.basic.checkout.checkout;

import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import com.basic.checkout.store.Store;
import java.util.Collection;

public interface StockTransaction {

    void loadStock(Store store, Collection<StockItem> skus);

    void addStock(Store store, StockItem stockItem);

    void addStockOffer(Store store, String sku, Offer offer);

    boolean scanItem(Store store, ShoppingBasket shoppingBasket, String skuId);
}
