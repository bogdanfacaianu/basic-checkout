package com.basic.checkout.checkout;

import com.basic.checkout.stock.Stock;
import com.basic.checkout.stock.StockItem;
import java.util.Optional;

public class ScanManager {

    public ScanManager() {}

    public Optional<ScannedItem> decorateStockItem(StockItem stockItem, ScannedItem previousScan) {
        return Optional.empty();
    }

    private Optional<ScannedItem> buildUpdatedScannedItem(StockItem stockItem, ScannedItem previousScan) {
        return Optional.ofNullable(previousScan);
    }

}
