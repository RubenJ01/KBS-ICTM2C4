package gui.view.panel;

import constants.Constants;
import gui.model.BinPackingModel;
import gui.model.BoxBinPacking;
import gui.model.BoxListBinPacking;
import gui.model.PackageBinPacking;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BinPackingPanel extends JPanel {

    private final BinPackingModel binPackingModel;


    public BinPackingPanel(BinPackingModel binPackingModel) {
        this.setLayout(new FlowLayout(FlowLayout.LEFT));
        this.binPackingModel = binPackingModel;
    }

    /**
     * Calculate the xRefactor: (Constants.SCREEN_WIDTH/ x coordinate)
     * Calculate the yRefactor: (Constants.SCREEN_HEIGHT/ y coordinate)
     *
     * @param g the <code>Graphics</code> object to protect
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setStroke(new BasicStroke(4));

        paintBoxWaitingList(g2);
        paintPackageBoxes(g2, 5.3, 3.66, 2.29, 1.45, 6.74, 3.2, 2.67);
        paintPackageBoxes(g2, 2.13, 1.80, 2.29, 1.45, 2.33, 1.68, 2.67);

        paintRobotArmQueue(g, g2);
        paintPackageOnScreenBoxes(g, g2);





//        //linker box
//        paintPackage(2, 1, 5.22, 1.6, 4.92, 1.57, g, g2); //hoogte 1
//        paintPackage(5, 2, 5.22, 1.7777777, 4.92, 1.74, g, g2); //hoogte 2
//        paintPackage(7, 3, 5.22, 2, 4.92, 1.95, g, g2); // hoogte 3
//
//
//        //rechter box
//        paintPackage(2, 1, 2.11, 1.6,2.06, 1.57, g, g2); //hoogte 1
//        paintPackage(5, 227, 2.11, 1.7777777, 2.06, 1.74, g, g2); //hoogte 2
//        paintPackage(7, 10, 2.11, 2, 2.06, 1.95, g, g2); // hoogte 3




    }

    public void paintRobotArmQueue(Graphics g, Graphics2D g2){
        for(int i = 0; i < binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().size(); i++){
            PackageBinPacking packageBinPacking = binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().get(i);
            if(i == 0) {
                paintPackage(packageBinPacking.getWeight(), packageBinPacking.getProductID(), 12.8, 8, 11.13, 7.27, g, g2);
            }
            else if(i==1){
                paintPackage(packageBinPacking.getWeight(), packageBinPacking.getProductID(), 5.12, 8, 4.83, 7.27, g, g2);
            }
            else if(i==2){
                paintPackage(packageBinPacking.getWeight(), packageBinPacking.getProductID(), 3.2, 8, 3.08, 7.27, g, g2);
            }
        }

    }

    public void paintPackage(int weight, int productID, double xRefactor, double yRefactor, double xRefactorText, double yRefactorText, Graphics g, Graphics2D g2) {
        if(weight == 2 || weight == 5 || weight == 7) {
            Color textColor = Color.BLACK;
            if (weight == 2) {
                g.setColor(Color.GREEN);
            } else if (weight == 5) {
                g.setColor(Color.BLUE);
                textColor = Color.WHITE;
            } else {
                g.setColor(Color.RED);
            }

            //calculate X box
            int xStartLargeBox = (int) (Constants.SCREEN_WIDTH / xRefactor);
            int xLargeBoxWidth = (int) (Constants.SCREEN_WIDTH / 12.8);
            int xSmallBoxWidth = (int) (Constants.SCREEN_WIDTH / 85.3);
            int xStartBox2 = xStartLargeBox + xLargeBoxWidth - xSmallBoxWidth;

            //calculate Y box
            int yStartLargeBox = (int) (Constants.SCREEN_HEIGHT / yRefactor);
            int yLargeBoxHeight = (int) (Constants.SCREEN_HEIGHT / 53.3);
            int ySmallBoxHeight = Constants.SCREEN_HEIGHT / 16;


            g.fillRect(xStartLargeBox, yStartLargeBox, xLargeBoxWidth, yLargeBoxHeight);
            g.fillRect(xStartBox2, yStartLargeBox, xSmallBoxWidth, ySmallBoxHeight);
            g.fillRect(xStartLargeBox, yStartLargeBox, xSmallBoxWidth, ySmallBoxHeight);

            String productText = "ProductID: " + productID;
            g2.setFont(new Font("default", Font.BOLD, 12));
            g2.setColor(textColor);
            g2.drawString(productText, (int) (Constants.SCREEN_WIDTH/xRefactorText), (int) (Constants.SCREEN_HEIGHT/yRefactorText));
        }
    }

    public void paintBoxWaitingList(Graphics2D g2){
        g2.setFont(new Font("default", Font.BOLD, 40));
        g2.drawString("Pakketten op de robotarm", Constants.SCREEN_WIDTH/16, Constants.SCREEN_HEIGHT/16);
        g2.drawRect(Constants.SCREEN_WIDTH/16, Constants.SCREEN_HEIGHT/10, (int) (Constants.SCREEN_WIDTH/2.84), Constants.SCREEN_HEIGHT/10);
    }

    public void paintPackageBoxes(Graphics2D g2, double xLeftRefactor, double xRightRefactor, double yTopRefactor, double yBottomRefactor, double xLeftLidRefactor, double xRightLidRefactor, double yLidRefactor){
        int xLeft = (int) (Constants.SCREEN_WIDTH/xLeftRefactor);
        int xRight = (int) (Constants.SCREEN_WIDTH/xRightRefactor);
        int yTop = (int) (Constants.SCREEN_HEIGHT/yTopRefactor);
        int yBottom = (int) (Constants.SCREEN_HEIGHT/yBottomRefactor);

        int xLeftLid = (int) (Constants.SCREEN_WIDTH/xLeftLidRefactor);
        int xRightLid = (int) (Constants.SCREEN_WIDTH/xRightLidRefactor);
        int yTopLid = (int) (Constants.SCREEN_HEIGHT/yLidRefactor);

        g2.drawLine(xLeft, yTop, xLeft, yBottom);
        g2.drawLine(xLeft, yBottom, xRight, yBottom);
        g2.drawLine(xRight, yTop, xRight, yBottom);

        g2.drawLine(xLeft, yTop, xLeftLid, yTopLid);
        g2.drawLine(xRight, yTop, xRightLid, yTopLid);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("default", Font.BOLD, 12));

        String productText1 = "leeg";
        String productText2 = "leeg";
        for(int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking box = binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if(box.getBoxLocation() > 0){
                String productText = "Doos " + box.getBoxNumber() + ": Open ruimte = " + box.getOpenSpace() + "kg";
                if(box.getBoxLocation() == 1){
                    productText1 = productText;
                }
                else{
                    productText2 = productText;
                }
            }
        }
        g2.drawString(productText1, (int) (Constants.SCREEN_WIDTH/5.69), (int) (Constants.SCREEN_HEIGHT/1.39));
        g2.drawString(productText2, (int) (Constants.SCREEN_WIDTH/2.19), (int) (Constants.SCREEN_HEIGHT/1.39));
    }

    public void paintPackageOnScreenBoxes(Graphics g, Graphics2D g2){
        BoxBinPacking box1 = null;
        BoxBinPacking box2 = null;
        for(int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking box = binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if(box.getBoxLocation() == 1){
                box1 = box;
            }
            else if(box.getBoxLocation() == 2){
                box2 = box;
            }
        }
        paintPackageInBox(box1, g, g2);
        paintPackageInBox(box2, g, g2);
    }

    public void paintPackageInBox(BoxBinPacking boxBinPacking, Graphics g, Graphics2D g2){
        double xRefactor = 0;
        double yRefactor;
        double xRefactorText = 0;
        double yRefactorText;
        List<PackageBinPacking> packageList = boxBinPacking.getPackagesInBox();


        for(int i = 0; i< packageList.size(); i++){
            if(boxBinPacking.getBoxLocation()==1){
                xRefactor = 5.22;
                xRefactorText = 4.92;
            }
            else if(boxBinPacking.getBoxLocation() == 2){
                xRefactor = 2.11;
                xRefactorText = 2.06;
            }

            if(i == 0) {
                yRefactor = 1.6;
                yRefactorText = 1.57;
            }
            else if(i == 1) {
                yRefactor = 1.7777777;
                yRefactorText = 1.74;
            }
            else {
                yRefactor = 2;
                yRefactorText = 1.95;
            }
            paintPackage(packageList.get(i).getWeight(), packageList.get(i).getProductID(), xRefactor, yRefactor, xRefactorText, yRefactorText, g, g2);
        }
        //nummer van de doos weten (van beide)
        //lijst opvragen
        //lijst tekenen
    }
}
