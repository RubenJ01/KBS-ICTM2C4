package serial;

import database.dao.OrderDao;
import database.dao.RackDao;
import database.util.DatabaseConnection;
import gui.MainFrame;
import gui.controller.RobotController;
import gui.model.PackageModel;
import gui.model.RackModel;
import gui.model.RobotQueue;
import gui.view.dialog.PlacePackageDialog;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

import javax.swing.*;
import java.sql.Connection;
import java.sql.SQLException;

import static gui.model.RobotQueue.executeQueue;
import static gui.model.RobotQueue.queue;


// voor het ontvangen en versturen van gegevens naar de hoofd arduino die gaat over Z en Y as
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
            JOptionPane.showMessageDialog(MainFrame.mainWindow, "Geen verbinding met Arduino Main", "Waarschuwing", JOptionPane.ERROR_MESSAGE);
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
                if (receivedDataBuilder.toString().contains("\n")||receivedDataBuilder.toString().contains("\r")) {
                    completeData = receivedDataBuilder.toString().trim();
                   //System.out.println("Ontvangen gegevens: " + completeData);
                    if (completeData.startsWith("Y:")) {
                        // Ontvangen Y-waarde
                        try {
                            String yString = completeData.substring(2);
                            int yValue = Integer.parseInt(yString);
                            RobotController.setYpositie(yValue);
                            System.out.println("Y-waarde: " + yValue);

                        }catch (NumberFormatException e){
                            System.err.println("Lees Error");
                        }
                    } else {
                        System.out.println("Melding Robot: " + completeData);
                        System.out.println(completeData);
                        StringToAction(completeData);
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



// x en y waarde meesturen,     als uitladen == 1 robot--> inladen     uitladen==2 robot --> uitladen
    public static void writeToSerial(int x, int y,int uitladen) {
        try {
            serialPort.writeByte((byte) y);
            Thread.sleep(10);
            serialPort.writeByte((byte) x);
            Thread.sleep(10);
            serialPort.writeByte((byte) uitladen);
            Thread.sleep(10);
            System.out.println("Data naar seriële poort geschreven: " + x+" "+y+" "+uitladen);

        } catch (SerialPortException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void setMeldingRobot(String meldingRobot) {
        SerialCommunication.meldingRobot = meldingRobot;
    }

    public static String getMeldingRobot() {
        return meldingRobot;
    }


    public static void StringToAction(String tekst){
        switch (tekst) {
            // Case 1
            case "BEREIKT":
                System.out.println("BEREIKT");
                RobotQueue.RobotBereiktInladen(RobotController.getLoad());
                break;

            // Case 2
            case "NOODSTOP":
                System.out.println("NOODSTOP");
                JOptionPane.showMessageDialog(MainFrame.mainWindow, "Noodstop is ingedrukt", "Waarschuwing", JOptionPane.ERROR_MESSAGE);
                break;

            // Case 3
            case "OPSTARTEN":
                System.out.println("OPSTARTEN");
                JOptionPane.showMessageDialog(MainFrame.mainWindow, "Robot is aan het opstarten", "opstarten", JOptionPane.WARNING_MESSAGE);
                break;

            case "INLADEN":
                System.out.println("INLADEN");
                //Dialoog wordt aan gemaakt voor het plaatsen van pakket op palletvork
                try {
                    PackageModel item = RobotQueue.queue.get(0);
                    PlacePackageDialog placePackageDialog = new PlacePackageDialog(item);
                }catch (IndexOutOfBoundsException e){
                    System.err.println("niks in wachtrij");
                }
                break;


            case "ORDER":
                System.out.println("ORDER");
                SerialCommunication.writeToSerial(6,1,4);
                RobotController.setLoad(queue.get(0));
                RackModel.removeFromRack(RobotController.getLoad());
                break;

            case "VERWERKT":
                System.out.println("VERWERKT");
                RobotQueue.RobotBereiktUitladen(RobotController.getLoad());
                RobotController.setLoad(null);
                if(queue.size()<=0){
                    SerialCommunication.writeToSerial(1,1,5);
                }else{
                    executeQueue();
                }
                break;


            //default
            default:
                System.out.println("no match");
        }
    }
}
