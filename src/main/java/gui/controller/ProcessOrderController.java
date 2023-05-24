package gui.controller;

import database.dao.OrderDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.event.ActionEvent;

public class ProcessOrderController {

    private static final Logger logger = LoggerFactory.getLogger(ProcessOrderController.class);
    private final OrderDao orderDao = OrderDao.getInstance();

    public void processButton(ActionEvent actionEvent) {
    }
}