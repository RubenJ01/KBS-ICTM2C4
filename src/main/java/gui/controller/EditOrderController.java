package gui.controller;

import database.dao.OrderDao;
import database.model.Order;
import database.model.OrderLine;
import database.util.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.Connection;
import java.util.List;

public class EditOrderController {

    private static final Logger logger = LoggerFactory.getLogger(EditOrderController.class);

    private final OrderDao orderDao = OrderDao.getInstance();

    public EditOrderController() {
    }


    public void saveUpdatedOrder(Order order, JTextField quantityField, JTextField pickedQuantityField) {
        try (Connection con = DatabaseConnection.getConnection()) {
            orderDao.updateOrder(con, order);
        } catch (Exception e) {
            logger.error("Error while saving order", e);
        }
    }

    public void updateQuantity(String text, Order copy, OrderLine orderLine) {
        List<OrderLine> orderLines = copy.getOrderLines();
        orderLines.forEach(orderLineCopy -> {
            if (orderLineCopy.getStockItemId() == orderLine.getStockItemId()) {
                orderLineCopy.setQuantity(Integer.parseInt(text));
            }
        });

    }

    public void updatePickedQuantity(String text, Order copy, OrderLine orderLine) {
        List<OrderLine> orderLines = copy.getOrderLines();
        orderLines.forEach(orderLineCopy -> {
            if (orderLineCopy.getStockItemId() == orderLine.getStockItemId()) {
                orderLineCopy.setPickedQuantity(Integer.parseInt(text));
            }
        });
    }

    public void cancelButton(JDialog editOrderDialog) {
        editOrderDialog.dispose();
    }
}
