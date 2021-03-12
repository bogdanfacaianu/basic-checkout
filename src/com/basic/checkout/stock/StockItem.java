package com.basic.checkout.stock;

import com.basic.checkout.common.Item;
import java.util.Objects;

public final class StockItem extends Item {

    private Offer offer;

    public StockItem(String skuId, double price) {
        super(skuId, price);
    }

    public Offer getOffer() {
        return offer;
    }

    public void applyOffer(Offer offer) {
        this.offer = offer;
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
        return Objects.equals(offer, stockItem.offer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), offer);
    }

    @Override
    public String toString() {
        return "StockItem{" +
            "offer=" + offer +
            '}';
    }
}
