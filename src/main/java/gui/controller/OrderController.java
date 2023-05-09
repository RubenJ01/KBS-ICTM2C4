package gui.controller;

import database.model.Order;
import gui.view.dialog.AddOrderDialog;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderController {

    private final CardLayout layout;
    private final JPanel root;
    private final JDialog addOrderDialog;


    public void listSelected(ListSelectionEvent e, JList<Order> orderList, JPanel singleOrder) {
        int selectedIndex = orderList.getSelectedIndex();
        if (selectedIndex >= 0) {
            Order selectedOrder = orderList.getSelectedValue();
            singleOrder.removeAll();
            singleOrder.add(new JLabel("Persoon: "));
            singleOrder.add(new JLabel(String.valueOf(selectedOrder.getContactPersonId())));
            singleOrder.add(new JLabel("Order ID: "));
            singleOrder.add(new JLabel(String.valueOf(selectedOrder.getOrderId())));
            singleOrder.add(new JLabel("Datum: "));
            singleOrder.add(new JLabel(selectedOrder.getOrderDate().toString()));
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
