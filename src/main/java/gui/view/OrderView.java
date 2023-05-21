package gui.view;

import constants.Constants;
import database.dao.OrderDao;
import database.model.Order;
import database.util.DatabaseConnection;
import gui.ViewBuilder;
import gui.controller.OrderController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class OrderView extends JPanel implements ViewBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderView.class);

    private final NavbarView navbarView;
    private final OrderDao orderDao;
    private final OrderController orderController;
    private final List<Order> allOrders = new ArrayList<>();
    private final DefaultListModel<Order> orderListModel;
    private final JLabel totalOrders;
    private final JLabel currentVisibleOrders;

    public OrderView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.orderDao = OrderDao.getInstance();
        this.totalOrders = new JLabel();
        this.currentVisibleOrders = new JLabel();
        this.orderListModel = new DefaultListModel<>();
        this.orderController = new OrderController(layout, root, totalOrders, orderListModel, currentVisibleOrders);
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.add(navbarView, BorderLayout.NORTH);

        JList<Order> orderList = new JList<>();
        orderList.setModel(orderListModel);
        orderList.setSelectionBackground(Color.GRAY);
        orderList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        orderList.addListSelectionListener(e -> orderController.listSelectionListener(orderList));
        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel singleOrder = new JPanel();
        singleOrder.setBackground(Color.WHITE);

        orderList.addListSelectionListener(e -> orderController.listSelected(e, orderList, singleOrder));
        orderList.setSelectionBackground(Color.GRAY);

        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridLayout(1, 2));
        centralPanel.add(scrollPane);
        this.add(centralPanel, BorderLayout.CENTER);
        centralPanel.add(singleOrder);


        JPanel orderBottomBarButtons = new JPanel();
        orderBottomBarButtons.setLayout(new FlowLayout(FlowLayout.LEFT));

        JPanel orderBottomBar = new JPanel();
        orderBottomBar.setLayout(new GridLayout(1, 2));
        orderBottomBar.add(orderBottomBarButtons);
        orderBottomBar.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10);

        JButton addOrder = new JButton("Toevoegen");
        addOrder.addActionListener(orderController::addButton);
        orderBottomBarButtons.add(addOrder);

        JButton editOrder = new JButton("Bewerken");
        editOrder.addActionListener(e -> orderController.editButton(orderList));
        orderBottomBarButtons.add(editOrder);

        JLabel filterOrder = new JLabel("Filters:");
        JCheckBox filterPickedOrder = new JCheckBox("Niet Gepickt");
        filterPickedOrder.addActionListener(e -> orderController.filterPickedOrder(orderList, filterPickedOrder.isSelected(),
                orderListModel ,currentVisibleOrders));

        JTextField searchOrderTextField = new JTextField();
        searchOrderTextField.setText("Zoeken...");
        searchOrderTextField.setPreferredSize(new Dimension(Constants.SCREEN_WIDTH / 20, Constants.SCREEN_HEIGHT / 27));
        searchOrderTextField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                orderController.searchTextField(orderList, orderListModel, searchOrderTextField.getText(),
                        filterPickedOrder, currentVisibleOrders);
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
               orderController.searchTextField(orderList, orderListModel, searchOrderTextField.getText(),
                       filterPickedOrder, currentVisibleOrders);
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                orderController.searchTextField(orderList, orderListModel, searchOrderTextField.getText(),
                        filterPickedOrder, currentVisibleOrders);
            }
        });

        // add a placeholder to the searchOrderTextField
        searchOrderTextField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchOrderTextField.getText().equals("Zoeken...")) {
                    searchOrderTextField.setText("");
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchOrderTextField.getText().isEmpty()) {
                    searchOrderTextField.setText("Zoeken...");
                }
            }
        });

        orderBottomBarButtons.add(searchOrderTextField);
        orderBottomBarButtons.add(filterOrder);
        orderBottomBarButtons.add(filterPickedOrder);

        this.totalOrders.setText(String.format("Totaal aantal orders: %d", allOrders.size()));
        this.currentVisibleOrders.setText(String.format("Aantal zichtbare orders: %d", orderList.getVisibleRowCount()));

        JPanel orderBottomBarText = new JPanel();
        orderBottomBarText.setLayout(new FlowLayout(FlowLayout.RIGHT));
        orderBottomBarText.add(currentVisibleOrders);
        orderBottomBarText.add(totalOrders);

        orderBottomBar.add(orderBottomBarText);

        this.add(orderBottomBar, BorderLayout.SOUTH);

        Executors.newSingleThreadExecutor().execute(() -> {
            loadAllOrders(orderListModel);
        });

        this.setVisible(true);
    }

    /**
     * Loads all order objects from the database in a separate thread and adds them to the list.
     */
    public void loadAllOrders(DefaultListModel<Order> orderDefaultListModel) {
        try (Connection con = DatabaseConnection.getConnection()) {
            this.allOrders.addAll(this.orderDao.getAllOrders(con, orderDefaultListModel, totalOrders, currentVisibleOrders));
        } catch (SQLException e) {
            logger.error(e.getMessage());
        }
    }

}
