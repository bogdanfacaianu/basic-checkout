package main.com.basic.checkout.stock;

import java.util.HashSet;
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
    public String toString() {
        return "Item{" +
            "skuId='" + skuId + '\'' +
            ", price=" + price +
            ", offers=" + offers +
            '}';
    }
}