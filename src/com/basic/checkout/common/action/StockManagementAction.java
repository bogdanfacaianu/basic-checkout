package com.basic.checkout.common.action;

public enum StockManagementAction {
    LOAD_DEFAULT(1, "Load Default Stock"),
    REGISTER_STOCK(2, "Register SKU"),
    ADD_OFFER(3, "Add offer to SKU"),
    COMPLETE(4, "Go to Checkout");

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
