package model;

public class BoardGame extends Product {
    private int minPlayers;

    public BoardGame(String id, String name, double unitPrice, int quantity, int reorderLevel, int minPlayers) {
        super(id, name, unitPrice, quantity, reorderLevel);
        setMinPlayers(minPlayers);
    }

    public int getMinPlayers() {
        return minPlayers;
    }

    public void setMinPlayers(int minPlayers) {
        if (minPlayers <= 0) {
            throw new IllegalArgumentException("Minimum players must be at least 1.");
        }
        this.minPlayers = minPlayers;
    }

    @Override
    public String getCategory() {
        return "Board Game";
    }

    @Override
    public String getCategoryDetails() {
        return "Players: " + minPlayers + "+";
    }

    @Override
    public String getSpecificFieldValue() {
        return String.valueOf(minPlayers);
    }

    @Override
    public void setSpecificFieldValue(String value) {
        try {
            setMinPlayers(Integer.parseInt(value.trim()));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Minimum players must be a whole number.");
        }
    }
}
