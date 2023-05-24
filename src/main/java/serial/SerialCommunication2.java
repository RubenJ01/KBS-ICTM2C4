package serial;

import gui.MainFrame;
import gui.controller.RobotController;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;


//ontvangen van gegevens van arduino die gaat over X-as
public class SerialCommunication2 implements SerialPortEventListener {
    private static String meldingRobot;
    private static SerialPort serialPort;
    private String newdata = "";
    private StringBuilder receivedDataBuilder = new StringBuilder();

    public SerialCommunication2() {
        // Initialisatie van de seriële communicatie
        serialPort = new SerialPort("COM3"); // Pas de poortnaam aan indien nodig
        try {
            serialPort.openPort();
            serialPort.setParams(SerialPort.BAUDRATE_9600, SerialPort.DATABITS_8, SerialPort.STOPBITS_1, SerialPort.PARITY_NONE);
            serialPort.addEventListener(this, SerialPort.MASK_RXCHAR);
        } catch (SerialPortException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(MainFrame.mainWindow, "Geen verbinding met Arduino 2", "Waarschuwing", JOptionPane.ERROR_MESSAGE);

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
                    //System.out.println("Ontvangen gegevens: " + completeData);
                    if (completeData.startsWith("Y:")) {
                        // Ontvangen Y-waarde
                        try {
                            String yString = completeData.substring(2);
                            int yValue = Integer.parseInt(yString);
                            RobotController.setYpositie(yValue);
                            //System.out.println("Y-waarde: " + yValue);

                        }catch (NumberFormatException e){
                            System.err.println("Lees Error");
                        }
                    } else if (completeData.startsWith("X:")) {
                        // Ontvangen X-waarde
                        try {
                            String xString = completeData.substring(2);
                            int xValue = Integer.parseInt(xString);
                            RobotController.setXpositie(xValue);
                            //System.out.println("X-waarde: " + xValue);

                        }catch (NumberFormatException e){
                            System.err.println("Lees Error");
                        }


                    } else {
                        System.out.println("Melding Robot: " + completeData);
                        setMeldingRobot(completeData);
                    }

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


//    public static void writeToSerial(int x, int y) {
//        try {
//            serialPort.writeByte((byte) y);
//            serialPort.writeByte((byte) x);
//            System.out.println("Data naar seriële poort geschreven: " + x+" "+y);
//
//        } catch (SerialPortException e) {
//            e.printStackTrace();
//        }
//    }

    public static void setMeldingRobot(String meldingRobot) {
        SerialCommunication2.meldingRobot = meldingRobot;
    }

    public static String getMeldingRobot() {
        return meldingRobot;
    }
}
