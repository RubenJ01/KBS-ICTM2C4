package gui.view;

import gui.MainFrame;

import javax.swing.*;

public class OrderView extends JPanel {

    private final MainFrame container;

    public OrderView(MainFrame container) {
        this.container = container;
        this.setSize(500, 500);
        this.add(new JButton("test"));

        this.setVisible(true);
    }
}
