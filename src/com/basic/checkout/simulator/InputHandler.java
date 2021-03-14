package com.basic.checkout.simulator;

import com.basic.checkout.sku.Offer;
import com.basic.checkout.sku.StockItem;
import com.basic.checkout.store.Store;
import java.util.Scanner;
import javafx.util.Pair;

public class InputHandler {

    private final Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public int chooseInput() {
        int number;
        do {
            while (!scanner.hasNextInt()) {
                String value = scanner.next();
                scanner.nextLine();
                System.out.printf("\"%s\" is not a valid number.\n", value);
                printSeparator();
            }
            number = scanner.nextInt();
        } while (number < 0);
        scanner.nextLine();
        return number;
    }

    public String scanSku(Store store) {
        String value;
        do {
            System.out.print("Scan sku: ");
            value = scanner.next();
            if (!doesSkuExist(store, value)) {
                System.out.println("Sku not found. Please try again.");
                printSeparator();
                value = null;
            }
        } while (value == null);
        return value;
    }

    public StockItem readStockValue() {
        StockItem item = null;
        do {
            System.out.print("Register stock (format accepted is: sku price): ");
            String readValue = scanner.nextLine();
            String[] s = readValue.split(" ");

            if (isNewSkuInputValid(s)) {
                item = new StockItem(s[0], Double.parseDouble(s[1]));
            }

        } while (item == null);
        return item;
    }

    public Pair<String, Offer> readSkuOffer(Store store) {
        Offer offer = null;
        String readSku = null;
        do {
            System.out.print("Introduce offer for sku (format accepted is: sku items_multiplier special_cost): ");
            String readValue = scanner.nextLine();
            String[] s = readValue.split(" ");

            if (isOfferInputValid(store, s)) {
                offer = new Offer(Integer.parseInt(s[1]), Double.parseDouble(s[1]));
            }

        } while (offer == null);
        return new Pair(readSku, offer);
    }

    private boolean isNewSkuInputValid(String[] input) {
        if (input.length != 2) {
            System.out.println("Not enough arguments provided.");
            printSeparator();
            return false;
        } else if (!isLetter(input[0])) {
            System.out.println("Invalid sku provided. Should be an individual letter of the alphabet");
            printSeparator();
            return false;
        } else if (!isDouble(input[1])) {
            System.out.println("Price provided is not a number.");
            printSeparator();
            return false;
        }
        return true;
    }

    private boolean isOfferInputValid(Store store, String[] input) {
        if (input.length != 3) {
            System.out.println("Not enough arguments provided.");
            printSeparator();
            return false;
        } else if (!isLetter(input[0])) {
            System.out.println("Invalid sku provided. Should be an individual letter of the alphabet");
            printSeparator();
            return false;
        } else if (!doesSkuExist(store, input[0])) {
            System.out.println("Unknown sku!");
            printSeparator();
            return false;
        } else if (!isInt(input[1])) {
            System.out.println("Invalid multiplier provided. Should be a valid number.");
            printSeparator();
            return false;
        } else if (!isDouble(input[2])) {
            System.out.println("Special price provided is not a number.");
            printSeparator();
            return false;
        }
        return true;
    }

    private boolean doesSkuExist(Store store, String skuId) {
        return store.findItem(skuId).isPresent();
    }

    private boolean isInt(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private boolean isLetter(String str) {
        if (str.length() != 1) {
            return false;
        }
        return Character.isLetter(str.charAt(0));
    }

    private void printSeparator() {
        System.out.println("-------------------------------------------");
    }
}
