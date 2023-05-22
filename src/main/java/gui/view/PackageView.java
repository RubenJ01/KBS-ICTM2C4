package gui.view;

import constants.*;
import gui.*;
import gui.controller.*;

import javax.swing.*;
import java.awt.*;


public class PackageView extends JPanel implements ViewBuilder {


    public static JTextField itemnummerInput;
    public static JTextField YInput;
    public static JTextField XInput;
    public static JTextField SizeInput;

    public static DefaultListModel<String> model = new DefaultListModel<>();
    private final PackageController loadController;
    private final NavbarView navbarView;



    public PackageView(CardLayout layout, JPanel root) {
        this.navbarView = new NavbarView(layout, root);
        this.loadController = new PackageController(layout, root);
        buildAndShowView();
    }

    public void buildAndShowView() {
        this.setLayout(new BorderLayout());
        this.setVisible(true);
        this.add(navbarView, BorderLayout.NORTH);


        JPanel loadInput = new JPanel();
        loadInput.setLayout(new FlowLayout());
        loadInput.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10);



        JLabel itemnummerText = new JLabel("Product Id: ");
        itemnummerText.setFont(new Font("Calibri", Font.BOLD, 20));
        loadInput.add(itemnummerText);

        itemnummerInput = new JTextField(20);
        loadInput.add(itemnummerInput);

        JLabel XText = new JLabel("X: ");
        XText.setFont(new Font("Calibri", Font.BOLD, 20));
        loadInput.add(XText);

        XInput = new JTextField(20);
        loadInput.add(XInput);


        JLabel YText = new JLabel("Y: ");
        YText.setFont(new Font("Calibri", Font.BOLD, 20));
        loadInput.add(YText);
        YInput = new JTextField(20);
        loadInput.add(YInput);

        JLabel SizeText = new JLabel("Gewicht: ");
        SizeText.setFont(new Font("Calibri", Font.BOLD, 20));
        loadInput.add(SizeText);
        SizeInput = new JTextField(20);
        loadInput.add(SizeInput);


        JPanel loadBottomBar = new JPanel();
        loadBottomBar.setLayout(new FlowLayout(FlowLayout.LEFT));
        loadBottomBar.setSize(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 10);

        JButton addLoad = new JButton("Toevoegen");
        addLoad.addActionListener(loadController::addLoadButton);
        loadBottomBar.add(addLoad);

        JPanel loadQueue = new JPanel();
        loadQueue.setBorder(BorderFactory.createLineBorder(Color.black));
        loadQueue.setLayout(new BorderLayout());

        JLabel wachtrijTxt = new JLabel("Wachtrij");
        loadQueue.add(wachtrijTxt, BorderLayout.NORTH);


        JList<String> loadList = new JList<>(model);
        JScrollPane scrollPane = new JScrollPane(loadList);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        loadQueue.add(scrollPane, BorderLayout.CENTER);

        JLabel wachtrijTitle = new JLabel("NR:");
        loadList.add(wachtrijTitle, BorderLayout.NORTH);

        JButton cancelLoad = new JButton("Stop");
        cancelLoad.addActionListener(loadController::CancelLoadButton);
        loadQueue.add(cancelLoad, BorderLayout.SOUTH);

        // wachtrij inladen
        this.add(loadQueue, BorderLayout.EAST);

        //input panel
        this.add(loadInput, BorderLayout.WEST);

        this.add(loadBottomBar, BorderLayout.SOUTH);

    }
}
