package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StockTransaction {
    private static int sequenceCounter = 1;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private final int transactionId;
    private final String type;
    private final String productId;
    private final int quantityChanged;
    private final String timestamp;

    // Constructor for live operations
    public StockTransaction(String type, String productId, int quantityChanged) {
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Transaction type cannot be empty.");
        }
        if (productId == null || productId.trim().isEmpty()) {
            throw new IllegalArgumentException("Product ID cannot be empty.");
        }
        this.transactionId = sequenceCounter++;
        this.type = type.trim().toUpperCase();
        this.productId = productId.trim().toUpperCase();
        this.quantityChanged = quantityChanged;
        this.timestamp = LocalDateTime.now().format(FORMATTER);
    }

    // Overloaded constructor for loading saved transactions from CSV
    public StockTransaction(int transactionId, String type, String productId, int quantityChanged, String timestamp) {
        this.transactionId = transactionId;
        this.type = type;
        this.productId = productId;
        this.quantityChanged = quantityChanged;
        this.timestamp = timestamp;
        if (transactionId >= sequenceCounter) {
            sequenceCounter = transactionId + 1;
        }
    }

    public int getTransactionId() {
        return transactionId;
    }

    public String getType() {
        return type;
    }

    public String getProductId() {
        return productId;
    }

    public int getQuantityChanged() {
        return quantityChanged;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String toCsvRow() {
        return String.join(",",
                String.valueOf(transactionId),
                type,
                productId,
                String.valueOf(quantityChanged),
                timestamp
        );
    }

    @Override
    public String toString() {
        return String.format("#%03d | %-9s | ID: %-6s | Qty: %-4d | %s",
                transactionId, type, productId, quantityChanged, timestamp);
    }
}
