package gui.view;

import constants.Constants;
import gui.ViewBuilder;
import gui.controller.NavbarController;

import javax.swing.*;
import java.awt.*;

public class NavbarView extends JPanel implements ViewBuilder {

    private final NavbarController navbarController;

    public NavbarView(CardLayout cardLayout, JPanel root) {
        this.navbarController = new NavbarController(cardLayout, root);
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10));
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.setBorder(BorderFactory.createMatteBorder(0,0,1,0, Color.BLACK));

        JButton homeButton = new JButton("home");
        homeButton.addActionListener(navbarController::homeButton);
        this.add(homeButton);

        JButton settingsButton = new JButton("settings");
        this.add(settingsButton);

        JButton orderButton = new JButton("orders");
        orderButton.addActionListener(navbarController::orderButton);
        this.add(orderButton);

        this.setVisible(true);
    }
}
