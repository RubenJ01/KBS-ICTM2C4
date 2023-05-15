package gui.controller;

import database.model.Order;
import gui.view.OrderView;
import gui.view.dialog.AddOrderDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderController {

    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final CardLayout layout;
    private final JPanel root;
    private final JDialog addOrderDialog;

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

    /**
     * Handles searching for a specific order id in the list of orders.
     *
     * @param orderList the JList with all the orders.
     * @param allOrders the list with all the order objects.
     * @param search    the search string.
     * @see Order
     */
    public void searchTextField(JList<Order> orderList, List<Order> allOrders, String search) {
        List<Order> result = new ArrayList<>();
        try {
            int orderId = Integer.parseInt(search);
            for (Order foundOrder : allOrders) {
                if (String.valueOf(foundOrder.getOrderId()).contains(String.valueOf(orderId))) {
                    result.add(foundOrder);
                }
            }
            if (result.size() > 0) {
                orderList.setListData(result.toArray(new Order[0]));
            } else {
                orderList.setListData(allOrders.toArray(new Order[0]));
            }
        } catch (NumberFormatException ex) {
            logger.error(ex.getMessage());
        }
    }


}
