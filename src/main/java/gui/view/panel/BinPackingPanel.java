package gui.view.panel;

import constants.Constants;
import gui.model.BinPackingModel;
import gui.model.BoxBinPacking;
import gui.model.PackageModel;

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
    }

    /**
     * Function to paint the correct package on the correct location on the robotarmqueue (fork)
     *
     * @param g
     * @param g2
     */
    public void paintRobotArmQueue(Graphics g, Graphics2D g2) {
        for (int i = 0; i < binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().size(); i++) {
            PackageModel packageModel = binPackingModel.getRobotArmQueueBinPacking().getRobotArmQueuePackages().get(i);
            if (i == 0) {
                paintPackage(packageModel.getWeight(), packageModel.getProductID(), 12.8, 8, 11.13, 7.27, g, g2);
            } else if (i == 1) {
                paintPackage(packageModel.getWeight(), packageModel.getProductID(), 5.12, 8, 4.83, 7.27, g, g2);
            } else if (i == 2) {
                paintPackage(packageModel.getWeight(), packageModel.getProductID(), 3.2, 8, 3.08, 7.27, g, g2);
            }
        }

    }

    /**
     * function to get a package to be painted.
     * Weight equals a color (2 = green, 5 = blue, 7 = red).
     * Refactor variables are used, so it will have the same dimensions on all screens.
     *
     * @param weight
     * @param productID
     * @param xRefactor
     * @param yRefactor
     * @param xRefactorText
     * @param yRefactorText
     * @param g
     * @param g2
     */
    public void paintPackage(int weight, int productID, double xRefactor, double yRefactor, double xRefactorText, double yRefactorText, Graphics g, Graphics2D g2) {
        if (weight == 2 || weight == 5 || weight == 7) {
            Color textColor = Color.BLACK;
            if (weight == 2) {
                g.setColor(Color.GREEN);
            } else if (weight == 5) {
                g.setColor(Color.BLUE);
                textColor = Color.WHITE;
            } else {
                g.setColor(Color.RED);
            }

            //calculate the coordinates of the X boxes for the package
            int xStartLargeBox = (int) (Constants.SCREEN_WIDTH / xRefactor);
            int xLargeBoxWidth = (int) (Constants.SCREEN_WIDTH / 12.8);
            int xSmallBoxWidth = (int) (Constants.SCREEN_WIDTH / 85.3);
            int xStartBox2 = xStartLargeBox + xLargeBoxWidth - xSmallBoxWidth;

            //calculate the coordinates of the Y boxes for the package
            int yStartLargeBox = (int) (Constants.SCREEN_HEIGHT / yRefactor);
            int yLargeBoxHeight = (int) (Constants.SCREEN_HEIGHT / 53.3);
            int ySmallBoxHeight = Constants.SCREEN_HEIGHT / 16;

            //painting the package
            g.fillRect(xStartLargeBox, yStartLargeBox, xLargeBoxWidth, yLargeBoxHeight);
            g.fillRect(xStartBox2, yStartLargeBox, xSmallBoxWidth, ySmallBoxHeight);
            g.fillRect(xStartLargeBox, yStartLargeBox, xSmallBoxWidth, ySmallBoxHeight);

            //Painting the product ID on the package
            String productText = "ProductID: " + productID;
            g2.setFont(new Font("default", Font.BOLD, 12));
            g2.setColor(textColor);
            g2.drawString(productText, (int) (Constants.SCREEN_WIDTH / xRefactorText), (int) (Constants.SCREEN_HEIGHT / yRefactorText));
        }
    }

    /**
     * Function to paint the robotarmqueue box.
     *
     * @param g2
     */
    public void paintBoxWaitingList(Graphics2D g2) {
        g2.setFont(new Font("default", Font.BOLD, 40));
        g2.drawString("Pakketten op de robotarm", Constants.SCREEN_WIDTH / 16, Constants.SCREEN_HEIGHT / 16);
        g2.drawRect(Constants.SCREEN_WIDTH / 16, Constants.SCREEN_HEIGHT / 10, (int) (Constants.SCREEN_WIDTH / 2.84), Constants.SCREEN_HEIGHT / 10);
    }

    /**
     * Function to paint the boxes that are used to put the packages in.
     * Refactor variables are used, so it will have the same dimensions on all screens.
     *
     * @param g2
     * @param xLeftRefactor
     * @param xRightRefactor
     * @param yTopRefactor
     * @param yBottomRefactor
     * @param xLeftLidRefactor
     * @param xRightLidRefactor
     * @param yLidRefactor
     */
    public void paintPackageBoxes(Graphics2D g2, double xLeftRefactor, double xRightRefactor, double yTopRefactor, double yBottomRefactor, double xLeftLidRefactor, double xRightLidRefactor, double yLidRefactor) {
        int xLeft = (int) (Constants.SCREEN_WIDTH / xLeftRefactor);
        int xRight = (int) (Constants.SCREEN_WIDTH / xRightRefactor);
        int yTop = (int) (Constants.SCREEN_HEIGHT / yTopRefactor);
        int yBottom = (int) (Constants.SCREEN_HEIGHT / yBottomRefactor);

        int xLeftLid = (int) (Constants.SCREEN_WIDTH / xLeftLidRefactor);
        int xRightLid = (int) (Constants.SCREEN_WIDTH / xRightLidRefactor);
        int yTopLid = (int) (Constants.SCREEN_HEIGHT / yLidRefactor);

        //painting lines for the box
        g2.drawLine(xLeft, yTop, xLeft, yBottom);
        g2.drawLine(xLeft, yBottom, xRight, yBottom);
        g2.drawLine(xRight, yTop, xRight, yBottom);

        g2.drawLine(xLeft, yTop, xLeftLid, yTopLid);
        g2.drawLine(xRight, yTop, xRightLid, yTopLid);

        g2.setColor(Color.BLACK);
        g2.setFont(new Font("default", Font.BOLD, 12));

        //painting the text underneath the boxes to show the box number and the open space left in the box
        String productText1 = "leeg";
        String productText2 = "leeg";
        for (int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking box = binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if (box.getBoxLocation() > 0) {
                String productText = "Doos " + box.getBoxNumber() + ": Open ruimte = " + box.getOpenSpace() + "kg";
                if (box.getBoxLocation() == 1) {
                    productText1 = productText;
                } else {
                    productText2 = productText;
                }
            }
        }
        g2.drawString(productText1, (int) (Constants.SCREEN_WIDTH / 5.69), (int) (Constants.SCREEN_HEIGHT / 1.39));
        g2.drawString(productText2, (int) (Constants.SCREEN_WIDTH / 2.19), (int) (Constants.SCREEN_HEIGHT / 1.39));
    }

    /**
     * Function to paint the correct box number (+ packages inside) on the screen
     *
     * @param g
     * @param g2
     */
    public void paintPackageOnScreenBoxes(Graphics g, Graphics2D g2) {
        BoxBinPacking box1 = null;
        BoxBinPacking box2 = null;
        for (int i = 0; i < binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().size(); i++) {
            BoxBinPacking box = binPackingModel.getBoxListBinPacking().getBoxBinPackingsList().get(i);
            if (box.getBoxLocation() == 1) {
                box1 = box;
            } else if (box.getBoxLocation() == 2) {
                box2 = box;
            }
        }
        paintPackageInBox(box1, g, g2);
        paintPackageInBox(box2, g, g2);
    }

    /**
     * Function to paint know what package needs to be in which box and what height
     *
     * @param boxBinPacking
     * @param g
     * @param g2
     */
    public void paintPackageInBox(BoxBinPacking boxBinPacking, Graphics g, Graphics2D g2) {
        double xRefactor = 0;
        double yRefactor;
        double xRefactorText = 0;
        double yRefactorText;
        List<PackageModel> packageList = boxBinPacking.getPackagesInBox();


        for (int i = 0; i < packageList.size(); i++) {
            // if else statement to get the right box to paint the package in
            if (boxBinPacking.getBoxLocation() == 1) {
                xRefactor = 5.22;
                xRefactorText = 4.92;
            } else if (boxBinPacking.getBoxLocation() == 2) {
                xRefactor = 2.11;
                xRefactorText = 2.06;
            }

            //if else state to get the right height to paint the pacakge on
            if (i == 0) {
                yRefactor = 1.6;
                yRefactorText = 1.57;
            } else if (i == 1) {
                yRefactor = 1.7777777;
                yRefactorText = 1.74;
            } else {
                yRefactor = 2;
                yRefactorText = 1.95;
            }
            paintPackage(packageList.get(i).getWeight(), packageList.get(i).getProductID(), xRefactor, yRefactor, xRefactorText, yRefactorText, g, g2);
        }
    }
}
