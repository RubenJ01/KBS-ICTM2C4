package gui.view;

import gui.ViewBuilder;
import gui.controller.BinPackingController;
import gui.model.BinPackingModel;
import gui.view.panel.BinPackingBoxesPanel;
import gui.view.panel.BinPackingPanel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class BinPackingView extends JPanel implements ViewBuilder {
    private static final Logger logger = LoggerFactory.getLogger(BinPackingView.class);
    private final NavbarView navbarView;
    private final BinPackingController binPackingController;
    private final BinPackingModel binPackingModel;
    private final BinPackingBoxesPanel binPackingBoxesPanel;
    private final BinPackingPanel binPackingPanel;

    public BinPackingView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.binPackingModel = new BinPackingModel();
        this.binPackingBoxesPanel = new BinPackingBoxesPanel(binPackingModel);
        this.binPackingPanel = new BinPackingPanel(this.binPackingModel);
        this.binPackingController = new BinPackingController(layout, root, this, this.binPackingModel.getBoxListBinPacking(), binPackingBoxesPanel, binPackingModel);
        buildAndShowView();

    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);
        this.add(binPackingPanel, BorderLayout.CENTER);

        //
        JPanel informationPanel = new JPanel();
        informationPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        JButton informationButton = new JButton("Informatie");
        informationButton.addActionListener(binPackingController::informationButton);
        informationPanel.add(informationButton);

        JButton fork1Button = new JButton("1product");
        fork1Button.addActionListener(binPackingController::fork1Button);
        informationPanel.add(fork1Button);

        JButton fork2Button = new JButton("2product");
        fork2Button.addActionListener(binPackingController::fork2Button);
        informationPanel.add(fork2Button);

        JButton fork3Button = new JButton("3product");
        fork3Button.addActionListener(binPackingController::fork3Button);
        informationPanel.add(fork3Button);

        JButton processButton = new JButton("verwerk 1 product");
        processButton.addActionListener(binPackingController::processButton);
        informationPanel.add(processButton);
        this.add(informationPanel,BorderLayout.SOUTH);

        JButton clearAllButton = new JButton("Clear all");
        clearAllButton.addActionListener(binPackingController::clearAllButton);
        informationPanel.add(clearAllButton);
        this.add(informationPanel,BorderLayout.SOUTH);





        JScrollPane scrollPanePanel = new JScrollPane(binPackingBoxesPanel);
        scrollPanePanel.setPreferredSize(new Dimension((int) (Constants.SCREEN_WIDTH/3.2), Constants.SCREEN_HEIGHT));
        scrollPanePanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPanePanel, BorderLayout.EAST);

        this.setVisible(true);
    }

    public BinPackingBoxesPanel getBinPackingBoxesPanel() {
        return binPackingBoxesPanel;
    }

    public void repaintScreen(){
        binPackingBoxesPanel.showPackagesInBoxesText();
        this.repaint();
    }

}
