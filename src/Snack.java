public class Snack extends Product {
    private double weightGrams;

    public Snack(String id, String name, double unitPrice, int quantity, int reorderLevel, double weightGrams) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setWeightGrams(weightGrams);
    }

    public double getWeightGrams() {
        return weightGrams;
    }

    public void setWeightGrams(double weightGrams) {
        if (weightGrams <= 0) {
            throw new IllegalArgumentException("Weight in grams must be greater than zero.");
        }
        this.weightGrams = weightGrams;
    }

    @Override
    public String getCategory() {
        return "Snack";
    }

    @Override
    public String getCategoryDetails() {
        return String.format("Net Wt: %.1fg", weightGrams);
    }

    @Override
    public String getSpecificFieldValue() {
        return String.valueOf(weightGrams);
    }
}
