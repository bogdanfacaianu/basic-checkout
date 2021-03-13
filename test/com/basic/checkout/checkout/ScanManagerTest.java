package com.basic.checkout.checkout;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_PRICE_1;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static com.basic.checkout.TestHelper.givenPreviouslyScannedItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ScanManagerTest {

    private static final int TOP_OFFER_MULTIPLIER = 5;
    private static final int TOP_OFFER_COST = 500;

    private ScanManager scanManager;

    @BeforeEach
    public void init() {
        scanManager = new ScanManager();
    }

    @Test
    public void testScannedItemIsSet_whenFirstScanned_and_thereIsNoOffer() {
        Optional<ScannedItem> maybeResultedScan = scanManager.buildUpdatedScannedItem(givenStock(), null);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 1, PRICE_1, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithNoOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 1, PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.buildUpdatedScannedItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 2, PRICE_1 * 2, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 3, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.buildUpdatedScannedItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 4, PRICE_1 + OFFER_PRICE_1, true);
    }

    @Test
    public void testScannedItemGetsCostOnOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 2, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.buildUpdatedScannedItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 3, OFFER_PRICE_1, true);
    }

    @Test
    public void testScannedItemGetsCostOnBestOffer() {
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 4, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = scanManager.buildUpdatedScannedItem(givenStockWithOffer(), previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 5, TOP_OFFER_COST, true);
    }

    private StockItem givenStockWithOffer() {
        StockItem stockItem = new StockItem(SKU_1, PRICE_1);
        stockItem.applyOffer(OFFER_1);
        stockItem.applyOffer(new Offer(TOP_OFFER_MULTIPLIER, TOP_OFFER_MULTIPLIER));
        return stockItem;
    }

    private StockItem givenStock() {
        return new StockItem(SKU_1, PRICE_1);
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
