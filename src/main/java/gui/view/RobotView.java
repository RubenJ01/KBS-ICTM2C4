package gui.view;

import gui.MainFrame;
import gui.controller.RobotController;
import serial.SerialCommunication;

import javax.swing.*;
import javax.swing.text.Position;
import java.awt.*;

public class RobotView {

    private static RobotView instance;

    public RobotView(){
        instance=this;
    }
    public static void paintRobot(Graphics g) {
        int robotSize = 50;
        Graphics2D g2d = (Graphics2D) g.create();

        // Set opacity to 80%
        float opacity = 0.8f;
        AlphaComposite alphaComposite = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity);
        g2d.setComposite(alphaComposite);
        g2d.setColor(Color.black);
        g2d.fillOval(RobotController.getXpositie() - robotSize / 2, (600-RobotController.getYpositie()) - robotSize / 2, robotSize, robotSize);
        g2d.dispose();
    }


}
