
//import gui.model.LoadModel;
import gui.MainFrame;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import gui.model.RobotQueue;
//import gui.view.StockView;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
//import serial.SerialCommunication;

public class Main {
    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws SQLException {
        //https://stackoverflow.com/questions/17762214/java-jtable-alternate-row-color-not-working
        // Used to get the table to have an alternating color between rows
        UIDefaults defaults = UIManager.getLookAndFeelDefaults();
        if (defaults.get("Table.alternateRowColor") == null) {
            defaults.put("Table.alternateRowColor", new Color(218, 217, 217));
        }
        logger.info("INFO - dit is een test");
        new MainFrame();

        //StockView stockView = new StockView();



        //this is for sending the locations of the products to the robot.

        //sending product location ends here.
    }
}
