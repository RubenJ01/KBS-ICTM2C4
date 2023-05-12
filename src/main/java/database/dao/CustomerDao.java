package database.dao;

import database.model.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDao {

    private static CustomerDao instance = null;

    private CustomerDao() {}

    public static CustomerDao getInstance() {
        if (instance == null) {
            instance = new CustomerDao();
        }
        return instance;
    }

    public List<Customer> getAllCustomers(Connection con) throws SQLException {
        String query = "SELECT * FROM customers";
        List<Customer> customers = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    customers.add(new Customer(
                            rs.getInt("CustomerID"),
                            rs.getString("CustomerName"),
                            rs.getInt("BillToCustomerID"),
                            rs.getInt("CustomerCategoryID"),
                            rs.getInt("BuyingGroupID"),
                            rs.getInt("PrimaryContactPersonID"),
                            rs.getInt("AlternateContactPersonID"),
                            rs.getInt("DeliveryMethodID"),
                            rs.getInt("DeliveryCityID"),
                            rs.getInt("PostalCityID"),
                            rs.getDouble("CreditLimit"),
                            rs.getDate("AccountOpenedDate"),
                            rs.getDouble("StandardDiscountPercentage"),
                            rs.getInt("IsStatementSent"),
                            rs.getInt("IsOnCreditHold"),
                            rs.getInt("PaymentDays"),
                            rs.getString("PhoneNumber"),
                            rs.getString("FaxNumber"),
                            rs.getString("DeliveryRun"),
                            rs.getString("RunPosition"),
                            rs.getString("WebsiteURL"),
                            rs.getString("DeliveryAddressLine1"),
                            rs.getString("DeliveryAddressLine2"),
                            rs.getString("DeliveryPostalCode"),
                            rs.getString("PostalAddressLine1"),
                            rs.getString("PostalAddressLine2"),
                            rs.getString("PostalPostalCode"),
                            rs.getString("LastEditedBy"),
                            rs.getDate("ValidFrom"),
                            rs.getDate("ValidTo")
                            ));
                }
            }
        }
        return customers;
    }

}
