package com.basic.checkout.common.action;

public enum CheckoutAction {
    SCAN_PRODUCT(1, "Scan Product"),
    CONFIRM(2, "Confirm Checkout"),
    CLEAR(3, "Reset Basket"),
    EXIT(4, "Exit");

    private final int index;
    private final String value;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    CheckoutAction(int index, String value) {
        this.index = index;
        this.value = value;
    }
}
