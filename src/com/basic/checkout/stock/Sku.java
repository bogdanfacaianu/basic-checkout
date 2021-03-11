package com.basic.checkout.stock;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Sku {

    private final String skuId;
    private final double price;
    private final Set<Offer> offers;

    public Sku(String skuId, double price) {
        this.skuId = skuId;
        this.price = price;
        this.offers = new HashSet<>();
    }

    public void addOffer(Offer offer) {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Sku sku = (Sku) o;
        return Double.compare(sku.price, price) == 0
            && Objects.equals(skuId, sku.skuId)
            && Objects.equals(offers, sku.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(skuId, price, offers);
    }

    @Override
    public String toString() {
        return "Item{" +
            "skuId='" + skuId + '\'' +
            ", price=" + price +
            ", offers=" + offers +
            '}';
    }
}