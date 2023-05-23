package gui.view.dialog;

import database.dao.OrderDao;
import gui.ViewBuilder;
import gui.controller.ProcessOrderController;

import javax.swing.*;
import java.awt.*;

public class ProcessOrderDialog extends JDialog implements ViewBuilder {

    private final ProcessOrderController pickenOrderController = new ProcessOrderController();
    private final OrderDao orderDao;

    public ProcessOrderDialog(OrderDao orderDao){
        this.orderDao = orderDao;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(300, 125));
        this.setLocationRelativeTo(null);

        JLabel header = new JLabel("Order verwerken", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        this.add(header, BorderLayout.NORTH);

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new GridLayout(1,2));
        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(centerContent, BorderLayout.CENTER);

        JLabel orderID = new JLabel("Order ID:");
        JTextField orderIDField = new JTextField();
        orderIDField.setSize(5, 1);

        centerContent.add(orderID);
        centerContent.add(orderIDField);

        JButton confirmButton = new JButton("Bevestigen");
        confirmButton.addActionListener(pickenOrderController::processButton);

        this.add(confirmButton, BorderLayout.SOUTH);
    }
}