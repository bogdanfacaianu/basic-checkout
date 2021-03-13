package com.basic.checkout;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_2;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.checkout.ScannedItem;
import com.basic.checkout.stock.StockItem;
import java.util.Collections;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StoreTest {

    private Store store;

    @BeforeEach
    public void init() {
        store = Store.openShop();
    }

    @Test
    public void testDecoratingScannedItemFails_whenSkuIsUnexpected() {
        Optional<ScannedItem> result = store.decorateScannedItem(SKU_1, null);
        assertFalse(result.isPresent());
    }

    @Test
    public void testDecoratingScannedItemPasses_whenSkuIsRegistered() {
        store.addStock(new StockItem(SKU_1, PRICE_1));
        Optional<ScannedItem> result = store.decorateScannedItem(SKU_1, null);
        assertTrue(result.isPresent());
    }

    @Test
    public void testAddStockInBulk_andApplyOffers() {
        store.loadStock(Collections.singletonList(new StockItem(SKU_1, PRICE_1)));
        store.addStockOffer(SKU_1, OFFER_2);

        Optional<ScannedItem> firstScan = store.decorateScannedItem(SKU_1, null);
        assertTrue(firstScan.isPresent());

        Optional<ScannedItem> result = store.decorateScannedItem(SKU_1, firstScan.get());
        assertTrue(result.isPresent());
        assertTrue(result.get().isDiscounted());
    }
}
