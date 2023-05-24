package database.dao;

import database.model.OrderLine;
import gui.model.PackageModel;
import gui.model.RackModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RackDao {
    private static final Logger logger = LoggerFactory.getLogger(RackDao.class);
    private static RackDao instance = null;



    private RackDao() {
    }

    public static RackDao getInstance() {
        if (instance == null) {
            instance = new RackDao();
        }
        return instance;
    }

    public void addPackage(Connection con, int productID,int weight, int x,int y)throws SQLException{
        String query = "UPDATE rack SET productID= ?,weight= ?  WHERE positionX= ? AND positionY= ? ";
        System.out.println(query);
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, productID);
            ps.setInt(2, weight);
            ps.setInt(3, x);
            ps.setInt(4, y);
            ps.executeUpdate();
        } catch (SQLException exc) {

            logger.error(exc.getMessage());
        }
    }

    public void removePackage(Connection con,int x,int y){
        String query = "UPDATE rack SET productID=NULL WHERE positionX= ?  AND positionY= ? ";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, x);
            ps.setInt(2, y);
            ps.executeUpdate();
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }

    public void fillRackStartUp(Connection con){
        String query = "SELECT * FROM rack WHERE productID IS NOT NULL";
        try (Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int productID = rs.getInt("productID");
                if (!rs.wasNull()) {
                    RackModel.addToRack(new PackageModel(rs.getInt("positionY"),rs.getInt("positionX"),productID,rs.getInt("weight"),true));
                }
            }
        } catch (SQLException exc) {
            logger.error(exc.getMessage());
        }
    }









}
