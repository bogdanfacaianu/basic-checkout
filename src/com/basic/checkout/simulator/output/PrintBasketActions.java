package com.basic.checkout.simulator.output;

import static java.lang.String.format;

import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.simulator.action.CheckoutAction;
import com.basic.checkout.simulator.InputHandler;
import com.basic.checkout.sku.ScannedItem;
import com.basic.checkout.store.Store;
import java.util.Arrays;
import java.util.Collection;

public class PrintBasketActions implements ActionPrinter {

    @Override
    public void print(Store store, ShoppingBasket shoppingBasket) {
        printSeparator();
        printCheckoutScansHeader();
        printSeparator();
        printScannedItems(shoppingBasket);
        printSeparator();
        printTotal(shoppingBasket);
        printSeparator();
        printOptions();
    }

    @Override
    public int printReadMenuOption(InputHandler inputHandler) {
        return inputHandler.chooseInput();
    }

    private void printCheckoutScansHeader() {
        System.out.printf("%s      %s      %s      %s%n", "Sku", "Quantity", "Cost", "Discounted");
    }

    private void printScannedItems(ShoppingBasket shoppingBasket) {
        Collection<ScannedItem> scannedItems = shoppingBasket.getScannedItems();
        scannedItems.stream().map(this::getLineOutput).forEach(this::printScan);
    }

    private String getLineOutput(ScannedItem scannedItem) {
        char discounted = scannedItem.isDiscounted()? '*' : ' ';
        return format("%s         %d          %.2fp           %c",
            scannedItem.getSkuId(), scannedItem.getQuantity(), scannedItem.getTotalCost(), discounted);
    }

    private void printScan(String line) {
        System.out.println(line);
    }

    private void printSeparator() {
        System.out.println("-------------------------------------------");
    }

    private void printTotal(ShoppingBasket shoppingBasket) {
        System.out.printf("Total: %.2fp%n", shoppingBasket.checkout());
    }

    private void printOptions() {
        System.out.println("Pick an action number:");
        Arrays.stream(CheckoutAction.values()).forEachOrdered(this::printCheckoutAction);
    }

    private void printCheckoutAction(CheckoutAction action) {
        System.out.printf("%d) %s%n", action.getIndex(), action.getValue());
    }
}
