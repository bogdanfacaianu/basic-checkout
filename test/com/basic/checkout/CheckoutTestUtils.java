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

import com.basic.checkout.stock.StockItem;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.checkout.ScannedItem;
import org.junit.jupiter.api.BeforeEach;

public class CheckoutTestUtils {

    private Store store;
    protected ShoppingBasket shoppingBasket;

    @BeforeEach
    public void init() {
        store = Store.openShop();
        givenDefaultStock();
        shoppingBasket = new ShoppingBasket();
    }

    protected void givenDefaultStock() {
        Set<StockItem> stockToAdd = new HashSet<>(Arrays.asList(
            new StockItem(SKU_1, PRICE_1),
            new StockItem(SKU_3, PRICE_3),
            new StockItem(SKU_4, PRICE_4),
            new StockItem(SKU_2, PRICE_2))
        );

        store.loadStock(stockToAdd);
    }

    protected void givenStockHasOffersAvailable() {
        store.addStockOffer(SKU_1, OFFER_1);
        store.addStockOffer(SKU_2, OFFER_2);
    }

    protected void scanItem(ShoppingBasket shoppingBasket, String skuId) {
        Optional<ScannedItem> lastScan = shoppingBasket.findLastScan(skuId);
        Optional<ScannedItem> updatedScan = store.decorateScannedItem(skuId, lastScan.orElse(null));
        updatedScan.ifPresent(shoppingBasket::scanItem);
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

    protected double getTotalCostWithoutOffers() {
        return PRICE_1 + (PRICE_2 * 2) + PRICE_3 + PRICE_4;
    }

    protected double getTotalCostWithOffers() {
        return OFFER_PRICE_1 + (OFFER_PRICE_2 + PRICE_2) + PRICE_3 + PRICE_4;
    }
}
