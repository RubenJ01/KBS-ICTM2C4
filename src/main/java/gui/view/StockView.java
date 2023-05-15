package gui.view;

import constants.Constants;
import database.dao.StockItemDao;
import database.model.StockItem;
import database.util.DatabaseConnection;
import gui.ViewBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableRowSorter;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockView extends JPanel implements ViewBuilder {

    private static final Logger logger = LoggerFactory.getLogger(StockView.class);
    private final NavbarView navbarView;
    private final StockItemDao stockItemDao;
    private JTextField searchStockItemID, searchStockItemName;
    private int rowCount;

    public StockView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.stockItemDao = StockItemDao.getInstance();
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);

        List<StockItem> allStocks = getStockitemsFromDatabase();

        //Table aanmaken
        VoorraadModel voorraadModel = new VoorraadModel(allStocks);
        JTable table = new JTable(voorraadModel);

        //zoekpanel
        JPanel stockBottomBar = new JPanel();
        stockBottomBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        stockBottomBar.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT/10);
        add(stockBottomBar, BorderLayout.SOUTH);

        JLabel jl_StockItemID = new JLabel("Zoek op ID");
        stockBottomBar.add(jl_StockItemID);

        searchStockItemID = new JTextField(15);
        stockBottomBar.add(searchStockItemID, BorderLayout.CENTER);

        JLabel jl_StockItemName = new JLabel("Zoek op naam");
        stockBottomBar.add(jl_StockItemName);

        searchStockItemName = new JTextField(15);
        stockBottomBar.add(searchStockItemName, BorderLayout.CENTER);


        //sorteren en zoeken
        TableRowSorter<VoorraadModel> sorter = new TableRowSorter<>(voorraadModel);
        searchStockItemID.getDocument().addDocumentListener(getDocumentListenerStockItemID(searchStockItemID, table, sorter));
        searchStockItemName.getDocument().addDocumentListener(getDocumentListenerStockItemName(searchStockItemName, table, sorter));
        table.setRowSorter(sorter);


        //https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
        //rijhoogtes en alignments van kolommen
        table.setRowHeight(Constants.SCREEN_HEIGHT/27);

        table.getColumnModel().getColumn(0).setCellRenderer(new LeftTableCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new MiddleTableCellRenderer());

        //tabel pane
        JScrollPane scrollpane = new JScrollPane(table);
        this.add(scrollpane, BorderLayout.CENTER);

        this.setVisible(true);
    }


    private DocumentListener getDocumentListenerStockItemName(JTextField searchStockItemName, JTable jtable, TableRowSorter<VoorraadModel> sorter) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemName);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemName);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemName);
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.regexFilter(str, 1));

                }
            }

        };
    }

    private DocumentListener getDocumentListenerStockItemID(JTextField searchStockItemID, JTable jtable, TableRowSorter<VoorraadModel> sorter) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemID);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemID);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable, searchStockItemID);
            }

            public void search(String str) {
                System.out.println(str);
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.valueOf(str), 0));
                }
            }
        };
    }

    private List<StockItem> getStockitemsFromDatabase() {
        List<StockItem> allStocks = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            allStocks.addAll(this.stockItemDao.getAllStockItemHoldings(con));
        } catch (SQLException e) {
            logger.error("Error", e);
        }
        return allStocks;
    }
    private boolean checkTableEmpty(JTable jtable){
        rowCount = jtable.getRowCount();
        if(rowCount <= 0){
            System.out.println(true);
            return true;
        }
        System.out.println(false);
        return false;
    }
    private void setBackgroundColorSearchFields(JTable table, JTextField searchStockItem) {
        if(checkTableEmpty(table)){
            searchStockItem.setBackground(new Color(247, 117, 114));
        }
        else{
            searchStockItem.setBackground(Color.WHITE);
        }
    }

    class VoorraadModel extends AbstractTableModel {

        private String[] columnNames = {"Product ID", "Product naam", "Voorraad"};

        private final Object[][] data;
        private final int rowCount;

        private VoorraadModel(List<StockItem> stockItemList) {
            data = new Object[stockItemList.size()][3];
            this.rowCount = stockItemList.size();
            for(int i = 0; i < stockItemList.size(); i++) {
                StockItem item = stockItemList.get(i);
                data[i][0] = item.getStockItemID();
                data[i][1] = item.getStockItemName();
                data[i][2] = item.getStockitemholdings().getQuantityOnHand();
            }

        }

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
    };

    //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
    public class LeftTableCellRenderer extends DefaultTableCellRenderer {
        protected LeftTableCellRenderer() {
            setHorizontalAlignment(JLabel.LEFT);  }

    }
    public class MiddleTableCellRenderer extends DefaultTableCellRenderer {
        protected MiddleTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER);  }

    }
}
