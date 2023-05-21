package database.model;

import java.sql.Date;

public class OrderLine {

    private final int orderLineId;
    private int orderId;
    private int stockItemId;
    private String description;
    private int packageTypeId;
    private int quantity;
    private float unitPrice;
    private float taxRate;
    private int pickedQuantity;
    private Date pickingCompletedWhen;
    private int lastEditedBy;
    private Date lastEditedWhen;

    public OrderLine(int orderLineId, int orderId, int stockItemId, String description){
        this.orderLineId = orderLineId;
        this.orderId     = orderId;
        this.stockItemId = stockItemId;
        this.description = description;
    }
    public OrderLine(int orderLineId, int orderId, int stockItemId, String description, int quantity){
        this.orderLineId = orderLineId;
        this.orderId     = orderId;
        this.stockItemId = stockItemId;
        this.description = description;
        this.quantity = quantity;
    }

    public OrderLine(int orderLineId, int orderId, int stockItemId, String description, int packageTypeId, int quantity,
                     float unitPrice, float taxRate, int pickedQuantity, Date pickingCompletedWhen, int lastEditedBy,
                     Date lastEditedWhen) {
        this.orderLineId = orderLineId;
        this.orderId = orderId;
        this.stockItemId = stockItemId;
        this.description = description;
        this.packageTypeId = packageTypeId;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.taxRate = taxRate;
        this.pickedQuantity = pickedQuantity;
        this.pickingCompletedWhen = pickingCompletedWhen;
        this.lastEditedBy = lastEditedBy;
        this.lastEditedWhen = lastEditedWhen;
    }

    public int getOrderLineId() {
        return orderLineId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getStockItemId() {
        return stockItemId;
    }

    public void setStockItemId(int stockItemId) {
        this.stockItemId = stockItemId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPackageTypeId() {
        return packageTypeId;
    }

    public void setPackageTypeId(int packageTypeId) {
        this.packageTypeId = packageTypeId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public float getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(float taxRate) {
        this.taxRate = taxRate;
    }

    public int getPickedQuantity() {
        return pickedQuantity;
    }

    public void setPickedQuantity(int pickedQuantity) {
        this.pickedQuantity = pickedQuantity;
    }

    public Date getPickingCompletedWhen() {
        return pickingCompletedWhen;
    }

    public void setPickingCompletedWhen(Date pickingCompletedWhen) {
        this.pickingCompletedWhen = pickingCompletedWhen;
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
        return "OrderLine{" +
                "orderLineId=" + orderLineId +
                ", orderId=" + orderId +
                ", stockItemId=" + stockItemId +
                ", description='" + description + '\'' +
                ", packageTypeId=" + packageTypeId +
                ", quantity=" + quantity +
                ", unitPrice=" + unitPrice +
                ", taxRate=" + taxRate +
                ", pickedQuantity=" + pickedQuantity +
                ", pickingCompletedWhen=" + pickingCompletedWhen +
                ", lastEditedBy=" + lastEditedBy +
                ", lastEditedWhen=" + lastEditedWhen +
                '}';
    }
}
