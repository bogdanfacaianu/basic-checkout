package com.basic.checkout.checkout;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_PRICE_1;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.CheckoutTestUtils;
import com.basic.checkout.stock.StockItem;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScanManagerTest extends CheckoutTestUtils {

    private ScanManager scanManager;

    @BeforeEach
    public void init() {
        super.init();

        scanManager = new ScanManager();
    }

    @Test
    public void testScannedProductIsUpdated_whenFirstScanned() {
        StockItem stockItem = new StockItem(SKU_1, PRICE_1);
        Optional<ScannedItem> resultedScannedItem = scanManager.decorateStockItem(stockItem, null);
        assertTrue(resultedScannedItem.isPresent());
        ScannedItem finalScannedItem = resultedScannedItem.get();
        assertEquals(1, finalScannedItem.getQuantity());
        assertEquals(stockItem.getSkuId(), finalScannedItem.getSkuId());
        assertEquals(stockItem.getPrice(), finalScannedItem.getPrice());
        assertEquals(PRICE_1, finalScannedItem.getTotalCost());
        assertFalse(finalScannedItem.isDiscounted());
    }

    @Test
    public void testScannedItemIsSet_whenFirstScanned_and_thereIsNoOffer() {
        Optional<ScannedItem> maybeResultedScan = scanManager.decorateStockItem(givenStock(), null);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 1, PRICE_1, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithNoOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 1, PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.decorateStockItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 2, PRICE_1 * 2, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 3, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.decorateStockItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 4, PRICE_1 + OFFER_PRICE_1, true);
    }

    @Test
    public void testScannedItemGetsCostOnOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 2, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.decorateStockItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 3, OFFER_PRICE_1, true);
    }

    private StockItem givenStockWithOffer() {
        StockItem stockItem = new StockItem(SKU_1, PRICE_1);
        stockItem.applyOffer(OFFER_1);
        return stockItem;
    }

    private StockItem givenStock() {
        return new StockItem(SKU_1, PRICE_1);
    }

    private ScannedItem givenPreviouslyScannedItem(String skuId, double price, int quantity, double totalCost) {
        ScannedItem previousScan = new ScannedItem(skuId, price);
        previousScan.setQuantity(quantity);
        previousScan.setTotalCost(totalCost);

        return previousScan;
    }

    private void assertScannedItemIsUpdated(
        Optional<ScannedItem> maybeResultedScan,
        String expectedSku,
        double expectedPrice,
        int expectedQuantity,
        double expectedTotalCost,
        boolean expectedDiscounted
    ) {
        assertTrue(maybeResultedScan.isPresent());
        maybeResultedScan.ifPresent(resultedScan -> {
            assertEquals(expectedSku, resultedScan.getSkuId());
            assertEquals(expectedPrice, resultedScan.getPrice());
            assertEquals(expectedQuantity, resultedScan.getQuantity());
            assertEquals(expectedTotalCost, resultedScan.getTotalCost());
            assertEquals(expectedDiscounted, resultedScan.isDiscounted());
        });
    }
}
