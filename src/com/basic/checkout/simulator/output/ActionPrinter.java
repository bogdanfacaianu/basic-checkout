package com.basic.checkout.simulator.output;

import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.simulator.InputHandler;
import com.basic.checkout.store.Store;

public interface ActionPrinter {

    void print(Store store, ShoppingBasket shoppingBasket);

    int printReadMenuOption(InputHandler inputHandler);
}
