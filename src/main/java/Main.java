
import gui.MainFrame;
import serial.SerialCommunication;

import java.io.IOException;
import java.util.concurrent.Executors;

public class Main {

    public static void main(String[] args) {
        new MainFrame();
        //this is for sending the locations of the products to the robot.
        SerialCommunication.getInstance().sendData();
        //sending product location ends here.
    }
}
