package serial;

import com.fazecast.jSerialComm.SerialPort;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Executors;

public class SerialCommunication {

    private static SerialCommunication instance = null;
    private final SerialPort port;

    public int locatieRobot;
    public static String meldingRobot="";


    //initializes the SerialPort with the right COM (you need to change the COM to the correct one in the main)
    private SerialCommunication(String portName) {
        port = SerialPort.getCommPort(portName);
        //sets the communication between java and arduino
        port.setComPortParameters(9600, 8, 1, 0);

    }

    public static SerialCommunication getInstance() {
        if (instance == null) {
            instance = new SerialCommunication("COM4");
        }
        return instance;
    }



    //sends the locations to the robot
    public void sendData(int x, int y, int uitladen,boolean versturen) {
        //make controller pas als actie is ondernomen voer actie uit
            //maakt verbinding
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            port.openPort();

                    //deze variabele gaat over of we gaan sturen naar de Serial of Gaan ontvangen
                    if(versturen==false) {

                        try {
                        port.setComPortTimeouts(SerialPort.TIMEOUT_READ_BLOCKING, 1000, 1000);
                        InputStream in = port.getInputStream();
                        StringBuilder buffer = new StringBuilder();
                        while (true) {
                            if (in.available() > 0) {
                                char c = (char) in.read();
                                if (c == '\n') {
                                    String data=(buffer.toString());
                                    buffer.setLength(0);


                                  try {
                                      System.out.println("int:");
                                      locatieRobot=Integer.parseInt(data);
                                      System.out.println(locatieRobot);
                                      break;


                                  }catch (NumberFormatException e){
                                      System.out.println("melding:");
                                      meldingRobot=data;
                                      System.out.println(meldingRobot);
                                      break;

                                  }


                                } else {
                                    buffer.append(c);

                                }
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        port.closePort();
                    }
                    }else {
                        Executors.newSingleThreadExecutor().execute(() -> {
                        try {
                        port.setComPortTimeouts(SerialPort.TIMEOUT_WRITE_BLOCKING, 500, 500);
                        //needs to be changed into the location of products
                        OutputStream outputStream = port.getOutputStream();
                        int datax = (byte) x;
                        int datay = (byte) y;
                        //if uitladen==1 robot--> inladen if uitladen==2 robot-->uitladen
                        int dataUitladen = (byte) uitladen;

                        Thread.sleep(3000);

                        //Writing data to the serial:
                        outputStream.write(datax);
                        outputStream.write(datay);
                        outputStream.write(dataUitladen);

                        //stuurt alle data in een keer (als een toilet die je doorspoelt)
                        outputStream.flush();
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            port.closePort();
                        }
                        });
                    }




    }


    public int getLocatieRobot() {
        return locatieRobot;
    }

    public static String getMeldingRobot() {
        return meldingRobot;
    }

    public static void setMeldingRobot(String meldingRobot) {
        SerialCommunication.meldingRobot = meldingRobot;
    }
}
