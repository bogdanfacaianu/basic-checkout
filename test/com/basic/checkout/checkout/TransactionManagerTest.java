package com.basic.checkout.checkout;

import static com.basic.checkout.TestHelper.OFFER_PRICE_2;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.PRICE_2;
import static com.basic.checkout.TestHelper.SKU_1;
import static com.basic.checkout.TestHelper.SKU_5;
import static com.basic.checkout.TestHelper.givenPreviouslyScannedItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.CheckoutTestUtils;
import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class TransactionManagerTest extends CheckoutTestUtils {

    @Test
    public void testBasketItemIsFirstTimeScanned() {
        boolean result = transactionManager.scanItem(store, shoppingBasket, SKU_1);

        assertTrue(result);
        thenItemHasBeenSavedSuccessfully(1);
    }

    @Test
    public void testItemIsUpdatedOnOfferForNewlyAddedStock() {
        transactionManager.addStock(store, new StockItem(SKU_5, PRICE_2));
        transactionManager.addStockOffer(store, SKU_5, new Offer(1, OFFER_PRICE_2));

        boolean result = transactionManager.scanItem(store, shoppingBasket, SKU_5);

        assertTrue(result);
        thenItemHasBeenUpdatedAfterNewStockAdded();
    }

    @Test
    public void testBasketItemIsUpdatedFromStock() {
        givenSkuHasBeenAlreadyScannedOnce();

        boolean result = transactionManager.scanItem(store, shoppingBasket, SKU_1);

        assertTrue(result);
        thenItemHasBeenSavedSuccessfully(2);
    }

    @Test
    public void testScanIsRejected_whenAnUnexpectedProductIsEncountered() {
        boolean result = transactionManager.scanItem(store, shoppingBasket, SKU_5);

        assertFalse(result);
        thenItemWasRejected();
    }

    private void givenSkuHasBeenAlreadyScannedOnce() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 1, PRICE_1);
        shoppingBasket.scanItem(previousScan);
    }

    private void thenItemHasBeenUpdatedAfterNewStockAdded() {
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(SKU_5);
        assertTrue(lastScan.isPresent());
        ScannedItem lastScanObject = lastScan.get();
        assertEquals(1, lastScanObject.getQuantity());
        assertEquals(OFFER_PRICE_2, lastScanObject.getTotalCost());
        assertTrue(lastScanObject.isDiscounted());
    }

    private void thenItemHasBeenSavedSuccessfully(int expectedQuantity) {
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(SKU_1);
        assertTrue(lastScan.isPresent());
        assertEquals(expectedQuantity, lastScan.get().getQuantity());
    }

    private void thenItemWasRejected() {
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(SKU_1);
        assertFalse(lastScan.isPresent());
    }
}
