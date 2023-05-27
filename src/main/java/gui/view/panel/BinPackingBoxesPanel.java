package gui.view.panel;

import gui.ViewBuilder;
import gui.model.BinPackingModel;

import javax.swing.*;
import java.awt.*;

public class BinPackingBoxesPanel extends JPanel implements ViewBuilder {
    private final BinPackingModel binPackingModel;

    public BinPackingBoxesPanel(BinPackingModel binPackingModel){
        this.binPackingModel = binPackingModel;
        buildAndShowView();
//        JList<String> strings = new JList<>();
//        strings.setListData(data);

//        JScrollPane jPanels = new JScrollPane(strings);
//        jPanels.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

//        JPanel jPanel = new JPanel(); aanmaken panel
//        jPanel.setBackground(Color.WHITE);
//        jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS)); layout zetten
//        jPanel.add(new JLabel("Doos 1")); label toevoegen
//        jPanel.add(jPanels); scrollpane

//        this.add(jPanel);
    }

    @Override
    public void buildAndShowView() {
        showPackagesInBoxesText();
        this.setVisible(true);
    }

    public void showPackagesInBoxesText(){
        this.setLayout(new GridLayout(binPackingModel.getBoxListBinPacking().getBoxCount(), 1));
        this.removeAll();
        for(int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++){
            String boxLabel = "Doos " + (i+1);
            if(binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getBoxIsOpen()){
                boxLabel += " (Open: " + binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getOpenSpace() + ")";
            }
            else{
                boxLabel += " (Gesloten: " + binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getOpenSpace() + ")";
            }
            JPanel jPanel = new JPanel();
            jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));
            jPanel.add(new JLabel(boxLabel));
            JList<String> packageList = new JList<>();
            String[] packages = new String[5];
            for(int j = 0; j < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i).getPackagesInBox().size(); j++) {
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