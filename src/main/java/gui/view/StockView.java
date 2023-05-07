package gui.view;

import constants.Constants;
import database.dao.StockitemsDao;
import database.model.Stockitemholdings;
import database.model.Stockitems;
import database.util.DatabaseConnection;
import gui.ViewBuilder;
import gui.model.StockModel;
import gui.controller.StockController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockView extends JPanel implements ViewBuilder {

    private static final Logger logger = LoggerFactory.getLogger(StockView.class);
    private final NavbarView navbarView;
    private StockModel stockModel;
    private final StockitemsDao stockitemsDao;
    private StockController stockController;
    private int yProduct;

    public StockView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.stockModel = new StockModel();
        this.stockitemsDao = StockitemsDao.getInstance();
        this.stockController = new StockController(layout, root);
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);

        List<Stockitems> allStocks = new ArrayList<>();
        try(Connection con = DatabaseConnection.getConnection()) {
            allStocks.addAll(this.stockitemsDao.getAllStockItemHoldings(con));
        } catch (SQLException e) {
            logger.error("Error", e);

        }

        JTable table = new JTable(new VoorraadModel(allStocks));
        table.setAutoCreateRowSorter(true);
        //https://www.codejava.net/java-se/swing/setting-column-width-and-row-height-for-jtable
        table.setRowHeight(Constants.SCREEN_HEIGHT/27);

        table.getColumnModel().getColumn(0).setCellRenderer(new LeftTableCellRenderer());
        table.getColumnModel().getColumn(2).setCellRenderer(new MiddleTableCellRenderer());


        JScrollPane scrollpane = new JScrollPane(table);
        this.add(scrollpane);

        this.setVisible(true);
    }

    class VoorraadModel extends AbstractTableModel {

        private String[] columnNames = {"Product ID", "Product naam", "Voorraad"};

        private final Object[][] data;
        private final int rowCount;

        private VoorraadModel(List<Stockitems> stockitemsList) {
            data = new Object[stockitemsList.size()][3];
            this.rowCount = stockitemsList.size();
            for(int i = 0; i < stockitemsList.size(); i++) {
                Stockitems item = stockitemsList.get(i);
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
