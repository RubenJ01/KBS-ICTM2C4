package gui.controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class NavbarController {

    private final CardLayout cardLayout;
    private final JPanel root;

    public NavbarController(CardLayout cardLayout, JPanel root) {
        this.cardLayout = cardLayout;
        this.root = root;
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void orderButton(ActionEvent e) {
        cardLayout.show(root, "orderView");
    }

    /**
     * This method is called when the home button is clicked.
     */
    public void homeButton(ActionEvent e) {
        cardLayout.show(root, "main");
    }

    /**
     * Methode die aangeroepen wordt als de knop wordt ingedrukt
     */
    public void stockButton(ActionEvent e) {
        cardLayout.show(root, "stockView");
    }

    public void loadButton(ActionEvent e) {
        cardLayout.show(root, "loadView");
    }

}
