package com.basic.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ShoppingBasketTest extends CheckoutTestUtils {

    @Test
    public void testCompleteCheckoutOperation() {
        whenItemsAreScannedBySku(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithoutOffers(), totalCost);
    }

    @Test
    public void testCompleteCheckoutOperation_whenItemsAreScannedAtRandom() {
        whenItemsAreScannedAtRandom(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithoutOffers(), totalCost);
    }

    @Test
    public void testCompleteCheckoutOperation_whenItemsAreOnOffer_thenTotalPriceIsUpdated() {
        givenStockHasOffersAvailable();

        whenItemsAreScannedAlongUnexpectedProducts(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithOffers(), totalCost);
    }

    @Test
    public void testCompleteCheckoutOperation_whenUnexpectedItemsAreScanned_thenTheyAreIgnored() {
        givenStockHasOffersAvailable();

        whenItemsAreScannedAlongUnexpectedProducts(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithoutOffers(), totalCost);
    }
}
