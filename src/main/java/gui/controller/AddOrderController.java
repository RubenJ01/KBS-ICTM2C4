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
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;

public class AddOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AddOrderController.class);
    private final OrderDao orderDao = OrderDao.getInstance();

    public AddOrderController() {

    }

    public void addOrder(ActionEvent e, JComboBox<Customer> klantIdField, JComboBox<Person> salesPersonIdField,
                         JComboBox<Person> contactPersonIdField, JDatePickerImpl orderDateField,
                         JDatePickerImpl expectedDeliveryDateField, JCheckBox isUndersupplyBackorderedField) {
        try(Connection con = DatabaseConnection.getConnection()) {
            Order order = new Order();
            Customer customer = (Customer) klantIdField.getSelectedItem();
            order.setCustomerId(customer.getCustomerID());
            Person salesPerson = (Person) salesPersonIdField.getSelectedItem();
            order.setSalespersonPersonId(salesPerson.getPersonID());

        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }
}
