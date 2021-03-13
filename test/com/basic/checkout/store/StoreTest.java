package com.basic.checkout.store;

import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.SKU_1;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.basic.checkout.sku.StockItem;
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
    public void testValidatingStockIsEmpty() {
        assertTrue(store.isStockEmpty());

        store.addStock(new StockItem(SKU_1, PRICE_1));

        assertFalse(store.isStockEmpty());
    }

    @Test
    public void testFindingItemsInStoreStock() {
        StockItem stockItem = new StockItem(SKU_1, PRICE_1);
        store.addStock(stockItem);

        Optional<StockItem> foundItem = store.findItem(SKU_1);
        assertTrue(foundItem.isPresent());
        assertEquals(stockItem, foundItem.get());
    }
}
