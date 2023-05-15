package serial;

import com.fazecast.jSerialComm.SerialPort;
import gui.model.LoadModel;

import java.util.Arrays;
import java.util.concurrent.Executors;

import static gui.view.LoadView.model;

public class SerialCommunication {

    private static SerialCommunication instance = null;
    private final SerialPort port;

    //initializes the SerialPort with the right COM (you need to change the COM to the correct one in the main)
    private SerialCommunication(String portName) {
        port = SerialPort.getCommPort(portName);
        //sets the communication between java and arduino
        port.setComPortParameters(9600, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public static SerialCommunication getInstance() {
        if (instance == null) {
            instance = new SerialCommunication("COM3");
        }
        return instance;
    }

    //sends the locations to the robot
    public void sendData() {
        //make controller pas als actie is ondernomen voer actie uit
        Executors.newSingleThreadExecutor().execute(() -> {
            if (port.openPort()) {
                try {
                    //needs to be changed into the location of products
                    byte[] data = {(byte) 1, (byte) 5};
                    System.out.println(Arrays.toString(data));
                    // ^ change into locations
                    port.getOutputStream().write(data);
                    port.getOutputStream().flush();
                    //this is here to ensure that there is enough time to effectively send the bites (DO NOT REMOVE)
                    Thread.sleep(7000);
                    port.writeBytes(data, data.length);
                    //just to check if the information is sent and received back
                    byte[] buffer = new byte[2];
                    int numRead = port.readBytes(buffer, buffer.length);
                    if (numRead == buffer.length && new String(buffer).equals("OK")) {
                        System.out.println("Byte sent successfully");
                    } else {
                        System.out.println("Error sending byte");
                    }
                    port.closePort();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("Failed to open port");
            }
        });
    }


}