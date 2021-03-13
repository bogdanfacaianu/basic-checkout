package com.basic.checkout.checkout;

import com.basic.checkout.sku.ScannedItem;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public final class ShoppingBasket {

    private final Map<String, ScannedItem> scannedItems;

    public ShoppingBasket() {
        this.scannedItems = new HashMap<>();
    }

    public void scanItem(ScannedItem scannedItem) {
        scannedItems.put(scannedItem.getSkuId(), scannedItem);
    }

    public Optional<ScannedItem> findLastScan(String skuId) {
        if (scannedItems.containsKey(skuId)) {
            return Optional.of(scannedItems.get(skuId));
        }
        return Optional.empty();
    }

    public double checkout() {
        return scannedItems.values()
            .stream()
            .mapToDouble(ScannedItem::getTotalCost)
            .sum();
    }

    public void clear() {
        scannedItems.clear();
    }
}
