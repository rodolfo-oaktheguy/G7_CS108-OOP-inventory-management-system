package model;

public class ActionFigure extends Product {
    private String brand;

    public ActionFigure(String id, String name, double unitPrice, int quantity, int reorderLevel, String brand) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setBrand(brand);
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        if (brand == null || brand.trim().isEmpty()) {
            throw new IllegalArgumentException("Brand cannot be empty.");
        }
        this.brand = brand.trim();
    }

    @Override
    public String getCategory() {
        return "Action Figure";
    }

    @Override
    public String getCategoryDetails() {
        return "Brand: " + brand;
    }

    @Override
    public String getSpecificFieldValue() {
        return brand;
    }

    @Override
    public void setSpecificFieldValue(String value) {
        setBrand(value);
    }
}
