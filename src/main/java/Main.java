import gui.MainFrame;
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
