package database.model;

public class StockItem {

    private final Integer stockItemID;
    private final String stockItemName;
    private final StockItemHolding stockitemholdings;

    public StockItem(Integer stockItemID, String stockItemName, StockItemHolding stockitemholdings) {
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

    public StockItemHolding getStockitemholdings() {
        return stockitemholdings;
    }

    @Override
    public String toString() {
        return this.stockItemName;
    }
}
