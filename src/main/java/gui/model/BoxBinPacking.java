package gui.model;

import java.util.ArrayList;
import java.util.List;

public class BoxBinPacking {
    private int openSpace;
    private boolean boxIsOpen;
    private final int boxNumber;
    private int boxLocation;
    private final List<PackageModel> packagesInBox;

    public BoxBinPacking(int boxNumber, int boxLocation) {
        this.openSpace = 10;
        this.boxIsOpen = true;
        this.boxNumber = boxNumber;
        this.packagesInBox = new ArrayList<>();
        this.boxLocation = boxLocation;
    }

    public int getBoxLocation() {
        return this.boxLocation;
    }

    public void setBoxLocation(int location) {
        this.boxLocation = location;
    }

    public boolean getBoxIsOpen() {
        return this.boxIsOpen;
    }

    public int getOpenSpace() {
        return openSpace;
    }

    public void setBoxIsOpen(boolean boxIsOpen) {
        this.boxIsOpen = boxIsOpen;
    }

    public int getBoxNumber() {
        return boxNumber;
    }


    public List<PackageModel> getPackagesInBox() {
        return packagesInBox;
    }

    /**
     * Function to add a package inside the list of packages in a box
     *
     * @param packageModel
     */
    public void addPackageInBox(PackageModel packageModel) {
        this.packagesInBox.add(packageModel);
        this.openSpace -= packageModel.getWeight();

    }

    public String toString() {
        String string = "";
        for (int i = 0; i < packagesInBox.size(); i++) {
            string += packagesInBox.get(i) + "\n";
        }
        return string;
    }
}
