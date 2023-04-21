package gui.view;

import gui.ViewBuilder;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JPanel implements ViewBuilder {

    private final NavbarView navbarView;

    public OrderView(CardLayout layout, JPanel root, NavbarView navbarView) {
        this.navbarView = navbarView;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());

        this.add(navbarView, BorderLayout.NORTH);

        this.add(new JLabel("test"), BorderLayout.CENTER);

        this.setVisible(true);
    }
}
