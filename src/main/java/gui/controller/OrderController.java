package gui.controller;

import database.dao.OrderDao;
import database.model.Order;
import database.model.OrderLine;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import gui.view.OrderView;
import gui.view.dialog.AddOrderDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

public class OrderController {

    private final CardLayout layout;
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final JPanel root;
    private final JDialog addOrderDialog;
    private OrderDao orderDao;


    public void listSelected(ListSelectionEvent e, JList<Order> orderList, JPanel singleOrder) {
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedOrder = orderList.getSelectedValue();
            singleOrder.removeAll();
            singleOrder.setLayout(new BoxLayout(singleOrder, BoxLayout.Y_AXIS));
            singleOrder.add(new JLabel("Producten in deze order: "));
            for (OrderLine orderLine : selectedOrder.getOrderLines()) {
                if (orderLine.getOrderId() == selectedOrder.getOrderId()) {
                    JPanel itemPanel = new JPanel();
                    itemPanel.setLayout(new GridLayout(3,1));
                    itemPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    itemPanel.add(new JLabel("Product ID:"));
                    itemPanel.add(new JLabel(String.valueOf(orderLine.getStockItemId())));
                    itemPanel.add(new JLabel("Productomschrijving: "));
                    itemPanel.add(new JLabel(String.valueOf(orderLine.getDescription())));
                    itemPanel.add(new JLabel("Hoeveelheid: "));
                    itemPanel.add(new JLabel(String.valueOf(orderLine.getQuantity())));

                    singleOrder.add(itemPanel);
                }
            }

            singleOrder.revalidate();
            singleOrder.repaint();
        }
    }


    public OrderController(CardLayout layout, JPanel root) {
        this.layout = layout;
        this.root = root;
        this.addOrderDialog = new AddOrderDialog();
    }

    public void editButton(ActionEvent e) {

    }

    public void addButton(ActionEvent e) {
        if (!this.addOrderDialog.isActive()) {
            this.addOrderDialog.setVisible(true);
        }
    }
    public void viewOrderButton(ActionEvent e){

    }

    /**
     * Handles searching for a specific order id in the list of orders.
     *
     * @param e         the actionevent.
     * @param orderList the JList with all the orders.
     * @param allOrders the list with all the order objects.
     * @see Order
     */
    public void searchTextField(ActionEvent e, JList<Order> orderList, List<Order> allOrders) {
        orderList.getSelectedIndex();
        JTextField textField = (JTextField) e.getSource();
        try {
            int orderId = Integer.parseInt(textField.getText());
            Optional<Integer> listIndex = Optional.empty();
            for (int i = 0; i < allOrders.size(); i++) {
                if (allOrders.get(i).getOrderId() == orderId) {
                    listIndex = Optional.of(i);
                }
            }
            listIndex.ifPresentOrElse(listIndex1 -> {
                orderList.ensureIndexIsVisible(listIndex1);
                orderList.setSelectedIndex(listIndex1);
                textField.setBackground(Color.GREEN);
            }, () -> textField.setBackground(Color.RED));
        } catch (NumberFormatException exc) {
            textField.setBackground(Color.RED);
        }
    }
}
