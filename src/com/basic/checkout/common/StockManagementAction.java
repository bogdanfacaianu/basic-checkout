package com.basic.checkout.common;

public enum CheckoutAction {
    CLEAR(1),
    COMPLETE(2);

    private final int index;

    CheckoutAction(int index) {
        this.index = index;
    }

    public static CheckoutAction fromValue(int v) {
        for (CheckoutAction c: CheckoutAction.values()) {
            if (c.index == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
