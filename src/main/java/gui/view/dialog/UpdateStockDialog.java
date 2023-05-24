package gui.view.dialog;

import database.dao.StockItemDao;
import gui.ViewBuilder;
import gui.controller.UpdateStockController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateStockDialog extends JDialog implements ViewBuilder {

    private final UpdateStockController updateStockController = new UpdateStockController();
    private final StockItemDao stockItemDao = StockItemDao.getInstance();
    private final JTable stockTable;

    public UpdateStockDialog(JTable stockTable) {
        this.stockTable = stockTable;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(300, 150));
        this.setLocationRelativeTo(null);

        JLabel header = new JLabel("Voorraad bewerken", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        this.add(header, BorderLayout.NORTH);

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new GridLayout(2, 2));
        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(centerContent, BorderLayout.CENTER);

        JLabel productID = new JLabel("Product ID:");
        JTextField productIDField = new JTextField();
        productIDField.setSize(5, 1);

        JLabel stock = new JLabel("Voorraad:");
        JTextField stockField = new JTextField();
        stockField.setSize(5, 2);

        centerContent.add(productID);
        centerContent.add(productIDField);
        centerContent.add(stock);
        centerContent.add(stockField);


        JButton addButton = new JButton("Opslaan");
        addButton.addActionListener(updateStockController::addButton);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStockController.updateStock(e, productIDField, stockField, stockTable);
            }
        });

        this.add(addButton, BorderLayout.SOUTH);

    }

}