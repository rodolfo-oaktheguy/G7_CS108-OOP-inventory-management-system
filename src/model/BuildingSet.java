package model;

public class BuildingSet extends Product {
    private int pieceCount;

    public BuildingSet(String id, String name, double unitPrice, int quantity, int reorderLevel, int pieceCount) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setPieceCount(pieceCount);
    }

    public int getPieceCount() {
        return pieceCount;
    }

    public void setPieceCount(int pieceCount) {
        if (pieceCount <= 0) {
            throw new IllegalArgumentException("Piece count must be at least 1.");
        }
        this.pieceCount = pieceCount;
    }

    @Override
    public String getCategory() {
        return "Building Set";
    }

    @Override
    public String getCategoryDetails() {
        return String.format("Pieces: %,d", pieceCount);
    }

    @Override
    public String getSpecificFieldValue() {
        return String.valueOf(pieceCount);
    }

    @Override
    public void setSpecificFieldValue(String value) {
        try {
            setPieceCount(Integer.parseInt(value.trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Piece count must be a whole number.");
        }
    }
}
