package gui.model;

import database.dao.RackDao;
import database.model.Rack;
import database.util.DatabaseConnection;
import database.util.RowLockType;
import gui.view.OrderView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.sql.Array;
import java.sql.Connection;
import java.sql.SQLException;

public class BinPackingModel {

    private static final Logger logger = LoggerFactory.getLogger(BinPackingModel.class);
    private BoxListBinPacking boxListBinPacking;
    private RobotArmQueueBinPacking robotArmQueueBinPacking;
    private RackDao rackDao;
    private boolean locationReached;

    public BinPackingModel() {
        this.boxListBinPacking = new BoxListBinPacking();
        this.robotArmQueueBinPacking = new RobotArmQueueBinPacking();
        this.rackDao = RackDao.getInstance();
    }
    public BoxListBinPacking getBoxListBinPacking(){
        return this.boxListBinPacking;
    }

    //functie moet aangeroepen worden op de plek wanneer er een plek is bereikt
    public void updateNewPackageOnScreen(int x, int y){
        PackageBinPacking binPacking = null;
        if(locationReached) {
            try (Connection con = DatabaseConnection.getConnection()) {
                Rack rack = rackDao.getRackByLocation(con, x, y, RowLockType.NONE);
                binPacking = new PackageBinPacking(rack.getProductID(), rack.getWeight(), rack.getPositionX(), rack.getPositionY());
                if (robotArmQueueBinPacking.getRobotArmQueuePackages().size() < 3) {
                    robotArmQueueBinPacking.getRobotArmQueuePackages().add(binPacking);
                    rackDao.deletePackageOnLocation(con, x, y, RowLockType.NONE);
                    //tekenen van het blokje
                }
            } catch (SQLException e) {
                logger.error(e.getMessage());
            }
        }
    }
    public RobotArmQueueBinPacking getRobotArmQueueBinPacking(){
        return robotArmQueueBinPacking;
    }

    public void processPackageFromFork(){
        if(getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() > 1) {
            PackageBinPacking packageBinPacking = getRobotArmQueueBinPacking().getRobotArmQueuePackages().get((getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() - 1));
            addPackageInBox(packageBinPacking);
            getRobotArmQueueBinPacking().getRobotArmQueuePackages().remove((getRobotArmQueueBinPacking().getRobotArmQueuePackages().size()-1));
        }
        else if(getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() == 1){
            PackageBinPacking packageBinPacking = getRobotArmQueueBinPacking().getRobotArmQueuePackages().get(0);
            addPackageInBox(packageBinPacking);
            getRobotArmQueueBinPacking().getRobotArmQueuePackages().remove(0);
        }
    }

    public void addPackageInBox(PackageBinPacking packageBinPacking){
        boolean isPackageAddedInBox = false;
        while(!isPackageAddedInBox){
            for(int i = 0; i < boxListBinPacking.getBoxBinPackingsList().size(); i++) {
                BoxBinPacking box = boxListBinPacking.getBoxBinPackingsList().get(i);
                if(box.getBoxIsOpen() && box.getOpenSpace() >= packageBinPacking.getWeight()){
                    box.addPackageInBox(packageBinPacking);
                    isPackageAddedInBox = true;
                    break;
                }
            }
            if(!isPackageAddedInBox){
                //wacht boven volle doos (Doos moet vervangen worden IRL)
                closeFullestBox();
            }
        }
    }


    public void closeFullestBox(){
        BoxBinPacking fullBox = null;
        int spaceInFullBox = 10;
        for(int i = 0; i < getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking boxBinPacking = getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if(boxBinPacking.getBoxIsOpen() && boxBinPacking.getOpenSpace() < spaceInFullBox){
                fullBox = boxBinPacking;
                spaceInFullBox = boxBinPacking.getOpenSpace();
            }
        }
        fullBox.setBoxIsOpen(false);
        boxListBinPacking.addNewBox(fullBox.getBoxLocation());
        fullBox.setBoxLocation(0);
    }

}
