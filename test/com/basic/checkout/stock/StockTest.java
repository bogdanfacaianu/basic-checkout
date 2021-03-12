package com.basic.checkout.stock;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_PRICE_1;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.checkout.ScannedItem;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockTest {

    private Stock stock;

    @BeforeEach
    public void init() {
        stock = Stock.initialise();
    }

    @Test
    public void testOfferIsNotAdded_whenSkuIsMissing() {

        stock.addStockOffer(SKU_1, OFFER_1);

        assertTrue(stock.isStockEmpty());
    }

    @Test
    public void testOfferIsAddedSuccessfully_whenSkuIsRegistered() {
        givenStockWithOffer();

        Optional<Offer> result = stock.getOfferForSku(SKU_1);
        assertTrue(result.isPresent());
        assertEquals(OFFER_1, result.get());
    }

    @Test
    public void testScannedItemIsNotUpdated_whenSkuIsUnexpected() {

        Optional<ScannedItem> maybeResultedScan = stock.decorateStockItem(SKU_1, null);

        assertFalse(maybeResultedScan.isPresent());
    }

    @Test
    public void testScannedItemIsSet_whenFirstScanned_and_thereIsNoOffer() {
        givenStock();

        Optional<ScannedItem> maybeResultedScan = stock.decorateStockItem(SKU_1, null);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 1, PRICE_1, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithNoOffer() {
        givenStockWithOffer();
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 1, PRICE_1);

        Optional<ScannedItem> maybeResultedScan = stock.decorateStockItem(SKU_1, previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 2, PRICE_1 * 2, false);
    }

    @Test
    public void testScannedItemIsUpdatedWithOffer() {
        givenStockWithOffer();
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 3, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = stock.decorateStockItem(SKU_1, previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 4, PRICE_1 + OFFER_PRICE_1, true);
    }

    @Test
    public void testScannedItemGetsCostOnOffer() {
        givenStockWithOffer();
        ScannedItem previousScan = givenPreviouslyScannedItem(SKU_1, PRICE_1, 2, OFFER_PRICE_1);

        Optional<ScannedItem> maybeResultedScan = stock.decorateStockItem(SKU_1, previousScan);

        assertScannedItemIsUpdated(maybeResultedScan, SKU_1, PRICE_1, 3, OFFER_PRICE_1, true);
    }

    private void givenStockWithOffer() {
        stock.addSku(new StockItem(SKU_1, PRICE_1));
        stock.addStockOffer(SKU_1, OFFER_1);
    }

    private void givenStock() {
        stock.addSku(new StockItem(SKU_1, PRICE_1));
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
