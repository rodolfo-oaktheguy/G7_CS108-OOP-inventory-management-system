package inventory;

import java.util.ArrayList;
import java.util.LinkedHashMap;

import model.ActionFigure;
import model.BoardGame;
import model.BuildingSet;
import model.Product;
import model.Snack;
import model.StockTransaction;

public class Inventory {
    private ArrayList<Product> products;
    private ArrayList<StockTransaction> transactions;

    public Inventory() {
        products = new ArrayList<>();
        transactions = new ArrayList<>();
        loadSampleData();
    }

    private void loadSampleData() {
        products.add(new ActionFigure("AF001", "Spider-Man Titan Hero", 799.00, 20, 5, "Hasbro"));
        products.add(new ActionFigure("AF002", "Optimus Prime Deluxe", 1499.00, 8, 3, "Hasbro"));
        products.add(new ActionFigure("AF003", "Goku Super Saiyan Figure", 999.00, 4, 5, "Bandai"));

        products.add(new BoardGame("BG001", "Monopoly Classic", 1299.00, 12, 4, 2));
        products.add(new BoardGame("BG002", "Scrabble", 999.00, 6, 3, 2));
        products.add(new BoardGame("BG003", "Uno Card Game", 249.00, 30, 10, 2));

        products.add(new BuildingSet("BS001", "LEGO City Fire Station", 3999.00, 5, 2, 540));
        products.add(new BuildingSet("BS002", "LEGO Technic Race Car", 2499.00, 2, 3, 320));
        products.add(new BuildingSet("BS003", "LEGO Classic Brick Box", 1799.00, 15, 5, 790));

        products.add(new Snack("SN001", "Gummy Bears Pack", 45.00, 50, 15, 80));
        products.add(new Snack("SN002", "Chocolate Bar", 60.00, 10, 12, 45));
    }

    public ArrayList<Product> getProducts() {
        return new ArrayList<>(products);
    }

    public int getProductCount() {
        return products.size();
    }

    public ArrayList<StockTransaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    private void recordTransaction(String type, String productId, int quantity) {
        transactions.add(new StockTransaction(type, productId, quantity));
    }

    public Product findById(String id) {
        if (id == null) {
            return null;
        }
        for (Product p : products) {
            if (p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }
        return null;
    }

    public ArrayList<Product> searchByName(String keyword) {
        ArrayList<Product> results = new ArrayList<>();
        if (keyword == null) {
            return results;
        }
        String search = keyword.trim().toLowerCase();
        for (Product p : products) {
            if (p.getName().toLowerCase().contains(search)) {
                results.add(p);
            }
        }
        return results;
    }

    public boolean addProduct(Product product) {
        if (product == null || findById(product.getId()) != null) {
            return false;
        }
        products.add(product);
        recordTransaction("ADD", product.getId(), product.getQuantity());
        return true;
    }

    public Product updateProduct(String id, String newName, double newPrice, int newReorderLevel, String newSpecificValue) {
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        p.setSpecificFieldValue(newSpecificValue);
        p.setName(newName);
        p.setUnitPrice(newPrice);
        p.setReorderLevel(newReorderLevel);
        recordTransaction("UPDATE", p.getId(), 0);
        return p;
    }

    public boolean deleteProduct(String id) {
        Product p = findById(id);
        if (p == null) {
            return false;
        }
        products.remove(p);
        recordTransaction("DELETE", p.getId(), p.getQuantity());
        return true;
    }

    public void stockIn(String id, int amount) {
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        p.addStock(amount);
        recordTransaction("STOCK_IN", p.getId(), amount);
    }

    public boolean stockOut(String id, int amount) {
        Product p = findById(id);
        if (p == null) {
            throw new IllegalArgumentException("Product not found: " + id);
        }
        p.removeStock(amount);
        recordTransaction("STOCK_OUT", p.getId(), amount);
        return p.isLowStock();
    }

    public ArrayList<Product> getLowStock() {
        ArrayList<Product> low = new ArrayList<>();
        for (Product p : products) {
            if (p.isLowStock()) {
                low.add(p);
            }
        }
        return low;
    }

    public void sortProducts(String field, boolean ascending) {
        for (int i = 0; i < products.size() - 1; i++) {
            for (int j = 0; j < products.size() - 1 - i; j++) {
                Product a = products.get(j);
                Product b = products.get(j + 1);
                int result = compareProducts(a, b, field);
                if (!ascending) {
                    result = -result;
                }
                if (result > 0) {
                    products.set(j, b);
                    products.set(j + 1, a);
                }
            }
        }
    }

    private int compareProducts(Product a, Product b, String field) {
        switch (field.toLowerCase()) {
            case "name":
                return a.getName().compareToIgnoreCase(b.getName());
            case "price":
                return Double.compare(a.getUnitPrice(), b.getUnitPrice());
            case "quantity":
                return Integer.compare(a.getQuantity(), b.getQuantity());
            default:
                throw new IllegalArgumentException("Unknown sort field: " + field);
        }
    }

    public double getTotalValueRecursive(int index) {
        if (index >= products.size()) {
            return 0;
        }
        return products.get(index).getInventoryValue() + getTotalValueRecursive(index + 1);
    }

    public double getTotalValue() {
        return getTotalValueRecursive(0);
    }

    public int getTotalUnits() {
        int total = 0;
        for (Product p : products) {
            total += p.getQuantity();
        }
        return total;
    }

    public String getSummaryReport() {
        LinkedHashMap<String, Integer> countPerCategory = new LinkedHashMap<>();
        LinkedHashMap<String, Double> valuePerCategory = new LinkedHashMap<>();

        for (Product p : products) {
            String category = p.getCategory();
            countPerCategory.put(category, countPerCategory.getOrDefault(category, 0) + 1);
            valuePerCategory.put(category, valuePerCategory.getOrDefault(category, 0.0) + p.getInventoryValue());
        }

        String report = "===== SUMMARY REPORT =====\n";
        report += String.format("Total products : %d%n", getProductCount());
        report += String.format("Total units    : %d%n", getTotalUnits());
        report += String.format("Total value    : PHP %,.2f%n", getTotalValue());

        report += "\n--- Per Category ---\n";
        for (String category : countPerCategory.keySet()) {
            report += String.format("%-14s : %2d products | PHP %,12.2f%n",
                    category, countPerCategory.get(category), valuePerCategory.get(category));
        }

        report += "\n--- Low Stock ---\n";
        ArrayList<Product> low = getLowStock();
        if (low.isEmpty()) {
            report += "None.\n";
        }
        for (Product p : low) {
            report += String.format("%-6s %-26s qty %3d (reorder at %d)%n",
                    p.getId(), p.getName(), p.getQuantity(), p.getReorderLevel());
        }
        return report;
    }
}
