package model;

public class HouseholdItem extends Product {
    private String usageType;

    public HouseholdItem(String id, String name, double unitPrice, int quantity, int reorderLevel, String usageType) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setUsageType(usageType);
    }

    public String getUsageType() {
        return usageType;
    }

    public void setUsageType(String usageType) {
        if (usageType == null || usageType.trim().isEmpty()) {
            throw new IllegalArgumentException("Usage type cannot be empty.");
        }
        this.usageType = usageType.trim();
    }

    @Override
    public String getCategory() {
        return "Household";
    }

    @Override
    public String getCategoryDetails() {
        return "Usage: " + usageType;
    }

    @Override
    public String getSpecificFieldValue() {
        return usageType;
    }
}
