package com.basic.checkout.stock;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_2;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import java.util.Arrays;
import java.util.Set;
import org.junit.jupiter.api.Test;

public class StockItemTest {

    @Test
    public void testAddingOffers() {
        StockItem stockItem = new StockItem(SKU_1, PRICE_1);

        stockItem.applyOffer(OFFER_1);
        stockItem.applyOffer(OFFER_2);

        Set<Offer> storedOffers = stockItem.getOffers();

        assertFalse(storedOffers.isEmpty());
        assertTrue(storedOffers.containsAll(Arrays.asList(OFFER_1, OFFER_2)));
    }

}
