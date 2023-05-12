package gui.view.dialog;

import database.dao.CustomerDao;
import database.dao.PeopleDao;
import database.model.Customer;
import database.model.Person;
import database.util.DatabaseConnection;
import gui.ViewBuilder;
import gui.controller.AddOrderController;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;
import utility.DateLabelFormatter;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class AddOrderDialog extends JDialog implements ViewBuilder {

    private final CustomerDao customerDao = CustomerDao.getInstance();
    private final PeopleDao peopleDao = PeopleDao.getInstance();
    private final AddOrderController addOrderController = new AddOrderController();

    public AddOrderDialog() {
        buildAndShowView();
    }

    @Override
    public void buildAndShowView() {
        this.setModal(true);
        this.setLayout(new BorderLayout());
        this.setSize(new Dimension(375, 280));

        JLabel header = new JLabel("Order Toevoegen", SwingConstants.CENTER);
        header.setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
        header.setFont(header.getFont().deriveFont(Font.BOLD, 14f));
        this.add(header, BorderLayout.NORTH);

        JPanel centerContent = new JPanel();
        centerContent.setLayout(new GridLayout(6, 2));
        centerContent.setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.BLACK));
        this.add(centerContent, BorderLayout.CENTER);

        List<Customer> customers = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            customers = customerDao.getAllCustomers(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel klantIdLabel = new JLabel("Klant:");
        JComboBox<Customer> klantIdField = new JComboBox<>(customers.toArray(new Customer[0]));

        centerContent.add(klantIdLabel);
        centerContent.add(klantIdField);

        List<Person> people = new ArrayList<>();
        try (Connection con = DatabaseConnection.getConnection()) {
            people = peopleDao.getAllPeople(con);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        JLabel salesPersonIdLabel = new JLabel("Medewerker:");
        JComboBox<Person> salesPersonIdField = new JComboBox<>(people.toArray(new Person[0]));

        centerContent.add(salesPersonIdLabel);
        centerContent.add(salesPersonIdField);

        JLabel contactPersonIdLabel = new JLabel("Contactpersoon:");
        JComboBox<Person> contactPersonIdField = new JComboBox<>(people.toArray(new Person[0]));

        centerContent.add(contactPersonIdLabel);
        centerContent.add(contactPersonIdField);

        JLabel orderDateLabel = new JLabel("Order datum:");
        UtilDateModel model = new UtilDateModel();
        Properties p = new Properties();
        p.put("text.today", "Today");
        p.put("text.month", "Month");
        p.put("text.year", "Year");
        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
        JDatePickerImpl orderDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        centerContent.add(orderDateLabel);
        centerContent.add(orderDateField);

        JLabel expectedDeliveryDateLabel = new JLabel("Verwachte leverdatum:");
        JDatePickerImpl expectedDeliveryDateField = new JDatePickerImpl(datePanel, new DateLabelFormatter());

        centerContent.add(expectedDeliveryDateLabel);
        centerContent.add(expectedDeliveryDateField);

        JLabel isUndersupplyBackorderedLabel = new JLabel("Onder voorraad backorderen:");
        JCheckBox isUndersupplyBackorderedField = new JCheckBox();

        centerContent.add(isUndersupplyBackorderedLabel);
        centerContent.add(isUndersupplyBackorderedField);

        JButton addButton = new JButton("Toevoegen");
        this.add(addButton, BorderLayout.SOUTH);


    }
}
