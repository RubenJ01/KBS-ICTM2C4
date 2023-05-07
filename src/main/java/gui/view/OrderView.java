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
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderView extends JPanel implements ViewBuilder {

    private static final Logger logger = LoggerFactory.getLogger(OrderView.class);
    private final NavbarView navbarView;
    private final OrderDao orderDao;
    private final OrderController orderController;

    public OrderView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.orderDao = OrderDao.getInstance();
        this.orderController = new OrderController(layout, root);
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setLayout(new BorderLayout());

        this.add(navbarView, BorderLayout.NORTH);

        List<Order> allOrders = new ArrayList<>();
        try(Connection con = DatabaseConnection.getConnection()) {
            allOrders.addAll(this.orderDao.getAllOrders(con));
        } catch (SQLException e) {
           logger.error(e.getMessage());
        }

        JList<Order> orderList = new JList<>(allOrders.toArray(new Order[0]));
        JScrollPane scrollPane = new JScrollPane(orderList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        this.add(scrollPane, BorderLayout.CENTER);

        JPanel orderBottomBar = new JPanel();
        orderBottomBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        orderBottomBar.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10);

        JButton addOrder = new JButton("Add");
        addOrder.addActionListener(orderController::addButton);
        orderBottomBar.add(addOrder);

        JButton editOrder = new JButton("Edit");
        editOrder.addActionListener(orderController::editButton);
        orderBottomBar.add(editOrder);

        this.add(orderBottomBar, BorderLayout.SOUTH);

        this.setVisible(true);
    }
}
