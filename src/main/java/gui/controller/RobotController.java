package gui.controller;

import gui.view.RackView;
import gui.view.RobotView;

import javax.swing.*;

public class RobotController extends RobotView{
    private static int Xpositie=0;
    private static int Ypositie=0;


    public static void setXpositie(int xpositie) {
        Xpositie = (xpositie/10)+40;

    }


    public static void setYpositie(int ypositie) {
        Ypositie = (ypositie/10)-20;


    }

    public static int getXpositie() {
        return Xpositie;
    }

    public static int getYpositie() {
        return Ypositie;
    }
}
