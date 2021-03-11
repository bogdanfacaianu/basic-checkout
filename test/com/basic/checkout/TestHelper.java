package com.basic.checkout;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import main.com.basic.checkout.checkout.Basket;
import main.com.basic.checkout.Store;
import main.com.basic.checkout.checkout.ScannedItem;
import main.com.basic.checkout.stock.Sku;

public class TestHelper {

    protected static final String SKU_1 = "A";
    protected static final String SKU_2 = "B";
    protected static final String SKU_3 = "C";
    protected static final String SKU_4 = "D";

    protected static final double PRICE_1 = 50;
    protected static final double PRICE_2 = 30;
    protected static final double PRICE_3 = 20;
    protected static final double PRICE_4 = 15;


    protected void givenDefaultStock(Store store) {
        Set<Sku> stockToAdd = new HashSet<>(Arrays.asList(
            new Sku(SKU_1, PRICE_1),
            new Sku(SKU_3, PRICE_3),
            new Sku(SKU_4, PRICE_4),
            new Sku(SKU_2, PRICE_2))
        );

        store.loadStock(stockToAdd);
    }

    protected void scanItem(Basket basket,String skuId) {
        ScannedItem scannedItem = new ScannedItem(skuId);
        basket.scanItem(scannedItem);
    }

    protected double getDesiredTotalCost() {
        return 130 + (90 + 30) + 80 + 15;
    }
}
