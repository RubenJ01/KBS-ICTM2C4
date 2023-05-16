package gui.controller;

import gui.MainFrame;
import gui.model.PackageModel;
import gui.model.RackModel;
import gui.model.RobotQueue;
import gui.view.PackageView;
import serial.SerialCommunication;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

import static gui.model.RobotQueue.executeQueue;


public class PackageController extends RackModel {

    private final CardLayout layout;
    private final JPanel root;


    public PackageController(CardLayout layout, JPanel root) {
        this.layout = layout;
        this.root = root;
    }

    public void addLoadButton(ActionEvent e){
        try {
            //verzamelen X en Y waarde uit de velden
            int grootte=Integer.parseInt(PackageView.SizeInput.getText());
            int y= Integer.parseInt(PackageView.YInput.getText());
            int x= Integer.parseInt(PackageView.XInput.getText());
            int item= Integer.parseInt(PackageView.itemnummerInput.getText());

            if(CheckLocationPossession(x,y)){
                PackageModel packageModel=new PackageModel(y,x,item,grootte,false);
                RobotQueue.addQueue(packageModel,true);

            }else{
                JOptionPane.showMessageDialog(MainFrame.mainWindow, "Locatie al bezet","Waarschuwing", JOptionPane.ERROR_MESSAGE);
            }


        }catch (NumberFormatException error) {
            JOptionPane.showMessageDialog(MainFrame.mainWindow, "Vul in elk veld een getal in","Waarschuwing", JOptionPane.ERROR_MESSAGE);

        }

    }

    public void CancelLoadButton(ActionEvent e){
        RobotQueue.removeQueue();
    }




    public boolean loadpackage(){

        return true;
    }

}
