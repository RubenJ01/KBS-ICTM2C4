package gui.view;

import gui.MainFrame;
import gui.controller.RobotController;
import gui.model.PackageModel;
import gui.model.RackModel;

import javax.swing.*;
import java.awt.*;

import static gui.model.RackModel.rack;

public class RackView extends JPanel {
    private int rackHoogte=600;
    private int rackBreedte=750;
    private  int positionX=1000;
    private  int positionY=1000;

    private int oldPositionX;
    private int oldPositionY;
    public RackView() {

    }

    public void paintPackages(Graphics g){
        for (PackageModel item : rack) {
            item.paintComponent(g);
        }
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);


        //inladen Pakketjes
        paintPackages(g);


        //tekenen rack Y-as
        g.setColor(Color.black);
        g.fillRect(0,0,5,rackHoogte);
        g.fillRect(150,0,5,rackHoogte);
        g.fillRect(300,0,5,rackHoogte);
        g.fillRect(450,0,5,rackHoogte);
        g.fillRect(600,0,5,rackHoogte);
        g.fillRect(750,0,5,rackHoogte);

        //tekenen rack X-as
        g.setColor(Color.black);
        g.fillRect(0,40,rackBreedte,5);
        g.fillRect(0,140,rackBreedte,5);
        g.fillRect(0,240,rackBreedte,5);
        g.fillRect(0,340,rackBreedte,5);
        g.fillRect(0,440,rackBreedte,5);
        g.fillRect(0,540,rackBreedte,5);

        //tekenen Robot
        RobotView.paintRobot(g);
        repaint();
    }
}
