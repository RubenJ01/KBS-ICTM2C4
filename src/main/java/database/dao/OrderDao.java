package database.dao;

import database.model.Order;
import database.util.RowLockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {

    private static final Logger logger = LoggerFactory.getLogger(OrderDao.class);
    private static OrderDao instance = null;

    private OrderDao() {
    }

    public static OrderDao getInstance() {
        if (instance == null) {
            instance = new OrderDao();
        }
        return instance;
    }

    /**
     * Retrieves an order from the database.
     *
     * @param con         the database connection object.
     * @param orderId     the id of the order you want to retrieve.
     * @param rowLockType the type of lock you want the query to use.
     * @return the order as an object.
     * @throws SQLException if the query failed.
     */
    public Order getOrderByOrderId(Connection con, int orderId, RowLockType rowLockType) throws SQLException {
        String query = rowLockType.getQueryWithLock("SELECT * FROM orders WHERE OrderID = ?");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Order(
                            rs.getInt("OrderID"),
                            rs.getInt("CustomerID"),
                            rs.getInt("SalespersonPersonID"),
                            rs.getInt("PickedByPersonID"),
                            rs.getInt("ContactPersonID"),
                            rs.getInt("BackorderOrderID"),
                            rs.getDate("OrderDate"),
                            rs.getDate("ExpectedDeliveryDate"),
                            rs.getString("CustomerPurchaseOrderNumber"),
                            rs.getInt("IsUndersupplyBackordered"),
                            rs.getString("Comments"),
                            rs.getString("DeliveryInstructions"),
                            rs.getString("InternalComments"),
                            rs.getDate("PickingCompletedWhen"),
                            rs.getInt("LastEditedBy"),
                            rs.getDate("LastEditedWhen")
                    );
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Retrieves all orders from the database.
     *
     * @param con the database connection object.
     * @return a List of all orders in the database.
     * @throws SQLException if the query failed.
     */
    public List<Order> getAllOrders(Connection con) throws SQLException {
        String query = "SELECT * FROM orders";
        List<Order> orders = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    orders.add(new Order(
                            rs.getInt("OrderID"),
                            rs.getInt("CustomerID"),
                            rs.getInt("SalespersonPersonID"),
                            rs.getInt("PickedByPersonID"),
                            rs.getInt("ContactPersonID"),
                            rs.getInt("BackorderOrderID"),
                            rs.getDate("OrderDate"),
                            rs.getDate("ExpectedDeliveryDate"),
                            rs.getString("CustomerPurchaseOrderNumber"),
                            rs.getInt("IsUndersupplyBackordered"),
                            rs.getString("Comments"),
                            rs.getString("DeliveryInstructions"),
                            rs.getString("InternalComments"),
                            rs.getDate("PickingCompletedWhen"),
                            rs.getInt("LastEditedBy"),
                            rs.getDate("LastEditedWhen")
                    ));
                }
            }
        }
        return orders;
    }

}
