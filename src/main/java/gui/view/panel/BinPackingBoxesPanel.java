package gui.view.panel;

import gui.ViewBuilder;
import gui.model.BinPackingModel;

import javax.swing.*;
import java.awt.*;

public class BinPackingBoxesPanel extends JPanel implements ViewBuilder {
    private final BinPackingModel binPackingModel;

    public BinPackingBoxesPanel(BinPackingModel binPackingModel) {
        this.binPackingModel = binPackingModel;
        buildAndShowView();
        this.setVisible(true);
    }

    @Override
    public void buildAndShowView() {
        showPackagesInBoxesText();
        this.setVisible(true);
    }

    /**
     * Function to get the information of the packages in a box to show up on the scrollpane list
     * on the right side of the screen
     * It deletes all panels and after rebuilds the new/correct panels with the correct information
     */
    public void showPackagesInBoxesText() {
        this.setLayout(new GridLayout(binPackingModel.getBoxListBinPacking().getBoxCount(), 1));
        this.removeAll();
        //for loop to get the box
        for (int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            String boxLabel = "Doos " + (i + 1);
            if (binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getBoxIsOpen()) {
                boxLabel += " (Open: " + binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getOpenSpace() + ")";
            } else {
                boxLabel += " (Gesloten: " + binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getOpenSpace() + ")";
            }
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
            jPanel.add(new JLabel(boxLabel));
            JList<String> packageList = new JList<>();
            String[] packages = new String[5];

            //for loop to get the packages in the list of packages of a box
            for (int j = 0; j < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getPackagesInBox().size(); j++) {
                packages[j] = binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getPackagesInBox().get(j).toString();
            }
            packageList.setListData(packages);
            JScrollPane jScrollPane = new JScrollPane(packageList);
            jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
            jPanel.add(jScrollPane);
            this.add(jPanel);
        }
    }


}