package com.basic.checkout.checkout;

import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static com.basic.checkout.TestHelper.SKU_5;
import static com.basic.checkout.TestHelper.givenPreviouslyScannedItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.CheckoutTestUtils;
import java.util.Optional;
import org.junit.jupiter.api.Test;

public class TransactionManagerTest extends CheckoutTestUtils {

    @Test
    public void testBasketItemIsFirstTimeScanned() {
        transactionManager.scanItem(shoppingBasket, SKU_1);

        thenItemHasBeenSavedSuccessfully(1);
    }

    @Test
    public void testBasketItemIsUpdatedFromStock() {
        givenSkuHasBeenAlreadyScannedOnce();

        transactionManager.scanItem(shoppingBasket, SKU_1);

        thenItemHasBeenSavedSuccessfully(2);
    }

    @Test
    public void testScanIsRejected_whenAnUnexpectedProductIsEncountered() {
        transactionManager.scanItem(shoppingBasket, SKU_5);

        thenItemWasRejected();
    }

    private void givenSkuHasBeenAlreadyScannedOnce() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 1, PRICE_1);
        shoppingBasket.scanItem(previousScan);
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
