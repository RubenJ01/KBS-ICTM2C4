package gui.controller;

import database.dao.OrderDao;
import database.model.Order;
import database.model.OrderLine;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import gui.view.OrderView;
import gui.view.dialog.AddOrderDialog;
import gui.view.dialog.EditOrderDialog;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.util.ArrayList;
import java.util.List;

public class OrderController {

    private final CardLayout layout;
    private final JPanel root;
    private final JDialog editOrderDialog;

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

    public OrderController(CardLayout layout, JPanel root, JLabel totalOrders, DefaultListModel<Order> orderListModel ,
                           JLabel currentVisibleOrders) {
        this.layout = layout;
        this.root = root;
        this.addOrderDialog = new AddOrderDialog(totalOrders, orderListModel, currentVisibleOrders);
        this.editOrderDialog = new EditOrderDialog();
    }

    /**
     * This method is used to open the JDialog to edit an order.
     */
    public void editButton(JList<Order> orderList) {
        if (!this.editOrderDialog.isActive() || !this.editOrderDialog.isVisible()) {
            if(orderList.getSelectedIndex() == -1) {
                JOptionPane.showMessageDialog(null, "Selecteer een order om te bewerken.");
                return;
            }
            EditOrderDialog.order = orderList.getSelectedValue();
            this.editOrderDialog.setVisible(true);
        }
    }

    /**
     * This method is used to open the JDialog to add an order.
     * @param e ActionEvent to check if the button is clicked.
     */
    public void addButton(ActionEvent e) {
        if (!this.addOrderDialog.isActive() || !this.addOrderDialog.isVisible()) {
            this.addOrderDialog.setVisible(true);
        }
    }
    public void viewOrderButton(ActionEvent e){

    }

    /**
     * This method is used to search for an order by order id.
     *
     * @param orderList         JList of orders.
     * @param orderListModel    DefaultListModel of orders.
     * @param search            String to search for.
     * @param filterPickedOrder JCheckBox to filter picked orders.
     * @param currentVisibleOrders JLabel to show the amount of orders that are visible.
     * @see Order for more information about the order.
     */
    public void searchTextField(JList<Order> orderList, DefaultListModel<Order> orderListModel, String search,
                                JCheckBox filterPickedOrder, JLabel currentVisibleOrders) {
        List<Order> result = new ArrayList<>();
        if (search.equals("Zoeken...") || search.equals("")) {
            for (int i = 0; i < orderListModel.getSize(); i++) {
                Order order = orderListModel.getElementAt(i);
                if (filterPickedOrder.isSelected() && order.getPickingCompletedWhen() != null) {
                    continue;
                }
                result.add(order);
                currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", result.size()));
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
                    currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", result.size()));
                }
            } catch (NumberFormatException ex) {
                currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", result.size()));
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
    public void filterPickedOrder(JList<Order> orderList, boolean isSelected, DefaultListModel<Order> orderListModel,
                                  JLabel currentVisibleOrders) {
        if (isSelected) {
            List<Order> result = new ArrayList<>();
            for (int i = 0; i < orderListModel.getSize(); i++) {
                Order order = orderListModel.getElementAt(i);
                if (order.getPickingCompletedWhen() == null) {
                    result.add(order);
                }
            }
            orderList.setListData(result.toArray(new Order[0]));
            currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", result.size()));
        } else {
            List<Order> allOrders = new ArrayList<>();
            for (int i = 0; i < orderListModel.getSize(); i++) {
                allOrders.add(orderListModel.getElementAt(i));
            }
            orderList.setListData(allOrders.toArray(new Order[0]));
            currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", allOrders.size()));
        }
    }

    /**
     * This method is called when an order is selected in the JList.
     * @param orderList JList of orders.
     */
    public void listSelectionListener(JList<Order> orderList) {
        if (orderList.getSelectedIndex() == -1) {
            return;
        }
        EditOrderDialog.order = orderList.getSelectedValue();
        // not very pretty but this refreshed the dialog
        this.editOrderDialog.setVisible(false);
    }
}

