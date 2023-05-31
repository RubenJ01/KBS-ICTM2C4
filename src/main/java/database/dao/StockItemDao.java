package database.dao;

import database.model.StockItem;
import database.model.StockItemHolding;
import database.util.RowLockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockItemDao {

    private static final Logger logger = LoggerFactory.getLogger(StockItemDao.class);
    private static StockItemDao instance = null;

    private StockItemDao() {
    }

    public static StockItemDao getInstance() {
        if (instance == null) {
            instance = new StockItemDao();
        }
        return instance;
    }

    /**
     * Funtion to receive stock information by searching on a specific stockItemID
     *
     * @param con
     * @param stockItemID
     * @param rowLockType
     * @throws SQLException
     */
    public StockItem getStockByStockItemID(Connection con, int stockItemID, RowLockType rowLockType) throws SQLException {
        String query = rowLockType.getQueryWithLock(
                "SELECT SI.stockItemID, QuantityOnHand, stockItemName " +
                "FROM stockitems AS SI " +
                "LEFT JOIN stockitemholdings AS SIH ON SIH.stockItemID = SI.stockItemID " +
                "WHERE SI.StockItemID = ? "
        );
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, stockItemID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return getStockitems(rs);
                } else {
                    return null;
                }
            }
        }
    }


    /**
     * function to receive all stock information from all products in the database
     *
     * @param con
     * @return
     * @throws SQLException
     */
    public List<StockItem> getAllStockItemHoldings(Connection con) throws SQLException {
        String query = "SELECT SI.stockItemID, QuantityOnHand, stockItemName " +
                "FROM stockitems AS SI " +
                "LEFT JOIN stockitemholdings AS SIH ON SIH.stockItemID = SI.stockItemID " +
                "ORDER BY stockItemID";
        List<StockItem> stockitems = new ArrayList<>();
        try (PreparedStatement ps = con.prepareStatement(query)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    stockitems.add(getStockitems(rs));
                }
            }
        }
        return stockitems;
    }

    public void updateQuantityOnHand(Connection con, StockItemHolding stockitemholdings) {
        String query = "UPDATE stockitemholdings SET QuantityOnHand = ? WHERE stockItemID = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, stockitemholdings.getQuantityOnHand());
            ps.setInt(2, stockitemholdings.getStockItemID());
            ps.executeUpdate();
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }

    /**
     * Function to put a result set in a list
     *
     * @param rs
     * @return new StockItem
     * @throws SQLException
     */
    private StockItem getStockitems(ResultSet rs) throws SQLException {
        StockItemHolding stockitemholdings = new StockItemHolding(
                rs.getInt("StockItemID"),
                rs.getInt("QuantityOnHand")
        );
        return new StockItem(
                rs.getInt("StockItemID"),
                rs.getString("StockItemName"),
                stockitemholdings);
    }

}
