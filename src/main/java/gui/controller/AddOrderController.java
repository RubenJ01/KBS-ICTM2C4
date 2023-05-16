package gui.controller;

import database.dao.OrderDao;
import database.model.Customer;
import database.model.Order;
import database.model.Person;
import database.util.DatabaseConnection;
import org.jdatepicker.impl.JDatePickerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class AddOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AddOrderController.class);
    private final OrderDao orderDao = OrderDao.getInstance();

    public AddOrderController() {

    }

    /**
     * Adds an order to the database.
     * @param e ActionEvent from the button.
     * @param klantIdField JComboBox with the customer ID.
     * @param salesPersonIdField JComboBox with the salesperson ID.
     * @param contactPersonIdField JComboBox with the contactperson ID.
     * @param orderDateField JDatePickerImpl with the order date.
     * @param expectedDeliveryDateField JDatePickerImpl with the expected delivery date.
     * @param isUndersupplyBackorderedField JCheckBox with the undersupply backordered.
     */
    public void addOrder(ActionEvent e, JComboBox<Customer> klantIdField, JComboBox<Person> salesPersonIdField,
                         JComboBox<Person> contactPersonIdField, JDatePickerImpl orderDateField,
                         JDatePickerImpl expectedDeliveryDateField, JCheckBox isUndersupplyBackorderedField) {
        try(Connection con = DatabaseConnection.getConnection()) {
            Order order = new Order();
            Customer customer = (Customer) klantIdField.getSelectedItem();
            order.setCustomerId(customer.getCustomerID());
            Person salesPerson = (Person) salesPersonIdField.getSelectedItem();
            order.setSalespersonPersonId(salesPerson.getPersonID());
            Person contactPerson = (Person) contactPersonIdField.getSelectedItem();
            order.setContactPersonId(contactPerson.getPersonID());
            order.setOrderDate(Date.valueOf(orderDateField.getJFormattedTextField().getText()));
            order.setExpectedDeliveryDate(Date.valueOf(expectedDeliveryDateField.getJFormattedTextField().getText()));
            order.setIsUndersupplyBackordered(isUndersupplyBackorderedField.isSelected() ? 1 : 0);
            order.setLastEditedBy(salesPerson.getPersonID());
            order.setLastEditedWhen(new Date(System.currentTimeMillis()));
            orderDao.addOrder(con, order);
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }
}
