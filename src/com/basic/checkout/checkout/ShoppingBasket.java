package com.basic.checkout.checkout;

import java.util.HashMap;
import java.util.Map;
import com.basic.checkout.stock.Sku;

public class ShoppingBasket {

    private final Map<Sku, ScannedItem> scannedItems;

    public ShoppingBasket() {
        this.scannedItems = new HashMap<>();
    }

    public void scanItem(ScannedItem scannedItem) {

    }

    public double checkout() {
        return 0;
    }

    public void clear() {
        this.scannedItems.clear();
    }

    public String toString() {
        return null;
    }
}
