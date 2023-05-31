package gui.controller;

import database.dao.OrderDao;
import database.model.Customer;
import database.model.Order;
import database.model.Person;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import org.jdatepicker.impl.JDatePickerImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AddOrderController {

    private static final Logger logger = LoggerFactory.getLogger(AddOrderController.class);
    private final OrderDao orderDao = OrderDao.getInstance();
    private final JLabel totalOrders;
    private final JLabel currentVisibleOrders;
    private final JDialog addOrderDialog;
    private final DefaultListModel<Order> orderListModel;

    public AddOrderController(JLabel totalOrders, JDialog addOrderDialog, DefaultListModel<Order> orderListModel,
                              JLabel currentVisibleOrders) {
        this.totalOrders = totalOrders;
        this.currentVisibleOrders = currentVisibleOrders;
        this.addOrderDialog = addOrderDialog;
        this.orderListModel = orderListModel;
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
        // check if every field has been filled in
        if (klantIdField.getSelectedItem() == null || salesPersonIdField.getSelectedItem() == null ||
                contactPersonIdField.getSelectedItem() == null || orderDateField.getJFormattedTextField().getText().isEmpty() ||
                expectedDeliveryDateField.getJFormattedTextField().getText().isEmpty()) {
            JOptionPane.showMessageDialog(addOrderDialog, "Vul alle velden in!", "Fout", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try(Connection con = DatabaseConnection.getConnection()) {
            con.setAutoCommit(false);
            int newestOrderId = orderDao.getNewestOrder(con, RowLockType.FOR_UPDATE).getOrderId();
            Order order = new Order();
            Customer customer = (Customer) klantIdField.getSelectedItem();
            order.setCustomerId(customer.getCustomerID());
            Person salesPerson = (Person) salesPersonIdField.getSelectedItem();
            order.setSalespersonPersonId(salesPerson.getPersonID());
            Person contactPerson = (Person) contactPersonIdField.getSelectedItem();
            order.setContactPersonId(contactPerson.getPersonID());
            order.setOrderId(newestOrderId + 1);
            order.setOrderDate(Date.valueOf(orderDateField.getJFormattedTextField().getText()));
            order.setExpectedDeliveryDate(Date.valueOf(expectedDeliveryDateField.getJFormattedTextField().getText()));
            order.setIsUndersupplyBackordered(isUndersupplyBackorderedField.isSelected() ? 1 : 0);
            order.setLastEditedBy(salesPerson.getPersonID());
            order.setLastEditedWhen(new Date(System.currentTimeMillis()));
            orderDao.addOrder(con, order);
            // update the total order count
            String formattedString = this.totalOrders.getText();
            Pattern pattern = Pattern.compile("\\d+");
            Matcher matcher = pattern.matcher(formattedString);
            if (matcher.find()) {
                String digits = matcher.group();
                int orderCount = Integer.parseInt(digits);
                this.totalOrders.setText(String.format("Totaal aantal orders: %d", ++orderCount));
            }
            this.currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", this.orderListModel.size() + 1));
            this.orderListModel.addElement(order);
            this.addOrderDialog.dispose();
            con.commit();
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }
}
