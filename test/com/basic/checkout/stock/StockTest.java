package com.basic.checkout;

import com.basic.checkout.stock.Stock;
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
        stock.addSku();
    }

}
