package model;
public class Drink extends Product {
    private int volumeMl;

    public Drink(String id, String name, double unitPrice, int quantity, int reorderLevel, int volumeMl) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setVolumeMl(volumeMl);
    }

    public int getVolumeMl() {
        return volumeMl;
    }

    public void setVolumeMl(int volumeMl) {
        if (volumeMl <= 0) {
            throw new IllegalArgumentException("Volume in mL must be greater than zero.");
        }
        this.volumeMl = volumeMl;
    }

    @Override
    public String getCategory() {
        return "Drink";
    }

    @Override
    public String getCategoryDetails() {
        return "Volume: " + volumeMl + " mL";
    }

    @Override
    public String getSpecificFieldValue() {
        return String.valueOf(volumeMl);
    }
}
