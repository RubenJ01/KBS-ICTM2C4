package gui.view;

import constants.Constants;
import database.dao.StockItemDao;
import database.model.StockItem;
import database.util.DatabaseConnection;
import gui.ViewBuilder;
import gui.model.StockModel;
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
        StockModel stockModel = new StockModel(allStocks);
        JTable table = new JTable(stockModel);

        //Search panel
        JPanel stockBottomBar = new JPanel();
        stockBottomBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        stockBottomBar.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10);
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
        TableRowSorter<StockModel> sorter = new TableRowSorter<>(stockModel);
        searchStockItemID.getDocument().addDocumentListener(getDocumentListenerStockItemID(table, sorter));
        searchStockItemName.getDocument().addDocumentListener(getDocumentListenerStockItemName(table, sorter));
        table.setRowSorter(sorter);


        //Custom rowheights
        table.setRowHeight(Constants.SCREEN_HEIGHT / 27);

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
    private DocumentListener getDocumentListenerStockItemName(JTable jtable, TableRowSorter<StockModel> sorter) {
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
    private DocumentListener getDocumentListenerStockItemID(JTable jtable, TableRowSorter<StockModel> sorter) {
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
        if(rowCount <= 0){
            return true;
        }
        return false;
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
     * Class to set the alignment of the text in a column to the left
     */
    //https://stackoverflow.com/questions/3467052/set-right-alignment-in-jtable-column
    public static class LeftTableCellRenderer extends DefaultTableCellRenderer {
        protected LeftTableCellRenderer() {
            setHorizontalAlignment(JLabel.LEFT);  }

    }

    /**
     * Class to set the alignment of the text in a column to the center
     */
    public static class MiddleTableCellRenderer extends DefaultTableCellRenderer {
        protected MiddleTableCellRenderer() {
            setHorizontalAlignment(JLabel.CENTER);  }

    }
}
