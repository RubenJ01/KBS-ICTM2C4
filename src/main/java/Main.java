
import com.mysql.cj.x.protobuf.MysqlxConnection;
import gui.MainFrame;
import serial.SerialCommunication;
import serial.SerialReceive;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        new MainFrame();
        //this is for sending the locations of the products to the robot.
        SerialCommunication.getInstance().sendData();
        byte receivedByte = SerialReceive.readByteFromSerialPort();
        System.out.println(receivedByte);
        //sending product location ends here.
    }
}
