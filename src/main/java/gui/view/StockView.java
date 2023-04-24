package gui.view;

import gui.model.StockModel;
import gui.controller.StockController;

import javax.swing.*;
import java.awt.*;

public class StockView extends JFrame {
    private StockModel stockModel;
    private StockController stockController;

    public StockView() {
        setTitle("Stock view");
        setSize(650, 450);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);



        setVisible(true);
    }
}
