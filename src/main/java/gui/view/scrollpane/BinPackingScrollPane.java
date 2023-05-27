package gui.view.scrollpane;

import constants.Constants;

import javax.swing.*;
import java.awt.*;

public class BinPackingScrollPane extends JScrollPane {

    public BinPackingScrollPane(){
        this.setLayout(new ScrollPaneLayout());
        this.setBackground(Color.RED);
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT));

        this.setVisible(true);
    }
}
