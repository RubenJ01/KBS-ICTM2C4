package gui.controller;

import database.model.Order;
import gui.view.dialog.AddOrderDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

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
     * This method is used to search for an order by order id.
     *
     * @param orderList         JList of orders.
     * @param orderListModel    DefaultListModel of orders.
     * @param search            String to search for.
     * @param filterPickedOrder JCheckBox to filter picked orders.
     * @see Order for more information about the order.
     */
    public void searchTextField(JList<Order> orderList, DefaultListModel<Order> orderListModel, String search, JCheckBox filterPickedOrder) {
        List<Order> result = new ArrayList<>();
        if (search.equals("Zoeken...") || search.equals("")) {
            for (int i = 0; i < orderListModel.getSize(); i++) {
                Order order = orderListModel.getElementAt(i);
                if (filterPickedOrder.isSelected() && order.getPickingCompletedWhen() != null) {
                    continue;
                }
                result.add(order);
            }
        } else {
            try {
                int orderId = Integer.parseInt(search);
                for (int i = 0; i < orderListModel.getSize(); i++) {
                    Order order = orderListModel.getElementAt(i);
                    if (filterPickedOrder.isSelected() && order.getPickingCompletedWhen() != null) {
                        continue;
                    }
                    if (order.getOrderId() == orderId) {
                        result.add(order);
                    }
                }
            } catch (NumberFormatException ex) {
                logger.error(ex.getMessage());
            }
        }
        orderList.setListData(result.toArray(new Order[0]));
    }


    /**
     * This method is used to filter orders by picked orders.
     *
     * @param orderList      JList of orders.
     * @param isSelected     boolean to check if the checkbox is selected.
     * @param orderListModel DefaultListModel of orders.
     * @see Order for more information about the order.
     */
    public void filterPickedOrder(JList<Order> orderList, boolean isSelected, DefaultListModel<Order> orderListModel) {
        if (isSelected) {
            List<Order> result = new ArrayList<>();
            for (int i = 0; i < orderListModel.getSize(); i++) {
                Order order = orderListModel.getElementAt(i);
                if (order.getPickingCompletedWhen() == null) {
                    result.add(order);
                }
            }
            orderList.setListData(result.toArray(new Order[0]));
        } else {
            List<Order> allOrders = new ArrayList<>();
            for (int i = 0; i < orderListModel.getSize(); i++) {
                allOrders.add(orderListModel.getElementAt(i));
            }
            orderList.setListData(allOrders.toArray(new Order[0]));
        }
    }
}

