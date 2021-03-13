package com.basic.checkout.stock;

import com.basic.checkout.common.Item;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public final class StockItem extends Item {

    private final Set<Offer> offers;

    public StockItem(String skuId, double price) {
        super(skuId, price);
        this.offers = new HashSet<>();
    }

    public Set<Offer> getOffers() {
        return offers;
    }

    public void applyOffer(Offer offer) {
        this.offers.add(offer);
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
        StockItem stockItem = (StockItem) o;
        return Objects.equals(offers, stockItem.offers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), offers);
    }

    @Override
    public String toString() {
        return "StockItem{" +
            "offers=" + offers +
            '}';
    }
}
