package gui;

import gui.view.NavbarView;

import javax.swing.*;
import java.awt.*;

/**
 * This is the main window.
 * It's the first window the user sees when the application is started.
 */
public class MainWindow extends JPanel implements ViewBuilder {

    private final NavbarView navbarView;

    /**
     * @param layout CardLayout we pass the CardLayout to every panel,
     *               so we can easily swap between panels.
     * @param root   JPanel we pass the root panel to every panel,
     *               which is required to swap between panels with the card layout.
     */
    public MainWindow(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT));
        panel.setBackground(Color.gray);
        add(panel);
        this.setVisible(true);

    }

    public void paintComponent(Graphics g) {

    }
}