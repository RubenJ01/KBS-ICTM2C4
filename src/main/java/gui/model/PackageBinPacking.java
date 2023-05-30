package gui.model;

import database.dao.StockItemDao;
import database.model.StockItem;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public class PackageBinPacking {
    private static final Logger logger = LoggerFactory.getLogger(PackageBinPacking.class);
    private int weight;
    private int productID;
    private int locationX;
    private int locationY;
    private final StockItemDao stockItemDao;

    public PackageBinPacking(int weight, int productID, int locationX, int locationY){
        this.weight = weight;
        this.productID = productID;
        this.locationX = locationX;
        this.locationY = locationY;
        this.stockItemDao = StockItemDao.getInstance();
    }

    public PackageBinPacking(int weight, int productID){
        this(weight, productID, 2, 3);
    }

    public int getWeight(){
        return this.weight;
    }
    public int getProductID(){
        return this.productID;
    }

    @Override
    public String toString(){
        String productName = "";
        try (Connection con = DatabaseConnection.getConnection()) {
            StockItem stockItem = stockItemDao.getStockByStockItemID(con, productID, RowLockType.NONE);
            productName = stockItem.getStockItemName();
        }
        catch (SQLException e) {
            logger.error(e.getMessage());
            productName = "Data niet ontvangen";
        }
        return "ID " + productID + ": " +  productName;
    }
}
