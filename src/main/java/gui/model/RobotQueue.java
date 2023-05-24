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

    public static void addQueue(PackageModel loadmodel, boolean inladen){
        if(inladen){
            queue.add(loadmodel);
            printQueue();
            PackageView.model.addElement("Inladen Product Id: "+loadmodel.getItemnummer()+" X:"+loadmodel.getLocationX()+" Y:"+loadmodel.getLocationY());
        } else if (!inladen) {
            queue.add(loadmodel);
            printQueue();
            PackageView.model.addElement("Uitladen Product Id: "+loadmodel.getItemnummer()+" X:"+loadmodel.getLocationX()+" Y:"+loadmodel.getLocationY());
        }
        System.out.println(queue.size());
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
                if (!CheckIfLoadInRack(item.getLocationY(),item.getLocationX())) {
                    //item moet ingeladen worden
                    // robot gaat eerst naar beginpunt
                    SerialCommunication.writeToSerial(6,1,3);
                    //Dialoog wordt aan gemaakt voor het plaatsen van pakket op palletvork
                    PlacePackageDialog placePackageDialog = new PlacePackageDialog(item);
                } else {
                    //item moet worden uitgeladen

                }

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
        // Start de loop in een nieuwe thread
        Executors.newSingleThreadExecutor().execute(() -> {
            // Wacht op "B" van de seriële poort
            while (true) {
                try {
                String b = SerialCommunication.getMeldingRobot();
                System.out.println(SerialCommunication.getMeldingRobot());
                    Thread.sleep(1000);
                if (b.equals("B")) {
                    RobotQueue.removeFirstItem(packageModel);
                    RobotController.removeLoad();
                    SerialCommunication.setMeldingRobot("");
                    System.out.println("Lading is in rack");
                    RackModel.addToRack(packageModel);
                    RackModel.printRack();
                    RobotQueue.printQueue();
                    executeQueue();
                    break;
                }
                }catch (NullPointerException ex){
                    System.err.println("b=null");
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }


        });


    }

    public static boolean CheckIfLoadInRack(int x,int y) {
        for (PackageModel item : RackModel.rack) {
            if(x==item.getLocationX()&&y==item.getLocationY()){
                return true;
            }
        }
        return false;
    }



}
