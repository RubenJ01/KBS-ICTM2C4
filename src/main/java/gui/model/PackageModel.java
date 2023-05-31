package gui.model;

import database.dao.StockItemDao;
import database.model.StockItem;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import gui.controller.RobotController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class PackageModel extends JPanel {

    private static final Logger logger = LoggerFactory.getLogger(PackageModel.class);
    private  int locationX;
    private  int locationY;
    private  int locationPanelX;
    private  int locationPanelY;
    private  int productID;

    private int weight;

    private boolean inRack;

    private Color color;

    private final StockItemDao stockItemDao;

    public PackageModel(int locationY, int locationX, int productID, int weight,boolean inRack) {
        this.locationY = locationY;
        this.locationX = locationX;
        this.productID = productID;
        this.weight=weight;
        this.stockItemDao = StockItemDao.getInstance();
        locationConvertToPanel(locationY,locationX);
        this.inRack=inRack;
        if(weight==2){
            this.color=Color.green;
        } else if (weight==5) {
            this.color=Color.blue;
        } else if (weight==7) {
            this.color=Color.red;
        }else{
            this.color=Color.red;
        }

    }
    public PackageModel(int productID, int weight) {
        this(1, 8, productID, weight, false);
    }

    public void locationConvertToPanel(int locationY,int locationX){
        locationPanelX=150*(locationX-1)+38;
        locationPanelY=600-(100*(locationY)+5);
    }

    public boolean isInRack() {
        return inRack;
    }

    public void setInRack(boolean inRack) {
        this.inRack = inRack;
    }

    public  int getLocationX() {
        return locationX;
    }

    public  int getLocationY() {
        return locationY;
    }
    public int getProductID() {
        return productID;
    }

    public int getWeight() {
        return weight;
    }
    public String getNameAndIDFromProductPackage(){
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

    public String toString() {
       return "productID: " + productID + " X: " + locationX + " Y: " + locationY+" Grootte: "+weight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        int packageWidth=75;
        int packageHeight=50;
        g.fillRect(locationPanelX,locationPanelY,packageWidth,packageHeight);
        g.setColor(Color.black);
        Font font = new Font("Calibri", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("ID: "+String.valueOf(productID),locationPanelX,locationPanelY+25);
    }

    public void paintComponent(Graphics g,float y,float x) {
        super.paintComponent(g);
        g.setColor(color);
        int packageWidth=75;
        int packageHeight=50;
        g.fillRect((int) x-(packageWidth/2), (int) y-(packageHeight/2),packageWidth,packageHeight);
        g.setColor(Color.black);
        Font font = new Font("Calibri", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("ID: "+String.valueOf(productID),(int) x-(packageWidth/2),(int) y);
    }

}


