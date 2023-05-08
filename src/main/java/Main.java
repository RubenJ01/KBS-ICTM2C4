
import gui.MainFrame;
import serial.SerialCommunication;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {
        new MainFrame();
        //this is for sending the locations of the products to the robot.
        SerialCommunication serial = new SerialCommunication("COM3");
        serial.sendData();
        //sending product location ends here.
    }
}
