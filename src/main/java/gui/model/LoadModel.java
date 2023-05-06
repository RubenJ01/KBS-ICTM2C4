package gui.model;

import gui.view.LoadView;

import java.awt.*;

public class LoadModel {
    private int locationX;
    private int locationY;
    private int itemnummer;

    private Color color;

    public LoadModel(int locationY,int locationX,int itemnummer){
        this.locationY= locationY;
        this.locationX= locationX;
        this.itemnummer=itemnummer;

    }


    public String toString() {
        return "itemnummer: "+itemnummer+" X: "+locationX+" Y: "+locationY;
    }
}


