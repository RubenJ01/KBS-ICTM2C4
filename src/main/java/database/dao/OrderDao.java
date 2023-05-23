package database.dao;

import database.model.Order;
import database.model.OrderLine;
import database.util.RowLockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsible for all the queries made to the database regarding orders.
 */
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
        String getAllOrderLines = "SELECT * FROM orderlines WHERE OrderID = ?";
        List<OrderLine> orderLines = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    try (PreparedStatement ps2 = con.prepareStatement(getAllOrderLines)) {
                        ps2.setInt(1, rs.getInt("OrderID"));
                        try (ResultSet rs2 = ps2.executeQuery()) {
                            orderLines.add(createOrderLineFromResultSet(rs2));
                        }
                    }
                    return createOrderFromResultSet(rs, orderLines);
                } else {
                    return null;
                }
            }
        }
    }

    /**
     * Retrieves all orders from the database and adds them to the list model.
     * This method should never be used outside of OrderView.
     *
     * @param con                   the database connection object.
     * @param orderDefaultListModel the list model to add the orders to.
     * @param orderAmount           the label to set the amount of orders to.
     * @return a list of all orders.
     * @throws SQLException if the query failed.
     */
    public List<Order> getAllOrders(Connection con, DefaultListModel<Order> orderDefaultListModel, JLabel orderAmount,
                                    JLabel currentVisibleOrders) throws SQLException {
        String getAllOrders = "SELECT * FROM orders ORDER BY OrderID";
        String getAllOrderLines = "SELECT * FROM orderlines WHERE OrderID = ?";
        List<Order> allOrders = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(getAllOrders);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int orderId = rs.getInt("OrderID");
                List<OrderLine> orderLines = getOrderLines(con, getAllOrderLines, orderId);
                Order loadedOrder = createOrderFromResultSet(rs, orderLines);
                allOrders.add(loadedOrder);
                if (allOrders.size() > 500) {
                    addOrdersToDefaultListModel(allOrders, orderDefaultListModel);
                    allOrders.clear();
                    orderAmount.setText(String.format("Totaal aantal orders: %d", orderDefaultListModel.size()));
                    currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", orderDefaultListModel.size()));
                }
            }
            addOrdersToDefaultListModel(allOrders, orderDefaultListModel);
            orderAmount.setText(String.format("Totaal aantal orders: %d", orderDefaultListModel.size()));
            currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", orderDefaultListModel.size()));
        } catch (ArrayIndexOutOfBoundsException e) {
            logger.error(e.getMessage());
        }
        return allOrders;
    }

    /**
     * Retrieves all order lines from a specific order.
     *
     * @param con     the database connection object.
     * @param query   the query to execute.
     * @param orderId the id of the order you want to retrieve the order lines from.
     * @return a list of order lines.
     * @throws SQLException if the query failed.
     */
    private List<OrderLine> getOrderLines(Connection con, String query, int orderId) throws SQLException {
        List<OrderLine> orderLines = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderLine orderLine = createOrderLineFromResultSet(rs);
                    orderLines.add(orderLine);
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
        return orderLines;
    }

    /**
     * Creates an order from a result set.
     *
     * @param rs         the result set to create the order from.
     * @param orderLines the order lines to add to the order.
     * @return the order as an object.
     * @throws SQLException if the query failed.
     */
    private Order createOrderFromResultSet(ResultSet rs, List<OrderLine> orderLines) throws SQLException {
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
                rs.getDate("LastEditedWhen"),
                orderLines
        );
    }

    /**
     * Creates an order line from a result set.
     *
     * @param rs the result set to create the order line from.
     * @return the order line as an object.
     * @throws SQLException if the query failed.
     */
    private OrderLine createOrderLineFromResultSet(ResultSet rs) throws SQLException {
        return new OrderLine(
                rs.getInt("OrderLineID"),
                rs.getInt("OrderID"),
                rs.getInt("StockItemID"),
                rs.getString("Description"),
                rs.getInt("PackageTypeID"),
                rs.getInt("Quantity"),
                rs.getFloat("UnitPrice"),
                rs.getFloat("TaxRate"),
                rs.getInt("PickedQuantity"),
                rs.getDate("PickingCompletedWhen"),
                rs.getInt("LastEditedBy"),
                rs.getDate("LastEditedWhen")
        );
    }

    /**
     * Adds orders to the list model.
     *
     * @param orders                the orders to add.
     * @param orderDefaultListModel the list model to add the orders to.
     */
    private void addOrdersToDefaultListModel(List<Order> orders, DefaultListModel<Order> orderDefaultListModel) {
        try {
            SwingUtilities.invokeAndWait(() -> {
                for (Order order : orders) {
                    orderDefaultListModel.addElement(order);
                }
            });
        } catch (InterruptedException | InvocationTargetException e) {
            logger.error(e.getMessage());
        }
    }

    /**
     * Adds an order to the database.
     *
     * @param con   the database connection object.
     * @param order the order you want to add.
     * @throws SQLException if the query failed.
     */
    public void addOrder(Connection con, Order order) throws SQLException {
        String query = "INSERT INTO orders(CustomerID, OrderDate, ExpectedDeliveryDate, IsUndersupplyBackordered, " +
                "LastEditedBy, LastEditedWhen, SalespersonPersonID, ContactPersonID, OrderID) VALUES (?,?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, order.getCustomerId());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getExpectedDeliveryDate());
            ps.setInt(4, order.getIsUndersupplyBackordered());
            ps.setInt(5, order.getLastEditedBy());
            ps.setDate(6, order.getLastEditedWhen());
            ps.setInt(7, order.getSalespersonPersonId());
            ps.setInt(8, order.getContactPersonId());
            ps.setInt(9, order.getOrderId());
            ps.executeUpdate();
        }
    }

    /**
     * Retrieves the newest order from the database.
     *
     * @param con         the database connection object.
     * @param rowLockType the row lock type.
     * @return the newest order.
     * @throws SQLException if the query failed.
     */
    public Order getNewestOrder(Connection con, RowLockType rowLockType) throws SQLException {
        String getLatestOrderQuery = rowLockType.getQueryWithLock("SELECT * FROM orders ORDER BY OrderID DESC LIMIT 1");
        String getAllOrderLines = rowLockType.getQueryWithLock("SELECT * FROM orderlines WHERE OrderID = ?");
        try (PreparedStatement ps = con.prepareStatement(getLatestOrderQuery)) {
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt("OrderID");
                List<OrderLine> orderLines = getOrderLines(con, getAllOrderLines, orderId);
                return createOrderFromResultSet(rs, orderLines);
            }
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
        return null;
    }

    /**
     * Updates an order in the database.
     *
     * @param con   the database connection object.
     * @param order the order you want to update.
     * @throws SQLException if the query failed.
     */
    public void updateOrder(Connection con, Order order) throws SQLException {
        String query = "UPDATE orders SET CustomerID = ?, OrderDate = ?, ExpectedDeliveryDate = ?, IsUndersupplyBackordered = ?, " +
                "LastEditedBy = ?, LastEditedWhen = ?, SalespersonPersonID = ?, ContactPersonID = ?, OrderID = ? WHERE OrderID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, order.getCustomerId());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getExpectedDeliveryDate());
            ps.setInt(4, order.getIsUndersupplyBackordered());
            ps.setInt(5, order.getLastEditedBy());
            ps.setDate(6, order.getLastEditedWhen());
            ps.setInt(7, order.getSalespersonPersonId());
            ps.setInt(8, order.getContactPersonId());
            ps.setInt(9, order.getOrderId());
            ps.setInt(10, order.getOrderId());

            for (OrderLine orderLine : order.getOrderLines()) {
                updateOrderLine(con, orderLine);
            }
            ps.executeUpdate();
        }

    }

    /**
     * Updates an order line in the database.
     *
     * @param con       the database connection object.
     * @param orderLine the order line you want to update.
     */
    private void updateOrderLine(Connection con, OrderLine orderLine) {
        String query = "UPDATE orderlines SET OrderID = ?, StockItemID = ?, Description = ?, PackageTypeID = ?, Quantity = ?, UnitPrice = ?, TaxRate = ?, PickedQuantity = ?, PickingCompletedWhen = ?, LastEditedBy = ?, LastEditedWhen = ? WHERE OrderLineID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderLine.getOrderId());
            ps.setInt(2, orderLine.getStockItemId());
            ps.setString(3, orderLine.getDescription());
            ps.setInt(4, orderLine.getPackageTypeId());
            ps.setInt(5, orderLine.getQuantity());
            ps.setFloat(6, orderLine.getUnitPrice());
            ps.setFloat(7, orderLine.getTaxRate());
            ps.setInt(8, orderLine.getPickedQuantity());
            ps.setDate(9, orderLine.getPickingCompletedWhen());
            ps.setInt(10, orderLine.getLastEditedBy());
            ps.setDate(11, orderLine.getLastEditedWhen());
            ps.setInt(12, orderLine.getOrderLineId());
            ps.executeUpdate();
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}
