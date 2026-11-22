package model;
public abstract class Product {
    private String id;
    private String name;
    private double unitPrice;
    private int quantity;
    private int reorderLevel;

    public Product(String id, String name, double unitPrice, int quantity, int reorderLevel) {
        setId(id);
        setName(name);
        setUnitPrice(unitPrice);
        setQuantity(quantity);
        setReorderLevel(reorderLevel);
    }

    // Abstract methods for polymorphism
    public abstract String getCategory();
    public abstract String getCategoryDetails();
    public abstract String getSpecificFieldValue();
    public abstract void setSpecificFieldValue(String value);

    // Shared business logic
    public double getInventoryValue() {
        return unitPrice * quantity;
    }

    public boolean isLowStock() {
        return quantity <= reorderLevel;
    }

    public void addStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Stock-in amount must be greater than zero.");
        }
        this.quantity += amount;
    }

    public void removeStock(int amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Stock-out amount must be greater than zero.");
        }
        if (amount > this.quantity) {
            throw new IllegalArgumentException("Insufficient stock. Available: " + this.quantity);
        }
        this.quantity -= amount;
    }

    // Helper for CSV File Persistence (+6 bonus)
    public String toCsvRow() {
        return String.join(",",
                getCategory(),
                escapeCsv(id),
                escapeCsv(name),
                String.valueOf(unitPrice),
                String.valueOf(quantity),
                String.valueOf(reorderLevel),
                escapeCsv(getSpecificFieldValue())
        );
    }

    protected String escapeCsv(String value) {
        if (value == null) return "";
        return value.replace(",", ";");
    }

    // Getters and Validated Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        this.id = id.trim().toUpperCase();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Product name cannot be empty.");
        }
        this.name = name.trim();
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        if (unitPrice < 0) {
            throw new IllegalArgumentException("Unit price cannot be negative.");
        }
        this.unitPrice = unitPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity cannot be negative.");
        }
        this.quantity = quantity;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        if (reorderLevel < 0) {
            throw new IllegalArgumentException("Reorder level cannot be negative.");
        }
        this.reorderLevel = reorderLevel;
    }
}
