package com.basic.checkout.common.action;

public enum StockManagementAction {
    LOAD_DEFAULT(1, "Load Default Stock"),
    REGISTER_STOCK(1, "Register SKU"),
    ADD_OFFER(1, "Add offer to SKU"),
    COMPLETE(2, "Go to Checkout"),
    CLEAR(3, "Clear Stock");

    private final int index;
    private final String value;

    public int getIndex() {
        return index;
    }

    public String getValue() {
        return value;
    }

    StockManagementAction(int index, String value) {
        this.index = index;
        this.value = value;
    }

    public static StockManagementAction fromIndex(int v) {
        for (StockManagementAction c: StockManagementAction.values()) {
            if (c.index == v) {
                return c;
            }
        }
        throw new IllegalArgumentException(String.valueOf(v));
    }
}
