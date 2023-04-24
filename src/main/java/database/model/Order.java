package database.model;

import java.sql.Date;
import java.time.Instant;

public class Order {

    private final int orderId;
    private int customerId;
    private int salespersonPersonId;
    private int pickedByPersonId;
    private int contactPersonId;
    private int backorderOrderId;
    private Date orderDate;
    private Date expectedDeliveryDate;
    private String customerPurchaseOrderNumber;
    private int isUndersupplyBackordered;
    private String comments;
    private String deliveryInstructions;
    private String internalComments;
    private Date pickingCompletedWhen;
    private int lastEditedBy;
    private Date lastEditedWhen;

    public Order(int orderId, int customerId, int salespersonPersonId, int pickedByPersonId, int contactPersonId,
                 int backorderOrderId, Date orderDate, Date expectedDeliveryDate, String customerPurchaseOrderNumber,
                 int isUndersupplyBackordered, String comments, String deliveryInstructions, String internalComments,
                 Date pickingCompletedWhen, int lastEditedBy, Date lastEditedWhen) {
        this.orderId = orderId;
        this.customerId = customerId;
        this.salespersonPersonId = salespersonPersonId;
        this.pickedByPersonId = pickedByPersonId;
        this.contactPersonId = contactPersonId;
        this.backorderOrderId = backorderOrderId;
        this.orderDate = orderDate;
        this.expectedDeliveryDate = expectedDeliveryDate;
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
        this.isUndersupplyBackordered = isUndersupplyBackordered;
        this.comments = comments;
        this.deliveryInstructions = deliveryInstructions;
        this.internalComments = internalComments;
        this.pickingCompletedWhen = pickingCompletedWhen;
        this.lastEditedBy = lastEditedBy;
        this.lastEditedWhen = lastEditedWhen;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getSalespersonPersonId() {
        return salespersonPersonId;
    }

    public int getPickedByPersonId() {
        return pickedByPersonId;
    }

    public int getContactPersonId() {
        return contactPersonId;
    }

    public int getBackorderOrderId() {
        return backorderOrderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public String getCustomerPurchaseOrderNumber() {
        return customerPurchaseOrderNumber;
    }

    public int getIsUndersupplyBackordered() {
        return isUndersupplyBackordered;
    }

    public String getComments() {
        return comments;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public String getInternalComments() {
        return internalComments;
    }

    public Date getPickingCompletedWhen() {
        return pickingCompletedWhen;
    }

    public int getLastEditedBy() {
        return lastEditedBy;
    }

    public Date getLastEditedWhen() {
        return lastEditedWhen;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setSalespersonPersonId(int salespersonPersonId) {
        this.salespersonPersonId = salespersonPersonId;
    }

    public void setPickedByPersonId(int pickedByPersonId) {
        this.pickedByPersonId = pickedByPersonId;
    }

    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public void setBackorderOrderId(int backorderOrderId) {
        this.backorderOrderId = backorderOrderId;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public void setCustomerPurchaseOrderNumber(String customerPurchaseOrderNumber) {
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
    }

    public void setIsUndersupplyBackordered(int isUndersupplyBackordered) {
        this.isUndersupplyBackordered = isUndersupplyBackordered;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public void setInternalComments(String internalComments) {
        this.internalComments = internalComments;
    }

    public void setPickingCompletedWhen(Date pickingCompletedWhen) {
        this.pickingCompletedWhen = pickingCompletedWhen;
    }

    public void setLastEditedBy(int lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public void setLastEditedWhen(Date lastEditedWhen) {
        this.lastEditedWhen = lastEditedWhen;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", salespersonPersonId=" + salespersonPersonId +
                ", pickedByPersonId=" + pickedByPersonId +
                ", contactPersonId=" + contactPersonId +
                ", backorderOrderId=" + backorderOrderId +
                ", orderDate=" + orderDate +
                ", expectedDeliveryDate=" + expectedDeliveryDate +
                ", customerPurchaseOrderNumber='" + customerPurchaseOrderNumber + '\'' +
                ", isUndersupplyBackordered=" + isUndersupplyBackordered +
                ", comments='" + comments + '\'' +
                ", deliveryInstructions='" + deliveryInstructions + '\'' +
                ", internalComments='" + internalComments + '\'' +
                ", pickingCompletedWhen=" + pickingCompletedWhen +
                ", lastEditedBy=" + lastEditedBy +
                ", lastEditedWhen=" + lastEditedWhen +
                '}';
    }
}
