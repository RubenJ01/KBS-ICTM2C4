package gui.view.dialog;

import database.dao.OrderDao;
import database.model.Order;
import gui.ViewBuilder;
import gui.controller.ProcessOrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;

public class ProcessOrderDialog extends JDialog implements ViewBuilder {

    private static final Logger logger = LoggerFactory.getLogger(ProcessOrderDialog.class);
    private final ProcessOrderController processOrderController = new ProcessOrderController();
//    private final OrderDao orderDao;
    public static Order order;
    private JLabel header;
    private Order copy;

    private JPanel scrollablePanel;

    public ProcessOrderDialog(){
 //       this.orderDao = orderDao;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(250, 100));
        this.setMaximumSize(new Dimension(250, 100));
        this.setLocationRelativeTo(null);


        header = new JLabel("", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        this.add(header, BorderLayout.NORTH);

        // make a scrollable panel
        scrollablePanel = new JPanel();
        scrollablePanel.setLayout(new BoxLayout(scrollablePanel, BoxLayout.Y_AXIS));
        scrollablePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane scrollPane = new JScrollPane(scrollablePanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        this.add(scrollPane, BorderLayout.CENTER);

//        this.setModal(true);
//        this.setLayout(new BorderLayout());
//        this.setSize(new Dimension(300, 125));
//        this.setLocationRelativeTo(null);
//
////        JLabel header = new JLabel("Order verwerken", SwingConstants.CENTER);
////        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
////        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
////        this.add(header, BorderLayout.NORTH);
//
//
//
//        JPanel centerContent = new JPanel();
//        centerContent.setLayout(new GridLayout(1,2));
//        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
//        this.add(centerContent, BorderLayout.CENTER);
//
//        JLabel orderID = new JLabel("Order ID:");
//        JTextField orderIDField = new JTextField();
//        orderIDField.setSize(5, 1);
//
//        centerContent.add(orderID);
//        centerContent.add(orderIDField);
//
//        JButton confirmButton = new JButton("Bevestigen");
//        confirmButton.addActionListener(processOrderController::processButton);
//
//        this.add(confirmButton, BorderLayout.SOUTH);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        this.copy = order.copy();
        updateView();
    }

    private void updateView() {
        this.header.setText(String.format("Order %d verwerken", copy.getOrderId()));

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new GridLayout(1,2));
        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(centerContent, BorderLayout.CENTER);

        JLabel orderID = new JLabel("Order ID: " + copy.getOrderId());

        centerContent.add(orderID);

        this.scrollablePanel.add(centerContent);

        JButton confirmButton = new JButton("Bevestigen");
        confirmButton.addActionListener(processOrderController::processButton);

        this.add(confirmButton, BorderLayout.SOUTH);


    }
}