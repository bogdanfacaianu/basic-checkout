package com.basic.checkout;

import com.basic.checkout.sku.ScannedItem;
import com.basic.checkout.sku.Offer;

public class TestHelper {

    public static final String SKU_1 = "A";
    public static final String SKU_2 = "B";
    public static final String SKU_3 = "C";
    public static final String SKU_4 = "D";
    public static final String SKU_5 = "E";
    public static final String UNEXPECTED_SKU_1 = "surprise_surprise";
    public static final String UNEXPECTED_SKU_2 = "surprise_surprise";

    public static final double PRICE_1 = 50;
    public static final double PRICE_2 = 30;
    public static final double PRICE_3 = 20;
    public static final double PRICE_4 = 15;
    public static final double OFFER_PRICE_1 = 130;
    public static final double OFFER_PRICE_2 = 45;

    public static final int OFFER_MULTIPLIER_1 = 3;
    public static final int OFFER_MULTIPLIER_2 = 2;

    public static final Offer OFFER_1 = new Offer(OFFER_MULTIPLIER_1, OFFER_PRICE_1);
    public static final Offer OFFER_2 = new Offer(OFFER_MULTIPLIER_2, OFFER_PRICE_2);

    public static ScannedItem givenPreviouslyScannedItem(String skuId, double price, int quantity, double totalCost) {
        ScannedItem previousScan = new ScannedItem(skuId, price);
        previousScan.setQuantity(quantity);
        previousScan.setTotalCost(totalCost);

        return previousScan;
    }
}
