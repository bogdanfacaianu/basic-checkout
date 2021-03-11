package com.basic.checkout.checkout;

import java.util.Objects;

public class ScannedItem {

    private final String sku;
    private int quantity;
    private double totalCost;
    private boolean discounted;

    public ScannedItem(String sku) {
        this.sku = sku;
        this.incrementQuantity();
    }

    public void incrementQuantity() {

    }

    public void updateTotal() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ScannedItem that = (ScannedItem) o;
        return quantity == that.quantity && Double.compare(that.totalCost, totalCost) == 0 && discounted == that.discounted && Objects.equals(sku, that.sku);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sku, quantity, totalCost, discounted);
    }
}
