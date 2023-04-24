package gui.model;


import java.util.ArrayList;

public class StockModel {
    private ArrayList<Integer> productStock;

    public StockModel(){
        this.productStock = new ArrayList<>();
    }


    public static void main(String[] args) {
        StockModel stockModel = new StockModel();
        stockModel.productStock.add(0, 10);
        stockModel.productStock.add(1, 20);
        stockModel.productStock.add(2, 30);

        for(int i = 0; i < stockModel.productStock.size(); i++){
            System.out.println(stockModel.productStock.get(i));
        }
    }
}
