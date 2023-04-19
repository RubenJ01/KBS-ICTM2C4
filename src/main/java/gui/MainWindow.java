package gui;

import javax.swing.*;
import java.awt.*;

public class MainWindow extends JPanel implements ViewBuilder {

    private final CardLayout cardLayout;
    private final JPanel root;

    public MainWindow(CardLayout layout, JPanel root) {
        this.cardLayout = layout;
        this.root = root;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());

        JButton test = new JButton("test");
        test.addActionListener(e -> {
            cardLayout.show(root, "orderView");
        });
        this.add(test);

        this.setVisible(true);
    }
}