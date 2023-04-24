package gui;

import constants.Constants;
import gui.view.NavbarView;

import javax.swing.*;
import java.awt.*;

/**
 * This is the main window.
 * It's the first window the user sees when the application is started.
 */
public class MainWindow extends JPanel implements ViewBuilder {

    private final CardLayout cardLayout;
    private final JPanel root;
    private final NavbarView navbarView;

    /**
     * @param layout CardLayout we pass the CardLayout to every panel,
     *               so we can easily swap between panels.
     * @param root JPanel we pass the root panel to every panel,
     *             which is required to swap between panels with the card layout.
     * @param navbarView NavbarView we pass the navbar to each JPanel, so we can add it to every panel.
     * @see NavbarView
     */
    public MainWindow(CardLayout layout, JPanel root, NavbarView navbarView) {
        this.cardLayout = layout;
        this.root = root;
        this.navbarView = navbarView;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));

        this.setLayout(new BorderLayout());

        this.add(navbarView, BorderLayout.NORTH);

        this.add(new JLabel("home window"), BorderLayout.CENTER);

        this.setVisible(true);
    }
}