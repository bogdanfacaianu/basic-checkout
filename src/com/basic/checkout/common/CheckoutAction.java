package com.basic.checkout.common;

public enum CheckoutAction {
    CLEAR(1, "Reset Basket"),
    SCAN_PRODUCT(2, "Scan Product"),
    CONFIRM(3, "Confirm Checkout");

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

    public static CheckoutAction fromIndex(int v) {
        for (CheckoutAction c: CheckoutAction.values()) {
            if (c.index == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
