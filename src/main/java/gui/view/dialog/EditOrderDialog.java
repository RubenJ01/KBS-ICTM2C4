package gui.view.dialog;

import gui.ViewBuilder;

import javax.swing.*;
import java.awt.*;

public class EditOrderDialog extends JDialog implements ViewBuilder {

    public EditOrderDialog() {
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(425, 280));

        JLabel header = new JLabel("Order Bewerken", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 2, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        this.add(header, BorderLayout.NORTH);

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new GridLayout(6, 2));
        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(centerContent, BorderLayout.CENTER);
    }
}
