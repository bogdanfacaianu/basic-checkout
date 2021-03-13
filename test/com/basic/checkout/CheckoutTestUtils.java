package com.basic.checkout;

import static com.basic.checkout.TestHelper.OFFER_1;
import static com.basic.checkout.TestHelper.OFFER_2;
import static com.basic.checkout.TestHelper.OFFER_PRICE_1;
import static com.basic.checkout.TestHelper.OFFER_PRICE_2;
import static com.basic.checkout.TestHelper.PRICE_1;
import static com.basic.checkout.TestHelper.PRICE_2;
import static com.basic.checkout.TestHelper.PRICE_3;
import static com.basic.checkout.TestHelper.PRICE_4;
import static com.basic.checkout.TestHelper.SKU_1;
import static com.basic.checkout.TestHelper.SKU_2;
import static com.basic.checkout.TestHelper.SKU_3;
import static com.basic.checkout.TestHelper.SKU_4;
import static com.basic.checkout.TestHelper.UNEXPECTED_SKU_1;
import static com.basic.checkout.TestHelper.UNEXPECTED_SKU_2;

import com.basic.checkout.checkout.StockTransaction;
import com.basic.checkout.checkout.TransactionManager;
import com.basic.checkout.sku.StockItem;
import com.basic.checkout.store.Store;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import com.basic.checkout.checkout.ShoppingBasket;
import org.junit.jupiter.api.BeforeEach;

public class CheckoutTestUtils {

    protected Store store;
    protected ShoppingBasket shoppingBasket;
    protected StockTransaction stockTransaction;

    @BeforeEach
    public void init() {
        store = Store.openShop();
        shoppingBasket = new ShoppingBasket();
        stockTransaction = new TransactionManager();
        givenDefaultStock();
    }

    protected void givenDefaultStock() {
        Set<StockItem> stockToAdd = new HashSet<>(Arrays.asList(
            new StockItem(SKU_1, PRICE_1),
            new StockItem(SKU_3, PRICE_3),
            new StockItem(SKU_4, PRICE_4),
            new StockItem(SKU_2, PRICE_2))
        );

        stockTransaction.loadStock(store, stockToAdd);
    }

    protected void givenStockHasOffersAvailable() {
        stockTransaction.addStockOffer(store, SKU_1, OFFER_1);
        stockTransaction.addStockOffer(store, SKU_2, OFFER_2);
    }

    protected void scanItem(ShoppingBasket shoppingBasket, String skuId) {
        stockTransaction.scanItem(store, shoppingBasket, skuId);
    }

    protected void whenItemsAreScannedBySku(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_4);
    }

    protected void whenItemsAreScannedAtRandom(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_4);
        scanItem(shoppingBasket, SKU_2);
    }

    protected void whenItemsAreScannedAlongUnexpectedProducts(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, UNEXPECTED_SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_4);
        scanItem(shoppingBasket, UNEXPECTED_SKU_2);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, UNEXPECTED_SKU_2);
    }

    protected void whenItemsAreScannedAtRandomWithOffersActive(ShoppingBasket shoppingBasket) {
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_1);
        scanItem(shoppingBasket, SKU_3);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_4);
        scanItem(shoppingBasket, SKU_2);
        scanItem(shoppingBasket, SKU_3);
    }

    protected double getTotalCostWithoutOffers() {
        return PRICE_1 + (PRICE_2 * 2) + PRICE_3 + PRICE_4;
    }

    protected double getTotalCostWithOffers() {
        return OFFER_PRICE_1 + ((OFFER_PRICE_2 * 2) + PRICE_2) + (PRICE_3 * 4) + PRICE_4;
    }
}
