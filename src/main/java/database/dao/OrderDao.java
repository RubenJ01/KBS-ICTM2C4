package database.dao;

import database.model.Order;
import database.model.OrderLine;
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
                    try(PreparedStatement ps2 = con.prepareStatement(getAllOrderLines)) {
                        ps2.setInt(1, rs.getInt("OrderID"));
                        try(ResultSet rs2 = ps2.executeQuery()) {
                            while(rs2.next()) {
                                orderLines.add(new OrderLine(
                                        rs2.getInt("OrderLineID"),
                                        rs2.getInt("OrderID"),
                                        rs2.getInt("StockItemID"),
                                        rs2.getString("Description"),
                                        rs2.getInt("PackageTypeID"),
                                        rs2.getInt("Quantity"),
                                        rs2.getFloat("UnitPrice"),
                                        rs2.getFloat("TaxRate"),
                                        rs2.getInt("PickedQuantity"),
                                        rs2.getDate("PickingCompletedWhen"),
                                        rs2.getInt("LastEditedBy"),
                                        rs2.getDate("LastEditedWhen")));

                            }
                        }
                    }
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
                } else {
                    return null;
                }
            }
        }
    }
    public Order getProductsByOrderId(Connection con, int orderId, RowLockType rowLockType) throws SQLException {
        String query = rowLockType.getQueryWithLock("SELECT StockItemID, Description FROM orderlines WHERE OrderID = ?");
        String getProductOrderLines = "SELECT StockItemID, Description FROM orderlines WHERE OrderID = ?";
        List<OrderLine> orderLines = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, orderId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    try(PreparedStatement ps2 = con.prepareStatement(getProductOrderLines)) {
                        ps2.setInt(1, rs.getInt("OrderID"));
                        try(ResultSet rs2 = ps2.executeQuery()) {
                            while(rs2.next()) {
                                orderLines.add(new OrderLine(
                                        rs2.getInt("OrderLineID"),
                                        rs2.getInt("OrderID"),
                                        rs2.getInt("StockItemID"),
                                        rs2.getString("Description")
                                    )
                                );
                            }
                        }
                    }
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
        String getAllOrders = "SELECT * FROM orders LIMIT 1000";
        List<Order> orders = new ArrayList<>();
        String getAllOrderLines = "SELECT * FROM orderlines WHERE OrderID = ?";
        List<OrderLine> orderLines = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(getAllOrders)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    try(PreparedStatement ps2 = con.prepareStatement(getAllOrderLines)) {
                        ps2.setInt(1, rs.getInt("OrderID"));
                        try(ResultSet rs2 = ps2.executeQuery()) {
                            while(rs2.next()) {
                                orderLines.add(new OrderLine(
                                        rs2.getInt("OrderLineID"),
                                        rs2.getInt("OrderID"),
                                        rs2.getInt("StockItemID"),
                                        rs2.getString("Description"),
                                        rs2.getInt("PackageTypeID"),
                                        rs2.getInt("Quantity"),
                                        rs2.getFloat("UnitPrice"),
                                        rs2.getFloat("TaxRate"),
                                        rs2.getInt("PickedQuantity"),
                                        rs2.getDate("PickingCompletedWhen"),
                                        rs2.getInt("LastEditedBy"),
                                        rs2.getDate("LastEditedWhen")));
                            }
                        }
                    }
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
                            rs.getDate("LastEditedWhen"),
                            orderLines
                    ));
                }
            }
        }
        return orders;
    }

}
