package gui.view;

import gui.ViewBuilder;

import javax.swing.*;
import java.awt.*;

public class OrderView extends JPanel implements ViewBuilder {

    private final CardLayout cardLayout;
    private final JPanel root;

    public OrderView(CardLayout layout, JPanel root) {
        this.cardLayout = layout;
        this.root = root;
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setSize(500, 500);

        JButton pfiew = new JButton("pfiew");
        pfiew.addActionListener(e -> cardLayout.show(root, "main"));
        this.add(pfiew);

        this.setVisible(true);
    }
}
