package database.dao;

import database.model.OrderLine;
import database.util.DatabaseConnection;
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



    public RackDao() {
        try (Connection con = DatabaseConnection.getConnection()) {
            String query="CREATE TABLE rack(productID int,weight int,positionX int NOT NULL,positionY int NOT NULL,PRIMARY KEY(positionX,positionY))";
            Statement statement = con.createStatement();
            int result = statement.executeUpdate(query);

            if (result >= 0) {
                System.out.println("Table created successfully.");
            } else {
                System.out.println("Table creation failed.");
            }
        } catch (SQLException e) {
            System.err.println("Error");
        }


        try (Connection con = DatabaseConnection.getConnection()) {
            String query = "INSERT INTO rack(positionX, positionY) VALUES (1,1),(1,2),(1,3),(1,4),(1,5),(1,6)";
            Statement statement = con.createStatement();
            statement.executeUpdate(query);

            query = "INSERT INTO rack(positionX, positionY) VALUES (2,1),(2,2),(2,3),(2,4),(2,5),(2,6)";
            statement.executeUpdate(query);

            query = "INSERT INTO rack(positionX, positionY) VALUES (3,1),(3,2),(3,3),(3,4),(3,5),(3,6)";
            statement.executeUpdate(query);

            query = "INSERT INTO rack(positionX, positionY) VALUES (4,1),(4,2),(4,3),(4,4),(4,5),(4,6)";
            statement.executeUpdate(query);

            query = "INSERT INTO rack(positionX, positionY) VALUES (5,1),(5,2),(5,3),(5,4),(5,5),(5,6)";
            statement.executeUpdate(query);

            System.out.println("Data inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }

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

    public static void removePackage(Connection con, int x, int y){
        String query = "UPDATE rack SET productID=NULL, weight=NULL WHERE positionX= ?  AND positionY= ? ";
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
