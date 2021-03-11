package main.com.basic.checkout.stock;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Stock {

    private static Stock instance;
    private Map<String, Sku> stock;

    private Stock() {
        stock = new HashMap<>();
    }

    public static Stock initialise() {
        return instance == null ? new Stock() : instance;
    }


    public void loadBulkStock(Collection<Sku> stock) {
        stock.forEach(this::addSku);
    }

    public void addSku(Sku sku) {

    }

    private Sku getSku(String skuId) {
        return null;
    }

    private void removeSku(String skuId) {

    }

    private void clearStock() {

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Stock stock1 = (Stock) o;
        return Objects.equals(stock, stock1.stock);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stock);
    }

    @Override
    public String toString() {
        return "Stock{" +
            "stock=" + stock +
            '}';
    }
}
