package gui.model;

import database.model.StockItem;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class StockModel extends AbstractTableModel {
    private String[] columnNames = {"Product ID", "Product naam", "Voorraad"};

    private final Object[][] data;
    private final int rowCount;

    public StockModel(List<StockItem> stockitemsList) {
        data = new Object[stockitemsList.size()][3];
        this.rowCount = stockitemsList.size();
        for(int i = 0; i < stockitemsList.size(); i++) {
            StockItem item = stockitemsList.get(i);
            data[i][0] = item.getStockItemID();
            data[i][1] = item.getStockItemName();
            data[i][2] = item.getStockitemholdings().getQuantityOnHand();
        }

    }

    /**
     * Class to get the type of the columns.
     * Needed for being able to sort correctly
     * @param column  the column being queried
     * @return class type of the column
     */
    //https://stackoverflow.com/questions/6592192/why-does-my-jtable-sort-an-integer-column-incorrectly
    @Override
    public Class getColumnClass(int column) {
        switch (column) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return Integer.class;
            default:
                return String.class;
        }
    }

    public int getColumnCount() {
        return 3;
    }
    public int getRowCount() {
        return rowCount;
    }
    public String getColumnName(int column) {
        return columnNames[column];
    }
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

}