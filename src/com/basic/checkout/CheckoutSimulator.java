package com.basic.checkout;

import com.basic.checkout.checkout.StockTransaction;
import com.basic.checkout.store.Store;
import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.checkout.TransactionManager;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import java.util.Scanner;

public class CheckoutSimulator implements Simulator {

    Logger log = LoggerFactory.getLogger(CheckoutSimulator.class);

    private final StockTransaction stockTransaction;
    private final Store store;
    private final ShoppingBasket shoppingBasket;

    private final Scanner input;

    public CheckoutSimulator() {
        store = Store.openShop();
        stockTransaction = new TransactionManager();
        shoppingBasket = new ShoppingBasket();
        input = new Scanner(System.in);
    }

    @Override
    public void startSimulator() {
        promptFirstMenu();
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
