package database.dao;

import database.model.Order;
import database.model.OrderLine;
import database.util.RowLockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
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
     * It blocks the EDT for a long time.
     * @param con the database connection object.
     * @param orderDefaultListModel the list model to add the orders to.
     * @param orderAmount the label to set the amount of orders to.
     * @return a list of all orders.
     * @throws SQLException if the query failed.
     */
    public List<Order> getAllOrders(Connection con, DefaultListModel<Order> orderDefaultListModel, JLabel orderAmount) throws SQLException {
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
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        return allOrders;
    }

    private List<OrderLine> getOrderLines(Connection con, String query, int orderId) throws SQLException {
        List<OrderLine> orderLines = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    OrderLine orderLine = createOrderLineFromResultSet(rs);
                    orderLines.add(orderLine);
                }
            }
        }
        return orderLines;
    }

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

    private OrderLine createOrderLineFromResultSet(ResultSet rs) throws SQLException {
        OrderLine orderLine = new OrderLine(
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
        return orderLine;
    }

    private void addOrdersToDefaultListModel(List<Order> orders, DefaultListModel<Order> orderDefaultListModel) {
        orders.forEach(orderDefaultListModel::addElement);
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
                "LastEditedBy, LastEditedWhen, SalespersonPersonID, ContactPersonID) VALUES (?,?,?,?,?,?,?,?)";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            System.out.println(order);
            ps.setInt(1, order.getCustomerId());
            ps.setDate(2, order.getOrderDate());
            ps.setDate(3, order.getExpectedDeliveryDate());
            ps.setInt(4, order.getIsUndersupplyBackordered());
            ps.setInt(5, order.getLastEditedBy());
            ps.setDate(6, order.getLastEditedWhen());
            ps.setInt(7, order.getSalespersonPersonId());
            ps.setInt(8, order.getContactPersonId());
            ps.executeUpdate();
        }
    }


}
