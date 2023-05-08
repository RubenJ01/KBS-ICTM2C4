package gui.view.dialog;

import javax.swing.*;
import java.awt.*;

public class AddOrderDialog extends JDialog {

    public AddOrderDialog() {
        this.setModal(true);
        this.setLayout(new GridLayout(5, 2));
        this.setSize(new Dimension(500, 500));


    }

}
