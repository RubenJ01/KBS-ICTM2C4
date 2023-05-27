package gui.model;

import java.awt.*;

public class PackageModel {
    private  int locationX;
    private  int locationY;
    private  int itemnummer;
    private int grootte;
    private boolean inMagazijn;

    public PackageModel(int locationY, int locationX, int itemnummer, int grootte,boolean inMagazijn) {
        this.locationY = locationY;
        this.locationX = locationX;
        this.itemnummer = itemnummer;
        this.grootte=grootte;
        this.inMagazijn=inMagazijn;

    }
    public PackageModel(int itemnummer, int grootte) {
        this.locationY = 1;
        this.locationX = 8;
        this.itemnummer = itemnummer;
        this.grootte=grootte;

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

    public int getGrootte() {
        return grootte;
    }

    public String toString() {
       return "itemnummer: " + itemnummer + " X: " + locationX + " Y: " + locationY+" Grootte: "+grootte;
    }
}


