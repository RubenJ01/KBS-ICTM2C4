package serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

public class SerialCommunication implements SerialPortEventListener {
    private static String meldingRobot;
    private static SerialPort serialPort;
    private String newdata = "";
    private StringBuilder receivedDataBuilder = new StringBuilder();

    public SerialCommunication() {
        // Initialisatie van de seriële communicatie
        serialPort = new SerialPort("COM6"); // Pas de poortnaam aan indien nodig
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void serialEvent(SerialPortEvent event) {
        if (event.isRXCHAR() && event.getEventValue() > 0) {
            try {
                byte[] buffer = serialPort.readBytes(event.getEventValue());
                String receivedData = new String(buffer);
                receivedDataBuilder.append(receivedData);

                // Controleer of het volledige getal is ontvangen
                String completeData;
                if (receivedDataBuilder.toString().contains("\n")) {
                    completeData = receivedDataBuilder.toString().trim();
                    System.out.println("Ontvangen gegevens: " + completeData);
                    setMeldingRobot(completeData);

                    // Reset de StringBuilder
                    receivedDataBuilder.setLength(0);
                } else {
                    completeData = "null";
                }

            } catch (SerialPortException e) {
                e.printStackTrace();
            }
        }
    }


    public static void writeToSerial(int x, int y) {
        try {
            serialPort.writeByte((byte) y);
            serialPort.writeByte((byte) x);
            System.out.println("Data naar seriële poort geschreven: " + x+" "+y);

        } catch (SerialPortException e) {
            e.printStackTrace();
        }
    }

    public static void setMeldingRobot(String meldingRobot) {
        SerialCommunication.meldingRobot = meldingRobot;
    }

    public static String getMeldingRobot() {
        return meldingRobot;
    }
}
