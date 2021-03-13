package com.basic.checkout.checkout;

import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Optional;

public class ScanManager {

    public Optional<ScannedItem> buildUpdatedScannedItem(StockItem stockItem, ScannedItem previousScan) {
        Offer stockOffer = stockItem.getOffer();
        ScannedItem scannedItem = new ScannedItem(stockItem.getSkuId(), stockItem.getPrice());
        if (previousScan != null) {
            scannedItem.setQuantity(previousScan.getQuantity() + 1);
            updateTotalCost(scannedItem, stockOffer);
        } else {
            scannedItem.setQuantity(1);
            scannedItem.setTotalCost(stockItem.getPrice());
        }
        return Optional.of(scannedItem);
    }

    private void updateTotalCost(ScannedItem scannedItem, Offer itemOffer) {
        if (itemOffer != null) {
            applyTotalWithDiscountIfApplicable(scannedItem, itemOffer);
        } else {
            double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
            scannedItem.setTotalCost(totalCost);
        }
    }

    private void applyTotalWithDiscountIfApplicable(ScannedItem scannedItem, Offer itemOffer) {
        if (scannedItem.getQuantity() < itemOffer.getMultiplier()) {
            double totalCost = scannedItem.getQuantity() * scannedItem.getPrice();
            scannedItem.setTotalCost(totalCost);
        } else {
            scannedItem.setTotalCost(computeTotalCost(scannedItem, itemOffer));
            scannedItem.setDiscounted(true);
        }
    }

    private double computeTotalCost(ScannedItem scannedItem, Offer itemOffer) {
        double offerCost = ((double) (scannedItem.getQuantity() / itemOffer.getMultiplier())) * itemOffer.getOfferPrice();
        double nonOfferCost = (scannedItem.getQuantity() % itemOffer.getMultiplier()) * scannedItem.getPrice();
        return offerCost + nonOfferCost;
    }

}
