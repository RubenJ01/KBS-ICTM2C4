package gui.controller;

import gui.model.BinPackingModel;
import gui.model.BoxListBinPacking;
import gui.model.PackageBinPacking;
import gui.view.BinPackingView;
import gui.view.panel.BinPackingBoxesPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class BinPackingController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
    private final CardLayout layout;
    private final JPanel root;
    private final BinPackingView binPackingView;
    private final BoxListBinPacking boxListBinPacking;
    private final BinPackingBoxesPanel binPackingBoxesPanel;
    private final BinPackingModel binPackingModel;

    public BinPackingController(CardLayout layout, JPanel root, BinPackingView binPackingView, BoxListBinPacking boxListBinPacking, BinPackingBoxesPanel binPackingBoxesPanel, BinPackingModel binPackingModel) {
        this.layout = layout;
        this.root = root;
        this.binPackingView = binPackingView;
        this.boxListBinPacking = boxListBinPacking;
        this.binPackingBoxesPanel = binPackingBoxesPanel;
        this.binPackingModel = binPackingModel;
    }

    public void informationButton(ActionEvent e) {
        JOptionPane.showMessageDialog(null, "Groene pakketten = 2kg\n" +
                "Blauwe pakketten = 5kg\n" +
                "Rode pakketten = 7kg\n" +
                "De maximale inhoud van de dozen = 10kg");
    }
    public void fork1Button(ActionEvent e){
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(7, 216, 4, 2));
        binPackingView.repaintScreen();
    }
    public void fork2Button(ActionEvent e){
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(2, 110, 4, 2));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(5, 1, 3, 2));
        binPackingView.repaintScreen();
    }
    public void fork3Button(ActionEvent e){
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(2, 24, 4, 2));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(7, 29, 3, 2));
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().add(new PackageBinPacking(7, 30, 2, 2));
        binPackingView.repaintScreen();
    }
    public void processButton(ActionEvent e){
        binPackingModel.processPackageFromFork();
        binPackingView.repaintScreen();
    }

    public void clearAllButton(ActionEvent e){
        binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().clear();
        binPackingModel.getBoxListBinPacking().startNewOrder();
        binPackingView.repaintScreen();
    }
}
