package com.basic.checkout.simulator.output;

import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.simulator.InputHandler;
import com.basic.checkout.simulator.action.MenuAction;
import com.basic.checkout.simulator.action.StockManagementAction;
import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import com.basic.checkout.store.Store;
import java.util.Arrays;
import javafx.util.Pair;

public class PrintStockActions implements ActionPrinter {

    @Override
    public void print(Store store, ShoppingBasket shoppingBasket) {
        printSeparator();
        printOptions();
        printGoToCheckoutOptionIfAvailable(store);
        printSeparator();
    }

    @Override
    public int printReadMenuOption(InputHandler inputHandler) {
        printForInput();
        return inputHandler.chooseInput();
    }

    private void printForInput() {
        System.out.print("Input: ");
    }

    private void printSeparator() {
        System.out.println("-------------------------------------------");
    }

    private void printOptions() {
        System.out.println("Pick an action number:");
        Arrays.stream(StockManagementAction.values()).forEachOrdered(this::printCheckoutAction);
    }

    private void printCheckoutAction(StockManagementAction action) {
        System.out.printf("%d) %s%n", action.getIndex(), action.getValue());
    }

    private void printGoToCheckoutOptionIfAvailable(Store store) {
        if (!store.isStockEmpty()) {
            printMenuAction(MenuAction.GO_TO_CHECKOUT);
        }
    }

    private void printMenuAction(MenuAction action) {
        System.out.printf("%d) %s%n", action.getIndex(), action.getValue());
    }

    public String printScanSku(InputHandler inputHandler, Store store) {
        return inputHandler.scanSku(store);
    }

    public StockItem printReadStockValue(InputHandler inputHandler) {
        return inputHandler.readStockValue();
    }

    public Pair<String, Offer> printReadOffer(InputHandler inputHandler, Store store) {
        return inputHandler.readSkuOffer(store);
    }
}
