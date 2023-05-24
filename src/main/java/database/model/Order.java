package database.model;

import ch.qos.logback.core.net.server.AbstractServerSocketAppender;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private int orderId;
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
    private List<OrderLine> orderLines;

    public Order(int orderId, int customerId, int salespersonPersonId, int pickedByPersonId, int contactPersonId,
                 int backorderOrderId, Date orderDate, Date expectedDeliveryDate, String customerPurchaseOrderNumber,
                 int isUndersupplyBackordered, String comments, String deliveryInstructions, String internalComments,
                 Date pickingCompletedWhen, int lastEditedBy, Date lastEditedWhen, List<OrderLine> orderLines) {
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
        this.orderLines = orderLines;
    }

    public Order() {
        this.orderId = -1;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getSalespersonPersonId() {
        return salespersonPersonId;
    }

    public void setSalespersonPersonId(int salespersonPersonId) {
        this.salespersonPersonId = salespersonPersonId;
    }

    public int getPickedByPersonId() {
        return pickedByPersonId;
    }

    public void setPickedByPersonId(int pickedByPersonId) {
        this.pickedByPersonId = pickedByPersonId;
    }

    public int getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(int contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public int getBackorderOrderId() {
        return backorderOrderId;
    }

    public void setBackorderOrderId(int backorderOrderId) {
        this.backorderOrderId = backorderOrderId;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public Date getExpectedDeliveryDate() {
        return expectedDeliveryDate;
    }

    public void setExpectedDeliveryDate(Date expectedDeliveryDate) {
        this.expectedDeliveryDate = expectedDeliveryDate;
    }

    public String getCustomerPurchaseOrderNumber() {
        return customerPurchaseOrderNumber;
    }

    public void setCustomerPurchaseOrderNumber(String customerPurchaseOrderNumber) {
        this.customerPurchaseOrderNumber = customerPurchaseOrderNumber;
    }

    public int getIsUndersupplyBackordered() {
        return isUndersupplyBackordered;
    }

    public void setIsUndersupplyBackordered(int isUndersupplyBackordered) {
        this.isUndersupplyBackordered = isUndersupplyBackordered;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDeliveryInstructions() {
        return deliveryInstructions;
    }

    public void setDeliveryInstructions(String deliveryInstructions) {
        this.deliveryInstructions = deliveryInstructions;
    }

    public String getInternalComments() {
        return internalComments;
    }

    public void setInternalComments(String internalComments) {
        this.internalComments = internalComments;
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

    public boolean isPicked() {
    	return this.pickingCompletedWhen != null;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    /**
     * Copies the order and returns a new instance of the order.
     * @return a new instance of the order.
     */
    public Order copy() {
        List<OrderLine> newOrderLines = new ArrayList<>();
        for (OrderLine orderLine : orderLines) {
            newOrderLines.add(orderLine.copy());
        }
        return new Order(orderId, customerId, salespersonPersonId, pickedByPersonId, contactPersonId, backorderOrderId,
                orderDate, expectedDeliveryDate, customerPurchaseOrderNumber, isUndersupplyBackordered, comments,
                deliveryInstructions, internalComments, pickingCompletedWhen, lastEditedBy, lastEditedWhen, newOrderLines);
    }

    @Override
    public String toString() {
        return String.format("Order ID: %d - Order Date: %s", orderId, orderDate);
    }

   //added
//    public AbstractServerSocketAppender<Object> getCustomer() {
//        return customerId;
//    }
//
//    public void setCustomer(AbstractServerSocketAppender<Object> customer) {
//        this.customer = customer;
//    }
}
