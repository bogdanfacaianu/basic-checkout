package com.basic.checkout.checkout;

import static com.basic.checkout.TestHelper.PRICE_3;
import static com.basic.checkout.TestHelper.SKU_1;
import static com.basic.checkout.TestHelper.SKU_2;
import static com.basic.checkout.TestHelper.givenPreviouslyScannedItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.CheckoutTestUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class ShoppingBasketTest extends CheckoutTestUtils {

    @Test
    public void testBasketInitialisation() {
        assertEquals(0, shoppingBasket.checkout());
    }

    @Test
    public void testBasketReset() {
        whenItemsAreScannedBySku(shoppingBasket);

        shoppingBasket.clear();

        assertEquals(0, shoppingBasket.checkout());
    }

    @Test
    public void testFindingLastScan() {
        whenItemsAreScannedBySku(shoppingBasket);

        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(SKU_1);

        assertTrue(lastScan.isPresent());
    }

    @Test
    public void testItemScanned() {
        assertEquals(0, shoppingBasket.checkout());

        ScannedItem expectedScannedItem = givenPreviouslyScannedItem(SKU_2, PRICE_3, 4, PRICE_3);
        shoppingBasket.scanItem(expectedScannedItem);

        Optional<ScannedItem> scannedItem = shoppingBasket.findLastScan(SKU_2);
        assertTrue(scannedItem.isPresent());
        assertEquals(expectedScannedItem.getTotalCost(), scannedItem.get().getTotalCost());
    }

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

        whenItemsAreScannedAtRandomWithOffersActive(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithOffers(), totalCost);
    }

    @Test
    public void testCompleteCheckoutOperation_whenUnexpectedItemsAreScanned_thenTheyAreIgnored() {
        givenStockHasOffersAvailable();

        whenItemsAreScannedAlongUnexpectedProducts(shoppingBasket);

        double totalCost = shoppingBasket.checkout();
        assertEquals(getTotalCostWithOffers(), totalCost);
    }
}