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
    private final StockItemDao stockitemsDao;
    private JTextField searchStockItemID, searchStockItemName;
    private int rowCount;

    public StockView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.stockitemsDao = StockItemDao.getInstance();
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);

        List<StockItem> allStocks = getStockitemsFromDatabase();

        //Create Table
        VoorraadModel voorraadModel = new VoorraadModel(allStocks);
        JTable table = new JTable(voorraadModel);

        //Search panel
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


        //Sorting and searching
        TableRowSorter<VoorraadModel> sorter = new TableRowSorter<>(voorraadModel);
        searchStockItemID.getDocument().addDocumentListener(getDocumentListenerStockItemID(table, sorter));
        searchStockItemName.getDocument().addDocumentListener(getDocumentListenerStockItemName(table, sorter));
        table.setRowSorter(sorter);


        //https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
        //Custom rowheights
        table.setRowHeight(Constants.SCREEN_HEIGHT/27);

        table.getColumnModel().getColumn(0).setCellRenderer(new LeftTableCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new MiddleTableCellRenderer());

        //Attach table to scrollpane and add scrollpane to the screen
        JScrollPane scrollpane = new JScrollPane(table);
        this.add(scrollpane, BorderLayout.CENTER);

        this.setVisible(true);
    }

    /**
     * Function to search on StockItemName in the table with automatic search results
     * Also sets textfield background to red if no products were found using the search
     * @param jtable
     * @param sorter
     */
    private DocumentListener getDocumentListenerStockItemName(JTable jtable, TableRowSorter<VoorraadModel> sorter) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchStockItemName.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
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

    /**
     * Function to search on StockItemID in the table with automatic search results
     * Also sets textfield background to red if no products were found using the search
     * @param jtable
     * @param sorter
     */
    private DocumentListener getDocumentListenerStockItemID(JTable jtable, TableRowSorter<VoorraadModel> sorter) {
        return new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                search(searchStockItemID.getText());
                checkTableEmpty(jtable);
                setBackgroundColorSearchFields(jtable);
            }

            public void search(String str) {
                if (str.length() == 0) {
                    sorter.setRowFilter(null);
                } else {
                    sorter.setRowFilter(RowFilter.numberFilter(RowFilter.ComparisonType.EQUAL, Integer.valueOf(str), 0));
                }
            }
        };
    }

    /**
     *  Make a list from all the received stock data
     */
    private List<StockItem> getStockitemsFromDatabase() {
        List<StockItem> allStocks = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            allStocks.addAll(this.stockitemsDao.getAllStockItemHoldings(con));
        } catch (SQLException e) {
            logger.error("Error", e);
        }
        return allStocks;
    }

    /**
     * Function to check if the search result got any results
     * @param jtable
     */
    private boolean checkTableEmpty(JTable jtable){
        rowCount = jtable.getRowCount();
        return rowCount <= 0;
    }

    /**
     * Function to set the textfield background to red if no search results were found
     * @param table
     */
    private void setBackgroundColorSearchFields(JTable table) {
        if(checkTableEmpty(table)){
            searchStockItemID.setBackground(new Color(247, 117, 114));
            searchStockItemName.setBackground(new Color(247, 117, 114));
        }
        else{
            searchStockItemID.setBackground(Color.WHITE);
            searchStockItemName.setBackground(Color.WHITE);
        }
    }

    /**
     * Class to make a table model with the following data: "Product ID", "Product naam" and "Voorraad"
     */
    class VoorraadModel extends AbstractTableModel {

        private String[] columnNames = {"Product ID", "Product naam", "Voorraad"};

        private final Object[][] data;
        private final int rowCount;

        private VoorraadModel(List<StockItem> stockitemsList) {
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
         * @return
         */
        //https://stackoverflow.com/questions/6592192/why-does-my-jtable-sort-an-integer-column-incorrectly
        @Override
        public Class getColumnClass(int column) {
            return switch (column) {
                case 0, 2 -> Integer.class;
                case 1 -> String.class;
                default -> String.class;
            };
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

    /**
     * Class to set the alignment of the text in a column to the left
     */
    //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
    public class LeftTableCellRenderer extends DefaultTableCellRenderer {
        protected LeftTableCellRenderer() {
            setHorizontalAlignment(JLabel.LEFT);  }

    }

    /**
     * Class to set the alignment of the text in a column to the center
     */
    public class MiddleTableCellRenderer extends DefaultTableCellRenderer {
        protected MiddleTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER);  }

    }
}
