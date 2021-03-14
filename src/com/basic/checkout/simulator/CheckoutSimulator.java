package com.basic.checkout.simulator;

import com.basic.checkout.checkout.ShoppingBasket;
import com.basic.checkout.checkout.StockTransaction;
import com.basic.checkout.checkout.TransactionManager;
import com.basic.checkout.simulator.output.PrintBasketActions;
import com.basic.checkout.simulator.output.PrintStockActions;
import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import com.basic.checkout.store.Store;
import javafx.util.Pair;

public class CheckoutSimulator implements Simulator {

    private final StockTransaction stockTransaction;
    private final Store store;
    private final ShoppingBasket shoppingBasket;

    private final PrintBasketActions printBasketActions;
    private final PrintStockActions printStockActions;

    private final InputHandler inputHandler;

    public CheckoutSimulator() {
        store = Store.openShop();
        stockTransaction = new TransactionManager();
        shoppingBasket = new ShoppingBasket();

        inputHandler = new InputHandler();

        printBasketActions = new PrintBasketActions();
        printStockActions = new PrintStockActions();
    }

    @Override
    public void startSimulator() {
        promptFirstMenu();
    }

    private void promptFirstMenu() {
        printStockActions.print(store, shoppingBasket);
        int chosenMenuOption = printStockActions.printReadMenuOption(inputHandler);
        pickNextStockPath(chosenMenuOption);
    }

    private void promptCheckoutMenu() {
        printBasketActions.print(store, shoppingBasket);
        int chosenMenuOption = printBasketActions.printReadMenuOption(inputHandler);
        pickNextCheckoutPath(chosenMenuOption);
    }

    private void pickNextStockPath(int chosenMenuOption) {
        switch (chosenMenuOption) {
            case 1:
                loadDefaultStock();
                break;
            case 2:
                StockItem item = printStockActions.printReadStockValue(inputHandler);
                stockTransaction.addStock(store, item);
                break;
            case 3:
                Pair<String, Offer> readValues = printStockActions.printReadOffer(inputHandler, store);
                stockTransaction.addStockOffer(store, readValues.getKey(), readValues.getValue());
                break;
            case 4:
                promptCheckoutMenu();
                break;
            default:
                break;
        }
        promptFirstMenu();
    }

    private void pickNextCheckoutPath(int chosenMenuOption) {
        switch (chosenMenuOption) {
            case 1:
                String scannedSku = printStockActions.printScanSku(inputHandler, store);
                stockTransaction.scanItem(store, shoppingBasket, scannedSku);
                break;
            case 2:
                printBasketActions.printReceipt(shoppingBasket);
                System.exit(1);
            case 3:
                shoppingBasket.clear();
                break;
            case 4:
                printBasketActions.printCancelled();
                System.exit(1);
            default:
                break;
        }
        promptCheckoutMenu();
    }

    private void loadDefaultStock() {
        stockTransaction.addStock(store, new StockItem("A", 50));
        stockTransaction.addStock(store, new StockItem("B", 30));
        stockTransaction.addStock(store, new StockItem("C", 20));
        stockTransaction.addStock(store, new StockItem("D", 15));
        stockTransaction.addStockOffer(store, "A", new Offer(3, 130));
        stockTransaction.addStockOffer(store, "B", new Offer(2, 45));
    }
}
