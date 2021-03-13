package com.basic.checkout.sku;

import java.util.Objects;

public class ScannedItem extends Item {

    private int quantity;
    private double totalCost;
    private boolean discounted;

    public ScannedItem(String skuId, double price) {
        super(skuId, price);
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isDiscounted() {
        return discounted;
    }

    public void setDiscounted(boolean discounted) {
        this.discounted = discounted;
    }

    public double getTotalCost() {
        return this.totalCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ScannedItem that = (ScannedItem) o;
        return quantity == that.quantity
            && Double.compare(that.totalCost, totalCost) == 0
            && discounted == that.discounted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), quantity, totalCost, discounted);
    }

    @Override
    public String toString() {
        return "ScannedItem{" +
            "quantity=" + quantity +
            ", totalCost=" + totalCost +
            ", discounted=" + discounted +
            '}';
    }
}
