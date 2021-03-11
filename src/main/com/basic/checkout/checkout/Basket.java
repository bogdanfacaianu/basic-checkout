package main.com.basic.checkout.checkout;

import java.util.HashMap;
import java.util.Map;
import main.com.basic.checkout.stock.Sku;

public class Basket {

    private final Map<Sku, ScannedItem> scannedItems;

    public Basket() {
        this.scannedItems = new HashMap<>();
    }

    public void scanItem(ScannedItem scannedItem) {

    }

    public double checkout() {
        return 0;
    }

    public String toString() {
        return null;
    }
}
