package gui.view.dialog;

import gui.model.PackageModel;
import gui.model.RobotQueue;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class PlacePackageDialog extends JDialog implements ActionListener {

    private boolean gaVerder;
    private JButton cancelButton;
    private JButton verderButton;

    private PackageModel packageModel;

    public PlacePackageDialog(PackageModel packageModel){
        this.packageModel=packageModel;
        this.setModal(true);
        this.setLayout(new GridLayout(5, 2));
        this.setSize(new Dimension(500, 500));
        JLabel label = new JLabel("Plaats pakket op de palletvork");

        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);

        verderButton = new JButton("Ga verder");
        verderButton.addActionListener(this);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(cancelButton);
        buttonPanel.add(verderButton);

        JPanel contentPanel = new JPanel();
        contentPanel.add(label);
        contentPanel.add(buttonPanel);

        this.setContentPane(contentPanel);
        this.pack();
        setVisible(true);
    }

    public void setGaVerder(boolean gaVerder) {
        this.gaVerder = gaVerder;
    }

    public boolean isGaVerder() {
        return gaVerder;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==verderButton){
            gaVerder=true;
            setVisible(false);
            RobotQueue.inladen(packageModel);

        }
        if(e.getSource()==cancelButton){
            gaVerder=false;
            RobotQueue.removeFirstItem(packageModel);
            RobotQueue.printQueue();
            setVisible(false);
        }

    }
}
