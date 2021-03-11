package main.com.basic.checkout.stock;

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
    public String toString() {
        return "Offer{" +
            "multiplier=" + multiplier +
            ", multipliedCost=" + multipliedCost +
            '}';
    }
}
