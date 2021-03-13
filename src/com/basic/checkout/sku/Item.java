package com.basic.checkout.sku;

import java.util.Objects;

public class Item {

    private final String skuId;
    private final double price;

    public Item(String skuId, double price) {
        this.skuId = skuId;
        this.price = price;
    }

    public String getSkuId() {
        return skuId;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Double.compare(item.price, price) == 0 && Objects.equals(skuId, item.skuId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId, price);
    }

    @Override
    public String toString() {
        return "Item{" +
            "skuId='" + skuId + '\'' +
            ", price=" + price +
            '}';
    }
}
