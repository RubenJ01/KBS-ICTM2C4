package gui.model;


import java.util.ArrayList;
import java.util.List;

public class BoxListBinPacking {
    //Hierin staat een lijst van de dozen die er zijn gemaakt.
    private int boxCount;
    private List<BoxBinPacking> boxBinPackingsList;


    public BoxListBinPacking(){
        startNewOrder();
    }

    public int getBoxCount() {
        return boxCount;
    }

    public BoxBinPacking addNewBox(int location) {
        this.boxCount += 1;
        BoxBinPacking boxBinPacking = new BoxBinPacking(boxCount, location);
        this.boxBinPackingsList.add(boxBinPacking);
        return boxBinPacking;
    }

    public List<BoxBinPacking> getBoxBinPackingsList(){
        return boxBinPackingsList;
    }

    public void startNewOrder(){
        this.boxCount = 0;
        this.boxBinPackingsList = new ArrayList<>();
        addNewBox(1);
        addNewBox(2);
    }

}
