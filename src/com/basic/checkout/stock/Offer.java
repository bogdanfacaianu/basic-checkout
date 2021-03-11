package com.basic.checkout.stock;

import java.util.Objects;

public class Offer {

    private final int multiplier;
    private final double multipliedCost;

    public Offer(int multiplier, double multipliedCost) {
        this.multiplier = multiplier;
        this.multipliedCost = multipliedCost;
    }

    public double getOfferPrice() {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Offer offer = (Offer) o;
        return multiplier == offer.multiplier && Double.compare(offer.multipliedCost, multipliedCost) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(multiplier, multipliedCost);
    }

    @Override
    public String toString() {
        return "Offer{" +
            "multiplier=" + multiplier +
            ", multipliedCost=" + multipliedCost +
            '}';
    }
}
