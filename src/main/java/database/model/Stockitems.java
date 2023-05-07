package database.model;

public class Stockitems {
    private final Integer stockItemID;
    private final String stockItemName;
    private final Stockitemholdings stockitemholdings;

    public Stockitems(Integer stockItemID, String stockItemName, Stockitemholdings stockitemholdings) {
        this.stockItemID = stockItemID;
        this.stockItemName = stockItemName;
        this.stockitemholdings = stockitemholdings;
    }

    public Integer getStockItemID() {
        return stockItemID;
    }

    public String getStockItemName() {
        return stockItemName;
    }

    public Stockitemholdings getStockitemholdings() {
        return stockitemholdings;
    }
}
