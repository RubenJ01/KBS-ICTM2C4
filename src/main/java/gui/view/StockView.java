package gui.view;

import constants.Constants;
import gui.ViewBuilder;
import gui.model.StockModel;
import gui.controller.StockController;
import org.yaml.snakeyaml.scanner.Constant;

import javax.swing.*;
import javax.swing.text.View;
import java.awt.*;

public class StockView extends JPanel implements ViewBuilder {

    private final NavbarView navbarView;
    private StockModel stockModel;
    private StockController stockController;
    private int yID, yStock;

    public StockView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.stockModel = new StockModel();
        buildAndShowView();
        yID = 150;
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);
        this.setVisible(true);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        yID = 150;
        g2.setStroke(new BasicStroke(2));
        g2.drawLine(0, (Constants.SCREEN_HEIGHT/8), Constants.SCREEN_WIDTH, (Constants.SCREEN_HEIGHT/8));
        g2.setFont(new Font("default", Font.PLAIN, 14));
        g2.drawString("Product", (Constants.SCREEN_WIDTH/8)-20, 75);
        g2.drawString("Voorraad", (Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3)-40, 75);
        g2.drawLine(Constants.SCREEN_WIDTH/4, (Constants.SCREEN_HEIGHT/8), Constants.SCREEN_WIDTH/4, Constants.SCREEN_HEIGHT);
        drawProducts(g2);
    }


    /**
     * zowel het productID als de voorraad op het scherm zetten
     * @param g
     */
    public void drawProducts(Graphics g){
        for(int i = 0; i < stockModel.getProductStock().size(); i++){
            g.drawString(String.valueOf(i+1), (Constants.SCREEN_WIDTH/8)-20, yID);
            g.drawString(String.valueOf(stockModel.getProductStock().get(i)), (Constants.SCREEN_WIDTH-Constants.SCREEN_WIDTH/3)-20, yID);
            yID += 75;
        }
    }
}
