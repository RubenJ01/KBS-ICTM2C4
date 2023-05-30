package database.model;

import java.sql.Date;

public class StockItemHolding {
    private final int stockItemID;
    private int quantityOnHand;
    private String binLocation;
    private int lastStocktakeQuantity;
    private double lastCostPrice;
    private int reorderLevel;
    private int targetStockLevel;
    private int lastEditedBy;
    private Date lastEditedWhen;

    public StockItemHolding(int stockItemID, int quantityOnHand) {
        this.stockItemID = stockItemID;
        this.quantityOnHand = quantityOnHand;

    }

    public int getStockItemID() {
        return stockItemID;
    }

    public int getQuantityOnHand() {
        return quantityOnHand;
    }

    public void setQuantityOnHand(int quantityOnHand) {
        this.quantityOnHand = quantityOnHand;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public int getLastStocktakeQuantity() {
        return lastStocktakeQuantity;
    }

    public void setLastStocktakeQuantity(int lastStocktakeQuantity) {
        this.lastStocktakeQuantity = lastStocktakeQuantity;
    }

    public double getLastCostPrice() {
        return lastCostPrice;
    }

    public void setLastCostPrice(double lastCostPrice) {
        this.lastCostPrice = lastCostPrice;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public int getTargetStockLevel() {
        return targetStockLevel;
    }

    public void setTargetStockLevel(int targetStockLevel) {
        this.targetStockLevel = targetStockLevel;
    }

    public int getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(int lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public Date getLastEditedWhen() {
        return lastEditedWhen;
    }

    public void setLastEditedWhen(Date lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    @Override
    public String toString() {
        return "Stockitemholdings{" +
                "stockItemID=" + stockItemID +
                ", quantityOnHand=" + quantityOnHand +
                ", binLocation='" + binLocation + '\'' +
                ", lastStocktakeQuantity=" + lastStocktakeQuantity +
                ", lastCostPrice=" + lastCostPrice +
                ", reorderLevel=" + reorderLevel +
                ", targetStockLevel=" + targetStockLevel +
                ", lastEditedBy=" + lastEditedBy +
                ", lastEditedWhen=" + lastEditedWhen +
                '}';
    }
}
