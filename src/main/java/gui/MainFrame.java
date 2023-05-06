package gui;

import constants.Constants;
import gui.view.OrderView;
import gui.view.StockView;
import gui.view.LoadView;

import javax.swing.*;
import java.awt.*;

/**
 * This is the MainFrame of our application.
 */
public class MainFrame implements ViewBuilder {

    public MainFrame() {
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        getBounds();
        JFrame mainWindow = new JFrame();
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setExtendedState(mainWindow.getExtendedState() | Frame.MAXIMIZED_BOTH);
        mainWindow.add(addViews());
        mainWindow.setVisible(true);
    }

    /**
     * Gets the default screenWidth and screenHeight for the device the application is run on.
     * The screenWidth and screenHeight are then saved in the constants, so they can easily be accessed elsewhere.
     */
    private static void getBounds() {
        Rectangle bounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice()
                .getDefaultConfiguration().getBounds();
        Constants.SCREEN_WIDTH = bounds.width;
        Constants.SCREEN_HEIGHT = bounds.height;
        Constants.SCREEN_DIMENSIONS = new Dimension(bounds.width, bounds.height);
    }

    /**
     * In this method we create a CardLayout which holds all the JPanels in our applications.
     * Every new JPanel will be initialized in this method.
     * @return JSplitPane
     */
    private JSplitPane addViews() {
        JPanel root = new JPanel();
        CardLayout cardLayout = new CardLayout();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(root);
        splitPane.setResizeWeight(0.8);

        root.setLayout(cardLayout);


        // the first panel added to the card cardLayout will be the first to be visible
        root.add("main", new MainWindow(cardLayout, root));
        root.add("orderView", new OrderView(cardLayout, root));
        root.add("stockView", new StockView(cardLayout, root));
        root.add("loadView", new LoadView(cardLayout, root));
        return splitPane;
    }
}
