package gui.model;


import java.util.ArrayList;
import java.util.List;

public class BoxListBinPacking {
    private int boxCount;
    private List<BoxBinPacking> boxBinPackingsList;


    public BoxListBinPacking() {
        startNewOrder();
    }

    public int getBoxCount() {
        return boxCount;
    }

    public void addNewBox(int location) {
        this.boxCount += 1;
        BoxBinPacking boxBinPacking = new BoxBinPacking(boxCount, location);
        this.boxBinPackingsList.add(boxBinPacking);
    }

    public List<BoxBinPacking> getBoxBinPackingsList() {
        return boxBinPackingsList;
    }

    /**
     * Function to clear the data from the robotarmqueue (fork) and the boxes
     */
    public void startNewOrder() {
        this.boxCount = 0;
        this.boxBinPackingsList = new ArrayList<>();
        addNewBox(1);
        addNewBox(2);
    }

}
