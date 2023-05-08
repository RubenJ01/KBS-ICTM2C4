import gui.MainFrame;

import gui.model.LoadModel;
import gui.model.LoadQueue;
import gui.view.StockView;

import java.sql.SQLException;
import serial.SerialCommunication;

public class Main {

    public static void main(String[] args) {
        new MainFrame();

        //StockView stockView = new StockView();



        //this is for sending the locations of the products to the robot.
        SerialCommunication.getInstance().sendData();
        //sending product location ends here.
    }
}
