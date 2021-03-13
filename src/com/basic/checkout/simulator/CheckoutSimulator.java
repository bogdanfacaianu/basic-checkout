package com.basic.checkout.simulator;

import com.basic.checkout.store.Store;
import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.checkout.TransactionManager;
import java.util.Scanner;

public class CheckoutSimulator {

    private static CheckoutSimulator instance;

    private final TransactionManager transactionManager;
    private final Store store;
    private final ShoppingBasket shoppingBasket;

    private final Scanner input;

    private CheckoutSimulator() {
        store = Store.openShop();
        transactionManager = new TransactionManager();
        shoppingBasket = new ShoppingBasket();
        input = new Scanner(System.in);
    }

    public CheckoutSimulator startSimulator() {
        return instance == null ? new CheckoutSimulator() : instance;
    }

    private void promptFirstMenu() {

    }

    private void promptStockOptionsMenu() {

    }

    private void promptScanProducts() {

    }

    private int validateAndReturnChosenInputOption() {
        return 0;
    }

    private String validateAndUseScannedProduct() {
        return null;
    }

    private String validateAndStoreStock() {
        return null;
    }

    private void registerStock() {

    }

    private void registerDefaultStock() {

    }

    private void addOfferToProduct() {

    }

    private void printScannedItems() {

    }
}
