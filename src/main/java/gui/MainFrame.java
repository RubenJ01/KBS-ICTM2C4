package gui;

import gui.view.OrderView;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private final CardLayout cardLayout;

    public MainFrame() {
        this.cardLayout = new CardLayout();
        this.setSize(500, 500);


        this.setVisible(true);
    }



    private void addCards() {
        this.cardLayout.addLayoutComponent(new OrderView(this), "orderView");
    }
}
