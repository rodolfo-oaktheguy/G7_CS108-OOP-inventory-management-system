package inventory;

import java.util.ArrayList;
import model.Product;
import model.StockTransaction;

public class Inventory {
    private ArrayList<Product> products; //holds all product types
    private ArrayList<StockTransaction> transactions; //second list for history of every add, update, delete, stock in and stock out

    public Inventory() {
        products = new ArrayList<>();
        transactions = new ArrayList<>();
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getProductCount() {
        return products.size();
    }
}
