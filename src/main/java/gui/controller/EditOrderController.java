package gui.controller;

import database.dao.OrderDao;
import database.model.Order;
import database.model.OrderLine;
import database.util.DatabaseConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class EditOrderController {

    private static final Logger logger = LoggerFactory.getLogger(EditOrderController.class);

    private final OrderDao orderDao = OrderDao.getInstance();

    public EditOrderController() {
    }


    public void updatePickedQuantity(OrderLine orderLine, String pickedQuantity, Order order) {
        try {
            int amount = Integer.parseInt(pickedQuantity);
            orderLine.setPickedQuantity(amount);
            try(Connection con = DatabaseConnection.getConnection()) {
                orderDao.updateOrder(con, order);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid amount entered");
        }
    }

    public void updateQuantity(OrderLine orderLine, String quantity, Order order) {
        try {
            int amount = Integer.parseInt(quantity);
            orderLine.setQuantity(amount);
            try(Connection con = DatabaseConnection.getConnection()) {
                orderDao.updateOrder(con, order);
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        } catch (NumberFormatException e) {
            logger.error("Invalid amount entered");
        }
    }
}
