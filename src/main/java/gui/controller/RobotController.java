package gui.controller;

import gui.model.PackageModel;
import gui.view.RobotView;

import java.awt.*;

public class RobotController extends RobotView{
    private static float Xpositie=0;
    private static float Ypositie=0;

    private static int XpositieTSP=0;
    private static int YpositieTSP=0;

    private static PackageModel load;


    public static void setXpositie(int xpositie) {
        Xpositie = (float) ((xpositie/9.6)+40);
        XpositieTSP=xpositie;
    }


    public static void setYpositie(int ypositie) {
        Ypositie = (float) (ypositie/9.6);
        YpositieTSP=ypositie;


    }

    public static float getXpositie() {
        return Xpositie;
    }

    public static float getYpositie() {
        return Ypositie;
    }

    public static void setLoad(PackageModel load1) {
        load = load1;
    }

    public static void removeLoad(){
        load=null;
    }

    public static PackageModel getLoad(){
        return load;
    }

    public static void paintLoad(Graphics g, float xpositie, float ypositie){
        load.paintComponent(g,ypositie,xpositie);
    }

    public static int getXpositieTSP() {
        return XpositieTSP;
    }

    public static int getYpositieTSP() {
        return YpositieTSP;
    }
}
