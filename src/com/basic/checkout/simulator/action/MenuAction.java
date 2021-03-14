package com.basic.checkout.simulator.action;

public enum MenuAction {
    GO_TO_CHECKOUT(4, "Go to Checkout");

    private final int index;
    private final String value;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    MenuAction(int index, String value) {
        this.index = index;
        this.value = value;
    }
}

