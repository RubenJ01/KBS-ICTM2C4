package gui.model;

import gui.controller.RobotController;

import javax.swing.*;
import java.awt.*;

public class PackageModel extends JPanel {
    private  int locationX;
    private  int locationY;
    private  int locationPanelX;
    private  int locationPanelY;
    private  int itemnummer;

    private int weight;

    private boolean inMagazijn;

    private Color color;

    public PackageModel(int locationY, int locationX, int itemnummer, int weight,boolean inMagazijn) {
        this.locationY = locationY;
        this.locationX = locationX;
        this.itemnummer = itemnummer;
        this.weight=weight;
        locationConvertToPanel(locationY,locationX);
        this.inMagazijn=inMagazijn;
        if(weight==2){
            this.color=Color.green;
        } else if (weight==5) {
            this.color=Color.blue;
        } else if (weight==7) {
            this.color=Color.red;
        }else{
            this.color=Color.red;
        }

    }
    public PackageModel(int itemnummer, int weight) {
        this.locationY = 1;
        this.locationX = 8;
        this.itemnummer = itemnummer;
        this.weight=weight;
        locationConvertToPanel(1,8);
        if(weight==2){
            this.color=Color.green;
        } else if (weight==5) {
            this.color=Color.blue;
        } else if (weight==7) {
            this.color=Color.red;
        }else{
            this.color=Color.red;
        }

    }

    public void locationConvertToPanel(int locationY,int locationX){
        locationPanelX=150*(locationX-1)+38;
        locationPanelY=600-(100*(locationY)+5);
    }

    public boolean isInMagazijn() {
        return inMagazijn;
    }

    public void setInMagazijn(boolean inMagazijn) {
        this.inMagazijn = inMagazijn;
    }

    public  int getLocationX() {
        return locationX;
    }

    public  int getLocationY() {
        return locationY;
    }
    public int getItemnummer() {
        return itemnummer;
    }

    public int getWeight() {
        return weight;
    }

    public Color getColor() {
        return color;
    }

    public String toString() {
       return "itemnummer: " + itemnummer + " X: " + locationX + " Y: " + locationY+" Grootte: "+weight;
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        int packageWidth=75;
        int packageHeight=50;
        g.fillRect(locationPanelX,locationPanelY,packageWidth,packageHeight);
        g.setColor(Color.black);
        Font font = new Font("Calibri", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("ID: "+String.valueOf(itemnummer),locationPanelX,locationPanelY+25);
    }

    public void paintComponent(Graphics g,float y,float x) {
        super.paintComponent(g);
        g.setColor(color);
        int packageWidth=75;
        int packageHeight=50;
        g.fillRect((int) x-(packageWidth/2), (int) y-(packageHeight/2),packageWidth,packageHeight);
        g.setColor(Color.black);
        Font font = new Font("Calibri", Font.BOLD, 16);
        g.setFont(font);
        g.drawString("ID: "+String.valueOf(itemnummer),(int) x-(packageWidth/2),(int) y);
    }

}


