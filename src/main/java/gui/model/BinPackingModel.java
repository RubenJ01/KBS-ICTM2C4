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

    public BoxListBinPacking getBoxListBinPacking() {
        return this.boxListBinPacking;
    }


    /**
     * Function to receive data from the arduino's to put a package on the robotarmqueue (fork)
     * Function does not yet operate/receive the needed data to work
     * ProcessButton in BinPackingController is a testbutton to simulate this implementation
     */
    public void updateNewPackageOnScreen() {
        //#todo
        // Receive signal/variable that a package has been retrieved from the rack.
        // Get the package that belonged to the retrieved place on the rack from the database
        // Put that package in the robotarmqueue array
    }

    public RobotArmQueueBinPacking getRobotArmQueueBinPacking() {
        return robotArmQueueBinPacking;
    }


    /**
     * Function to process a package from the robotarmqueue (fork) to a box.
     * Also removes the package from the robotarmqueue list.
     */
    public void processPackageFromFork() {
        if (getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() > 1) {
            PackageBinPacking packageBinPacking = getRobotArmQueueBinPacking().getRobotArmQueuePackages().get((getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() - 1));
            addPackageInBox(packageBinPacking);
            getRobotArmQueueBinPacking().getRobotArmQueuePackages().remove((getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() - 1));
        } else if (getRobotArmQueueBinPacking().getRobotArmQueuePackages().size() == 1) {
            PackageBinPacking packageBinPacking = getRobotArmQueueBinPacking().getRobotArmQueuePackages().get(0);
            addPackageInBox(packageBinPacking);
            getRobotArmQueueBinPacking().getRobotArmQueuePackages().remove(0);
        }
    }

    /**
     * Function to check if a package can be added to the list of packages from a box.
     * if it's not possible, closeFullestBox() will be called on.
     *
     * @param packageBinPacking
     */
    public void addPackageInBox(PackageBinPacking packageBinPacking) {
        boolean isPackageAddedInBox = false;
        while (!isPackageAddedInBox) {
            for (int i = 0; i < boxListBinPacking.getBoxBinPackingsList().size(); i++) {
                BoxBinPacking box = boxListBinPacking.getBoxBinPackingsList().get(i);
                if (box.getBoxIsOpen() && box.getOpenSpace() >= packageBinPacking.getWeight()) {
                    box.addPackageInBox(packageBinPacking);
                    isPackageAddedInBox = true;
                    //#todo
                    //send message to arduino to process the next package for the packing process
                    break;
                }
            }
            if (!isPackageAddedInBox) {
                //#todo
                //send message to arduino to replace the box/wait till box is replaced
                closeFullestBox();
            }
        }
    }

    /**
     * Function to check which box to close off/keep open if a package doesnt fit in either open boxes.
     */
    public void closeFullestBox() {
        BoxBinPacking fullBox = null;
        int spaceInFullBox = 10;
        for (int i = 0; i < getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking boxBinPacking = getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if (boxBinPacking.getBoxIsOpen() && boxBinPacking.getOpenSpace() < spaceInFullBox) {
                fullBox = boxBinPacking;
                spaceInFullBox = boxBinPacking.getOpenSpace();
            }
        }
        fullBox.setBoxIsOpen(false);
        boxListBinPacking.addNewBox(fullBox.getBoxLocation());
        fullBox.setBoxLocation(0);
    }

}
