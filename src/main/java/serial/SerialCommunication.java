package serial;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

public class SerialCommunication {

    private static SerialCommunication instance = null;
    private final SerialPort port;
    private int locatieRobot;
    private String meldingRobot;

    // Initializes the SerialPort with the right COM (you need to change the COM to the correct one in the main)
    private SerialCommunication(String portName) {
        port = SerialPort.getCommPort(portName);
        // Sets the communication between Java and Arduino
        port.setComPortParameters(9600, 8, 1, 0);
    }

    // Uses a singleton pattern to ensure only one instance of SerialCommunication is created
    public static SerialCommunication getInstance(String portName) {
        if (instance == null) {
            instance = new SerialCommunication(portName);
        }
        return instance;
    }

    // Sends the locations to the robot
    public void sendData(int x, int y, int uitladen, boolean versturen) {
        if (!port.isOpen()) {
            port.openPort();
        }

        if (versturen) {
            Executors.newSingleThreadExecutor().execute(() -> {
                try {
                    port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 500, 500);
                    OutputStream outputStream = port.getOutputStream();
                    // Needs to be changed into the location of products
                    int datax = (byte) x;
                    int datay = (byte) y;
                    // If uitladen == 1 robot --> inladen if uitladen == 2 robot --> uitladen
                    int dataUitladen = (byte) uitladen;

                    // Writing data to the serial:
                    outputStream.write(datax);
                    outputStream.write(datay);
                    outputStream.write(dataUitladen);

                    // Sends all data at once (like flushing a toilet)
                    outputStream.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    port.closePort();
                }
            });
        } else {
            try {
                port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 1000);
                InputStream in = port.getInputStream();
                StringBuilder buffer = new StringBuilder();
                while (true) {
                    if (in.available() > 0) {
                        char c = (char) in.read();
                        if (c == '\n') {
                            String data = buffer.toString().trim();
                            buffer.setLength(0);
                            try {
                                locatieRobot = Integer.parseInt(data);
                                break;
                            } catch (NumberFormatException e) {
                                meldingRobot = data;
                                break;
                            }
                        } else {
                            buffer.append(c);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public int getLocatieRobot() {
        return locatieRobot;
    }

    public String getMeldingRobot() {
        return meldingRobot;
    }

    public void setMeldingRobot(String meldingRobot) {
        this.meldingRobot = meldingRobot;
    }

    public void close() {
        if (port.isOpen()) {
            port.closePort();
        }
    }
}
