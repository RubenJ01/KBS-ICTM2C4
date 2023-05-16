package database.model;

import java.sql.Date;

public class Customer {

    private int customerID;
    private String customerName;
    private int billToCustomerID;
    private int customerCategoryID;
    private int buyingGroupID;
    private int primaryContactPersonID;
    private int alternateContactPersonID;
    private int deliveryMethodID;
    private int deliveryCityID;
    private int postalCityID;
    private double creditLimit;
    private Date accountOpenedDate;
    private double standardDiscountPercentage;
    private int isStatementSent;
    private int isOnCreditHold;
    private int paymentDays;
    private String phoneNumber;
    private String faxNumber;
    private String deliveryRun;
    private String runPosition;
    private String websiteURL;
    private String deliveryAddressLine1;
    private String deliveryAddressLine2;
    private String deliveryPostalCode;
    private String postalAddressLine1;
    private String postalAddressLine2;
    private String postalPostalCode;
    private String lastEditedBy;
    private Date validFrom;
    private Date validTo;

    public Customer(int customerID, String customerName, int billToCustomerID, int customerCategoryID, int buyingGroupID,
                    int primaryContactPersonID, int alternateContactPersonID, int deliveryMethodID, int deliveryCityID,
                    int postalCityID, double creditLimit, Date accountOpenedDate, double standardDiscountPercentage,
                    int isStatementSent, int isOnCreditHold, int paymentDays, String phoneNumber, String faxNumber,
                    String deliveryRun, String runPosition, String websiteURL, String deliveryAddressLine1,
                    String deliveryAddressLine2, String deliveryPostalCode, String postalAddressLine1,
                    String postalAddressLine2, String postalPostalCode, String lastEditedBy, Date validFrom, Date validTo) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.billToCustomerID = billToCustomerID;
        this.customerCategoryID = customerCategoryID;
        this.buyingGroupID = buyingGroupID;
        this.primaryContactPersonID = primaryContactPersonID;
        this.alternateContactPersonID = alternateContactPersonID;
        this.deliveryMethodID = deliveryMethodID;
        this.deliveryCityID = deliveryCityID;
        this.postalCityID = postalCityID;
        this.creditLimit = creditLimit;
        this.accountOpenedDate = accountOpenedDate;
        this.standardDiscountPercentage = standardDiscountPercentage;
        this.isStatementSent = isStatementSent;
        this.isOnCreditHold = isOnCreditHold;
        this.paymentDays = paymentDays;
        this.phoneNumber = phoneNumber;
        this.faxNumber = faxNumber;
        this.deliveryRun = deliveryRun;
        this.runPosition = runPosition;
        this.websiteURL = websiteURL;
        this.deliveryAddressLine1 = deliveryAddressLine1;
        this.deliveryAddressLine2 = deliveryAddressLine2;
        this.deliveryPostalCode = deliveryPostalCode;
        this.postalAddressLine1 = postalAddressLine1;
        this.postalAddressLine2 = postalAddressLine2;
        this.postalPostalCode = postalPostalCode;
        this.lastEditedBy = lastEditedBy;
        this.validFrom = validFrom;
        this.validTo = validTo;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getBillToCustomerID() {
        return billToCustomerID;
    }

    public void setBillToCustomerID(int billToCustomerID) {
        this.billToCustomerID = billToCustomerID;
    }

    public int getCustomerCategoryID() {
        return customerCategoryID;
    }

    public void setCustomerCategoryID(int customerCategoryID) {
        this.customerCategoryID = customerCategoryID;
    }

    public int getBuyingGroupID() {
        return buyingGroupID;
    }

    public void setBuyingGroupID(int buyingGroupID) {
        this.buyingGroupID = buyingGroupID;
    }

    public int getPrimaryContactPersonID() {
        return primaryContactPersonID;
    }

    public void setPrimaryContactPersonID(int primaryContactPersonID) {
        this.primaryContactPersonID = primaryContactPersonID;
    }

    public int getAlternateContactPersonID() {
        return alternateContactPersonID;
    }

    public void setAlternateContactPersonID(int alternateContactPersonID) {
        this.alternateContactPersonID = alternateContactPersonID;
    }

    public int getDeliveryMethodID() {
        return deliveryMethodID;
    }

    public void setDeliveryMethodID(int deliveryMethodID) {
        this.deliveryMethodID = deliveryMethodID;
    }

    public int getDeliveryCityID() {
        return deliveryCityID;
    }

    public void setDeliveryCityID(int deliveryCityID) {
        this.deliveryCityID = deliveryCityID;
    }

    public int getPostalCityID() {
        return postalCityID;
    }

    public void setPostalCityID(int postalCityID) {
        this.postalCityID = postalCityID;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Date getAccountOpenedDate() {
        return accountOpenedDate;
    }

    public void setAccountOpenedDate(Date accountOpenedDate) {
        this.accountOpenedDate = accountOpenedDate;
    }

    public double getStandardDiscountPercentage() {
        return standardDiscountPercentage;
    }

    public void setStandardDiscountPercentage(double standardDiscountPercentage) {
        this.standardDiscountPercentage = standardDiscountPercentage;
    }

    public int getIsStatementSent() {
        return isStatementSent;
    }

    public void setIsStatementSent(int isStatementSent) {
        this.isStatementSent = isStatementSent;
    }

    public int getIsOnCreditHold() {
        return isOnCreditHold;
    }

    public void setIsOnCreditHold(int isOnCreditHold) {
        this.isOnCreditHold = isOnCreditHold;
    }

    public int getPaymentDays() {
        return paymentDays;
    }

    public void setPaymentDays(int paymentDays) {
        this.paymentDays = paymentDays;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getFaxNumber() {
        return faxNumber;
    }

    public void setFaxNumber(String faxNumber) {
        this.faxNumber = faxNumber;
    }

    public String getDeliveryRun() {
        return deliveryRun;
    }

    public void setDeliveryRun(String deliveryRun) {
        this.deliveryRun = deliveryRun;
    }

    public String getRunPosition() {
        return runPosition;
    }

    public void setRunPosition(String runPosition) {
        this.runPosition = runPosition;
    }

    public String getWebsiteURL() {
        return websiteURL;
    }

    public void setWebsiteURL(String websiteURL) {
        this.websiteURL = websiteURL;
    }

    public String getDeliveryAddressLine1() {
        return deliveryAddressLine1;
    }

    public void setDeliveryAddressLine1(String deliveryAddressLine1) {
        this.deliveryAddressLine1 = deliveryAddressLine1;
    }

    public String getDeliveryAddressLine2() {
        return deliveryAddressLine2;
    }

    public void setDeliveryAddressLine2(String deliveryAddressLine2) {
        this.deliveryAddressLine2 = deliveryAddressLine2;
    }

    public String getDeliveryPostalCode() {
        return deliveryPostalCode;
    }

    public void setDeliveryPostalCode(String deliveryPostalCode) {
        this.deliveryPostalCode = deliveryPostalCode;
    }

    public String getPostalAddressLine1() {
        return postalAddressLine1;
    }

    public void setPostalAddressLine1(String postalAddressLine1) {
        this.postalAddressLine1 = postalAddressLine1;
    }

    public String getPostalAddressLine2() {
        return postalAddressLine2;
    }

    public void setPostalAddressLine2(String postalAddressLine2) {
        this.postalAddressLine2 = postalAddressLine2;
    }

    public String getPostalPostalCode() {
        return postalPostalCode;
    }

    public void setPostalPostalCode(String postalPostalCode) {
        this.postalPostalCode = postalPostalCode;
    }

    public String getLastEditedBy() {
        return lastEditedBy;
    }

    public void setLastEditedBy(String lastEditedBy) {
        this.lastEditedBy = lastEditedBy;
    }

    public Date getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(Date validFrom) {
        this.validFrom = validFrom;
    }

    public Date getValidTo() {
        return validTo;
    }

    public void setValidTo(Date validTo) {
        this.validTo = validTo;
    }

    @Override
    public String toString() {
        return this.customerName;
    }
}
