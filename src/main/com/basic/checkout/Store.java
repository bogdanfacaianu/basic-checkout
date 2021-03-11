package main.com.basic.checkout;


import java.util.Collection;
import main.com.basic.checkout.checkout.Basket;
import main.com.basic.checkout.stock.Sku;
import main.com.basic.checkout.stock.Stock;

public class Store {

    private static Store instance;
    private Stock stock;
    private Basket basket;

    private Store() {
        this.stock = Stock.initialise();
        this.basket = new Basket();
    }

    public static Store openShop() {
        return instance == null ? new Store() : instance;
    }

    public void loadStock(Collection<Sku> skus) {
        skus.forEach(stock::addSku);
    }
}
