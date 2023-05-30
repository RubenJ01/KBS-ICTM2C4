package database.dao;

import database.model.Rack;
import database.model.StockItem;
import database.model.StockItemHolding;
import database.util.RowLockType;
import gui.model.PackageBinPacking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RackDao {
        private static final Logger logger = LoggerFactory.getLogger(database.dao.RackDao.class);
        private static database.dao.RackDao instance = null;

        private RackDao() {}

        public static database.dao.RackDao getInstance() {
            if(instance == null) {
                instance = new database.dao.RackDao();
            }
            return instance;
        }

    /**
     * Function to get a package info on a certain location from the database
     * @param con
     * @param x
     * @param y
     * @param rowLockType
     * @return either null or the package info of a certain location
     * @throws SQLException
     */
        public Rack getRackByLocation(Connection con, int x, int y, RowLockType rowLockType) throws SQLException {
            String query = rowLockType.getQueryWithLock(
                    "SELECT * FROM rack WHERE positionX = ? AND positionY = ?"
            );
            try(PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, x);
                ps.setInt(2, y);
                try(ResultSet rs = ps.executeQuery()) {
                    if(rs.next()) {
                        return getRack(rs);
                    } else {
                        return null;
                    }
                }
            }
        }

        // wordt dit al ergens ander gedaan?
//    /**
//     * Function to remove a package from a location in the database
//     * @param con
//     * @param x
//     * @param y
//     * @param rowLockType
//     * @throws SQLException
//     */
//    public void deletePackageOnLocation(Connection con, int x, int y, RowLockType rowLockType) throws SQLException {
//        String query = rowLockType.getQueryWithLock(
//                "UPDATE rack " +
//                        "SET productID = null, weight = null " +
//                        "WHERE positionX = ? AND positionY = ?"
//        );
//        try(PreparedStatement ps = con.prepareStatement(query)) {
//            ps.setInt(1, x);
//            ps.setInt(2, y);
//            ps.executeUpdate();
//        }
//    }

        private static Rack getRack(ResultSet rs) throws SQLException {
            return new Rack(
                    rs.getInt("productID"),
                    rs.getInt("weight"),
                    rs.getInt("positionX"),
                    rs.getInt("positionY")
            );
        }
}
