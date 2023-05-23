package gui;

import constants.Constants;
import gui.controller.RobotController;
import gui.view.NavbarView;

import javax.swing.*;
import java.awt.*;
import constants.Constants;
import gui.view.RackView;
import gui.view.RobotView;

/**
 * This is the main window.
 * It's the first window the user sees when the application is started.
 */
public class MainWindow extends JPanel implements ViewBuilder {

    private final NavbarView navbarView;
    private final RackView rackView;

    public static DefaultListModel<String> model = new DefaultListModel<>();
    private  JPanel rackPanel;


    /**
     * @param layout CardLayout we pass the CardLayout to every panel,
     *               so we can easily swap between panels.
     * @param root   JPanel we pass the root panel to every panel,
     *               which is required to swap between panels with the card layout.
     */
    public MainWindow(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.rackView=new RackView();
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setPreferredSize(new Dimension(1000, 600));
        panel.setBackground(Color.gray);

        rackPanel=new JPanel();
        rackPanel.setPreferredSize(new Dimension(1000, 600));
        rackPanel.setBackground(Color.green);
        rackPanel.setLayout(new GridLayout());
        rackPanel.add(new RackView());





        add(panel);
        panel.add(rackPanel,BorderLayout.CENTER);
        this.setVisible(true);

    }


}