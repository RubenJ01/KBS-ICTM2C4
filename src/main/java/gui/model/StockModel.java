package gui.model;


import java.util.ArrayList;

public class StockModel {
    private ArrayList<Integer> productStock;

    public StockModel() {
        this.productStock = new ArrayList<>();
        this.productStock.add(0, 10);
        this.productStock.add(1, 20);
        this.productStock.add(2, 30);
        this.productStock.add(3, 15);
        this.productStock.add(4, 36);
        this.productStock.add(5, 12);
        this.productStock.add(6, 5);
        this.productStock.add(7, 56);
    }

    public ArrayList<Integer> getProductStock() {
        return productStock;
    }
}