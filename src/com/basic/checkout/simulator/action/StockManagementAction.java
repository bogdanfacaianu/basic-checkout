package com.basic.checkout.simulator.action;

public enum StockManagementAction {
    LOAD_DEFAULT(1, "Load Default Stock"),
    REGISTER_STOCK(2, "Register SKU"),
    ADD_OFFER(3, "Add offer to SKU");

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
}