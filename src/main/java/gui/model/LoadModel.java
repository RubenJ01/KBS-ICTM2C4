package gui.model;

import java.awt.*;

public class LoadModel {
    private static int locationX;
    private static int locationY;
    private int itemnummer;

    private Color color;

    public LoadModel(int locationY, int locationX, int itemnummer) {
        this.locationY = locationY;
        this.locationX = locationX;
        this.itemnummer = itemnummer;

    }
    public static int getLocationX() {
        return locationX;
    }

    public static int getLocationY() {
        return locationY;
    }


    public String toString() {
        return "itemnummer: " + itemnummer + " X: " + locationX + " Y: " + locationY;
    }
}


