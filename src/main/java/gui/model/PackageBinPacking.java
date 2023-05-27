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
    //Hierin wordt de informatie over een pakket weergegeven: Een productID en een gewicht
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

    public int getWeight(){
        return this.weight;
    }
    public int getProductID(){
        return this.productID;
    }
    public void setWeight(String color){
        if(color.equals("green")){
            this.weight = 2;
        }
        else if(color.equals("blue")){
            this.weight = 5;
        }
        else if(color.equals("red")){
            this.weight = 7;
        }
        else{
            this.weight = 0;
        }
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
