package gui.controller;

import database.model.Order;
import gui.model.LoadModel;
import gui.model.LoadQueue;
import gui.view.LoadView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;


public class LoadController {

    private final CardLayout layout;
    private final JPanel root;


    public LoadController(CardLayout layout, JPanel root) {
        this.layout = layout;
        this.root = root;
    }

    public void addLoadButton(ActionEvent e){
        try {

            int y= Integer.parseInt(LoadView.YInput.getText());
            int x= Integer.parseInt(LoadView.XInput.getText());
            int item= Integer.parseInt(LoadView.itemnummerInput.getText());
            if(CheckLocationPossession(x,y)){
                setErrorMessage("");
                LoadQueue.addQueue(y,x,item);
            }else{
                setErrorMessage("Locatie al bezet");
            }


        }catch (NumberFormatException error) {
            setErrorMessage("Vul in elk veld een getal in");
        }

    }

    public void CancelLoadButton(ActionEvent e){
        LoadQueue.removeQueue();
    }

    public boolean CheckLocationPossession(int x, int y){
        return true;
    }


    public static void setErrorMessage(String errorMessage) {
        LoadView.Error.setText(errorMessage);
        LoadView.Error.repaint();
    }

    public boolean loadpackage(){
        return true;
    }

}
