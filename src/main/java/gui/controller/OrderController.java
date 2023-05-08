package gui.controller;

import database.model.Order;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;
import java.util.Optional;

public class OrderController {

    private final CardLayout layout;
    private final JPanel root;

    public OrderController(CardLayout layout, JPanel root) {
        this.layout = layout;
        this.root = root;
    }

    public void editButton(ActionEvent e) {

    }

    public void addButton(ActionEvent e) {

    }

    /**
     * Handles searching for a specific order id in the list of orders.
     * @param e the actionevent.
     * @param orderList the JList with all the orders.
     * @param allOrders the list with all the order objects.
     * @see Order
     */
    public void searchTextField(ActionEvent e, JList<Order> orderList, List<Order> allOrders) {
        JTextField textField = (JTextField) e.getSource();
        try {
            int orderId = Integer.parseInt(textField.getText());
            Optional<Integer> listIndex = Optional.empty();
            for(int i=0; i < allOrders.size(); i++) {
                if(allOrders.get(i).getOrderId() == orderId) {
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
