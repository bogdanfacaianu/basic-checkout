package com.basic.checkout;

import com.basic.checkout.stock.Offer;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.checkout.ScannedItem;
import com.basic.checkout.stock.Sku;
import org.junit.jupiter.api.BeforeEach;

public class CheckoutTestUtils {

    protected static final String SKU_1 = "A";
    protected static final String SKU_2 = "B";
    protected static final String SKU_3 = "C";
    protected static final String SKU_4 = "D";
    protected static final String UNEXPECTED_SKU_1 = "surprise_surprise";
    protected static final String UNEXPECTED_SKU_2 = "surprise_surprise";

    protected static final double PRICE_1 = 50;
    protected static final double PRICE_2 = 30;
    protected static final double PRICE_3 = 20;
    protected static final double PRICE_4 = 15;
    protected static final double OFFER_PRICE_1 = 130;
    protected static final double OFFER_PRICE_2 = 45;

    protected static final int OFFER_MULTIPLIER_1 = 3;
    protected static final int OFFER_MULTIPLIER_2 = 2;

    protected static final Offer OFFER_1 = new Offer(OFFER_MULTIPLIER_1, OFFER_PRICE_1);
    protected static final Offer OFFER_2 = new Offer(OFFER_MULTIPLIER_2, OFFER_PRICE_2);


    private Store store;
    protected ShoppingBasket shoppingBasket;

    @BeforeEach
    public void init() {
        store = Store.openShop();
        givenDefaultStock();
        shoppingBasket = new ShoppingBasket();
    }

    protected void givenDefaultStock() {
        Set<Sku> stockToAdd = new HashSet<>(Arrays.asList(
            new Sku(SKU_1, PRICE_1),
            new Sku(SKU_3, PRICE_3),
            new Sku(SKU_4, PRICE_4),
            new Sku(SKU_2, PRICE_2))
        );

        store.loadStock(stockToAdd);
    }

    protected void givenStockHasOffersAvailable() {
        store.addStockOffer(SKU_1, OFFER_1);
        store.addStockOffer(SKU_2, OFFER_2);
    }

    protected void scanItem(ShoppingBasket shoppingBasket,String skuId) {
        ScannedItem scannedItem = new ScannedItem(skuId);
        shoppingBasket.scanItem(scannedItem);
    }

    protected void whenItemsAreScannedBySku(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_4);
    }

    protected void whenItemsAreScannedAtRandom(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_4);
        scanItem(shoppingBasket, SKU_2);
    }

    protected void whenItemsAreScannedAlongUnexpectedProducts(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, UNEXPECTED_SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_4);
        scanItem(shoppingBasket, UNEXPECTED_SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, UNEXPECTED_SKU_2);
    }

    protected double getTotalCostWithoutOffers() {
        return PRICE_1 + (PRICE_2 * 2) + PRICE_3 + PRICE_4;
    }

    protected double getTotalCostWithOffers() {
        return OFFER_PRICE_1 + (OFFER_PRICE_2 + PRICE_2) + PRICE_3 + PRICE_4;
    }
}
