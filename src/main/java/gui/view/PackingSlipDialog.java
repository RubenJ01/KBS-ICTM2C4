package gui.view;

import database.model.Order;
import database.model.OrderLine;

import gui.ViewBuilder;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PackingSlipDialog extends JDialog implements ViewBuilder {
    private JPanel orderLinesPanel;
    public static Order selectedOrder = null;
    //makes it so everything is ready to be displayed
    public PackingSlipDialog(CardLayout layout, JPanel root) {
        initializeComponents();
        buildAndShowView();
    }
   //makes it so the information is displayed under each other.
    private void initializeComponents() {
        orderLinesPanel = new JPanel();
        orderLinesPanel.setLayout(new BoxLayout(orderLinesPanel, BoxLayout.Y_AXIS));
    }
   //creates the header of the packing slip with the customers id information and packing slip number
    private JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(3, 1));
        headerPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerPanel.add(new JLabel("Pakbon nummer: " + selectedOrder.getCustomerPurchaseOrderNumber()));
        headerPanel.add(new JLabel(""));
        headerPanel.add(new JLabel("Klant Id: " + selectedOrder.getCustomerId()));
        return headerPanel;
    }

   //makes a scroll panel to easily go through the packing slip
    private JPanel createOrderLinesPanel() {
        JScrollPane scrollPane = new JScrollPane(orderLinesPanel);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JPanel panel = new JPanel(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    //displays the product information in the Packing slip
    private JPanel createOrderLinePanel(OrderLine orderLine) {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.add(new JLabel("Product ID:"));
        panel.add(new JLabel(String.valueOf(orderLine.getStockItemId())));
        panel.add(new JLabel("Productomschrijving:"));
        panel.add(new JLabel(orderLine.getDescription()));
        panel.add(new JLabel("Hoeveelheid:"));
        panel.add(new JLabel(String.valueOf(orderLine.getQuantity())));
        return panel;
    }

    @Override
    //is for setting the dialog and checks if the selected order is not null.
    //if it's null it calls the refresh panel to update the dialog with the order details.
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(750, 750));
        if (!(selectedOrder == null)) {
            refreshPanel();
        }
    }
     //updates the content of the dialog panel with the order lines from selectedOrder.
    public void refreshPanel() {
        orderLinesPanel.removeAll();
        List<OrderLine> orderLines = selectedOrder.getOrderLines();
        for (OrderLine orderLine : orderLines) {
            JPanel orderLinePanel = createOrderLinePanel(orderLine);
            orderLinesPanel.add(orderLinePanel);
        }
        //clears the Packing slip so new info can be displayed
        this.getContentPane().removeAll();
        this.add(createHeaderPanel(), BorderLayout.NORTH);
        this.add(createOrderLinesPanel(), BorderLayout.CENTER);

        orderLinesPanel.revalidate();
        orderLinesPanel.repaint();


    }
}