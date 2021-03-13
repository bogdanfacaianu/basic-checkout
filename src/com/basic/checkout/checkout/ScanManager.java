package com.basic.checkout.checkout;

import com.basic.checkout.stock.Offer;
import com.basic.checkout.stock.StockItem;
import java.util.Comparator;
import java.util.Optional;
import java.util.Set;

public class ScanManager {

    public Optional<ScannedItem> buildUpdatedScannedItem(StockItem stockItem, ScannedItem previousScan) {
        Set<Offer> stockItemOffers = stockItem.getOffers();
        ScannedItem scannedItem = new ScannedItem(stockItem.getSkuId(), stockItem.getPrice());

        int newQuantity = previousScan == null ? 1 : previousScan.getQuantity() + 1;
        scannedItem.setQuantity(newQuantity);

        updateTotalCost(scannedItem, stockItemOffers);
        return Optional.of(scannedItem);
    }

    private void updateTotalCost(ScannedItem scannedItem, Set<Offer> itemOffers) {
        Offer bestOfferFound = determineBestOfferForSkuScanned(itemOffers, scannedItem.getQuantity());
        if (bestOfferFound != null) {
            applyTotalWithDiscountIfApplicable(scannedItem, bestOfferFound);
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

    private Offer determineBestOfferForSkuScanned(Set<Offer> offers, int quantity) {
        return offers.stream()
            .filter(offer -> offer.getMultiplier() <= quantity)
            .max(Comparator.comparing(Offer::getMultiplier))
            .orElse(null);
    }
}
