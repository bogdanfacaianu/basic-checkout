package com.basic.checkout.stock;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
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

        Set<Offer> result = stock.getOffersForSku(SKU_1);
        assertFalse(result.isEmpty());
        assertEquals(OFFER_1, result.iterator().next());
    }

    @Test
    public void testFindingOfferForMissingSku_doesntThrowException() {
        Set<Offer> result = stock.getOffersForSku(SKU_1);
        assertTrue(result.isEmpty());
    }

    private void givenStockWithOffer() {
        stock.addSku(new StockItem(SKU_1, PRICE_1));
        stock.addStockOffer(SKU_1, OFFER_1);
    }
}
