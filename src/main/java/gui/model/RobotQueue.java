package gui.model;

import database.dao.RackDao;
import database.util.DatabaseConnection;
import gui.MainWindow;
import gui.controller.RackController;
import gui.controller.RobotController;
import gui.view.PackageView;
import gui.view.dialog.PlacePackageDialog;
import serial.SerialCommunication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.concurrent.Executors;

public class RobotQueue {
    public static ArrayList<PackageModel> queue=new ArrayList<>();

    public RobotQueue(){

    }


    public static void printQueue() {
        if(queue.size()>0){
            System.out.println("wachtrij:");
            for(int i = 0; i < queue.size(); i++) {
                System.out.println(i+": "+queue.get(i));
            }
        }else{
            System.out.println("er is geen wachtrij");
        }
    }

    public static void addQueue(PackageModel packageModel, boolean inladen){
        //voegt pakketje toe aan wachtrij en als de wachtrij 0 is dan voert die de opdracht meteen uit
        if(inladen){
            queue.add(packageModel);
            printQueue();
            PackageView.model.addElement("Inladen Product Id: "+packageModel.getItemnummer()+" X:"+packageModel.getLocationX()+" Y:"+packageModel.getLocationY());
        } else if (!inladen) {
            queue.add(packageModel);
            printQueue();
            PackageView.model.addElement("Uitladen Product Id: "+packageModel.getItemnummer()+" X:"+packageModel.getLocationX()+" Y:"+packageModel.getLocationY());
        }
        System.out.println("Lengte wachtrij: "+queue.size());
        if(queue.size()==1){
            executeQueue();
        }



    }
    public static void executeQueue(){
        //als er iets in de rij staat gaat die door
        try {
        if(queue.size()>=1) {
                PackageModel item=queue.get(0);
                //kijkt of item al in het magazijn staat of niet

                    SerialCommunication.writeToSerial(item.getLocationX(), item.getLocationY(),2);
                    RobotController.setLoad(item);


            } else {
                System.out.println("wachtrij is leeg");
            }

        } catch (ConcurrentModificationException e) {
            System.err.println("opdracht gecancelled");
        }
    }


    public static void removeQueue() {
        PackageView.model.removeAllElements();
        MainWindow.model.removeAllElements();

        queue.clear();
    }

    public static void removeFirstItem(PackageModel packageModel) {
        queue.remove(packageModel);
        PackageView.model.removeAllElements();
        for (PackageModel item : queue) {
            PackageView.model.addElement(item.toString());
            MainWindow.model.addElement(item.toString());
        }
    }

    public static void inladen(PackageModel packageModel) {
        int x = packageModel.getLocationX();
        int y = packageModel.getLocationY();
        SerialCommunication.writeToSerial(x,y,1);
        RobotController.setLoad(packageModel);
    }

    public static boolean CheckIfLoadInRack(int x,int y) {
        for (PackageModel item : RackModel.rack) {
            if(x==item.getLocationX()&&y==item.getLocationY()){
                return true;
            }
        }
        return false;
    }

    public static void RobotBereikt(PackageModel packageModel){
        RobotQueue.removeFirstItem(packageModel);
        RobotController.removeLoad();
        SerialCommunication.setMeldingRobot("");
        System.out.println("Robot heeft opdracht voltooid");
        RackModel.addToRack(packageModel);
        RackModel.printRack();
        RobotQueue.printQueue();
        executeQueue();
    }



}
