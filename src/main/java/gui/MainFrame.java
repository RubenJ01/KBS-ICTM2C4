package gui;

import gui.view.OrderView;

import javax.swing.*;
import java.awt.*;

public class MainFrame implements ViewBuilder {

    public MainFrame() {
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        JFrame mainWindow = new JFrame();
        mainWindow.setLayout(new BorderLayout());
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setSize(500, 500);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.add(addViews());
        mainWindow.setVisible(true);
    }

    private JSplitPane addViews() {
        JPanel root = new JPanel();
        CardLayout layout = new CardLayout();

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.add(root);
        splitPane.setResizeWeight(0.8);

        root.setLayout(layout);

        root.add("orderView", new OrderView(layout, root));

        root.add("main", new MainWindow(layout, root));

        return splitPane;
    }
}
