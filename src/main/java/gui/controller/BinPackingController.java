package gui.controller;

import gui.model.BinPackingModel;
import gui.model.PackageModel;
import gui.view.BinPackingView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class BinPackingController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final BinPackingView binPackingView;
    private final BinPackingModel binPackingModel;

    public BinPackingController(BinPackingView binPackingView, BinPackingModel binPackingModel) {
        this.binPackingView = binPackingView;
        this.binPackingModel = binPackingModel;
    }

    /**
     * Information button to open dialog to show the info on the packages and the boxes.
     *
     * @param e
     */
    public void informationButton(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Groene pakketten = 2kg\n" +
                "Blauwe pakketten = 5kg\n" +
                "Rode pakketten = 7kg\n" +
                "De maximale inhoud van de dozen = 10kg");
    }

    /**
     * TestButton to simulate adding 1 package to the fork
     *
     * @param e
     */
    public void fork1Button(ActionEvent e) {
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(2, 5));
        binPackingView.repaintScreen();
    }

    /**
     * TestButton to simulate adding 2 package to the fork
     *
     * @param e
     */
    public void fork2Button(ActionEvent e) {
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(110, 2));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(1, 5));
        binPackingView.repaintScreen();
    }

    /**
     * TestButton to simulate adding 3 package to the fork
     *
     * @param e
     */
    public void fork3Button(ActionEvent e) {
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(210, 2));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(29, 7));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageModel(30, 7));
        binPackingView.repaintScreen();
    }

    /**
     * TestButton to simulate processing a package from the fork to a box with a bin packing algorithm
     *
     * @param e
     */
    public void processButton(ActionEvent e) {
        binPackingModel.processPackageFromFork();
        binPackingView.repaintScreen();
    }

    /**
     * TestButton to clear out all the lists and boxes
     *
     * @param e
     */
    public void clearAllButton(ActionEvent e) {
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getBoxListBinPacking().startNewOrder();
        binPackingView.repaintScreen();
    }
}
