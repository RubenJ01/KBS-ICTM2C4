package serial;
import com.fazecast.jSerialComm.*;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class SerialCommunication {

    private final SerialPort port;
    private static SerialCommunication instance = null;

    //initializes the SerialPort with the right COM (you need to change the COM to the correct one in the main)
    private SerialCommunication(String portName) {
        port = SerialPort.getCommPort(portName);
        //sets the communication between java and arduino
        port.setComPortParameters(9600, 8, 1, 0);
        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 0, 0);
    }

    public static SerialCommunication getInstance() {
        if(instance == null) {
           instance = new SerialCommunication("COM3");
        }
        return instance;
    }

    //sends the locations to the robot
    public void sendData() {
        Executors.newSingleThreadExecutor().execute(() -> {
            if (port.openPort()) {
                try {
                    //needs to be changed into the location of products
                    byte[] data = {1, 5};
                    // ^ change into locations
                    port.getOutputStream().write(data);
                    port.getOutputStream().flush();
                    //this is here to ensure that there is enough time to effectively send the bites (DO NOT REMOVE)
                    Thread.sleep(5000);
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