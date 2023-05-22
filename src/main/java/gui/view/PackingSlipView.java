package gui.view;

import gui.ViewBuilder;

import javax.swing.*;
import java.awt.*;

public class PackingSlipView extends JPanel implements ViewBuilder {
    private JLabel nameLabel;
    private JLabel addressLabel;
    private JLabel itemLabel;
    private JLabel quantityLabel;
    private JLabel priceLabel;

    public PackingSlipView() {
        setLayout(new GridLayout(5, 2));

        nameLabel = new JLabel("Name: ");
        addressLabel = new JLabel("Address: ");
        itemLabel = new JLabel("Item: ");
        quantityLabel = new JLabel("Quantity: ");
        priceLabel = new JLabel("Price: ");

        JTextField nameTextField = new JTextField();
        JTextField addressTextField = new JTextField();
        JTextField itemTextField = new JTextField();
        JTextField quantityTextField = new JTextField();
        JTextField priceTextField = new JTextField();

        add(nameLabel);
        add(nameTextField);
        add(addressLabel);
        add(addressTextField);
        add(itemLabel);
        add(itemTextField);
        add(quantityLabel);
        add(quantityTextField);
        add(priceLabel);
        add(priceTextField);
    }

    @Override
    public void buildAndShowView() {

    }
}