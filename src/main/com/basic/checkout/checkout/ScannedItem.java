package main.com.basic.checkout.checkout;

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
}
