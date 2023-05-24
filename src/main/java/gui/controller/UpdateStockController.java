package gui.controller;

import database.dao.StockItemDao;
import database.model.*;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import gui.view.StockView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;


public class UpdateStockController {

    private static final Logger logger = LoggerFactory.getLogger(UpdateStockController.class);
    private final StockItemDao stockItemDao = StockItemDao.getInstance();


    public void updateStock(ActionEvent e, JTextField orderIDField, JTextField stockField, JTable stockTable) {
        try (Connection con = DatabaseConnection.getConnection()) {
            StockItem stockItemByStockItemID = stockItemDao.getStockByStockItemID(con,
                    Integer.parseInt(orderIDField.getText()), RowLockType.NONE);
            StockItemHolding stockitemholdings = stockItemByStockItemID.getStockitemholdings();
            stockitemholdings.addQuantityOnHand(Integer.parseInt(stockField.getText()));
            stockItemDao.updateQuantityOnHand(con, stockitemholdings);
            stockTable.repaint();
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }

    public void addButton(ActionEvent actionEvent) {
    }
}