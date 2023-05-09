package gui.controller;

import gui.model.RobotQueue;
import gui.view.LoadView;
import serial.SerialCommunication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;


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
                RobotQueue.addQueue(y,x,item);
                RobotQueue.addQueue(y,x,item,true);

                //serial comm
                SerialCommunication.getInstance().sendData();
            }else{
                setErrorMessage("Locatie al bezet");
            }


        }catch (NumberFormatException error) {
            setErrorMessage("Vul in elk veld een getal in");
        }

    }

    public void CancelLoadButton(ActionEvent e){
        RobotQueue.removeQueue();
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
