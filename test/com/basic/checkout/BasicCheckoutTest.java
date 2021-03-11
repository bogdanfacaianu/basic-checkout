package com.basic.checkout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import main.com.basic.checkout.checkout.Basket;
import main.com.basic.checkout.Store;
import org.junit.Test;

public class BasicCheckoutTest extends TestHelper {

    @Test
    public void testCompleteCheckoutOperation() {
        Store store = Store.openShop();
        givenDefaultStock(store);

        Basket basket = new Basket();

        scanItem(basket, SKU_1);
        scanItem(basket, SKU_1);
        scanItem(basket, SKU_1);
        scanItem(basket, SKU_2);
        scanItem(basket, SKU_2);
        scanItem(basket, SKU_2);
        scanItem(basket, SKU_2);
        scanItem(basket, SKU_2);
        scanItem(basket, SKU_3);
        scanItem(basket, SKU_3);
        scanItem(basket, SKU_3);
        scanItem(basket, SKU_3);
        scanItem(basket, SKU_4);


        double totalCost = basket.checkout();
        assertEquals(getDesiredTotalCost(), totalCost);
    }


}
