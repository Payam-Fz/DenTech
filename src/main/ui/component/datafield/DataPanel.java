package ui.component.datafield;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a GUI panel which shows label of one piece of data
public abstract class DataPanel extends JPanel {
    protected JLabel label;

    // EFFETCS: constructs new data field in GUI
    public DataPanel(String labelName) {
        super();
        label = new JLabel(labelName + ": ");
        label.setFont(new Font(null,0,20));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBorder(new EmptyBorder(10,0,10,0));
        add(label);
        add(Box.createRigidArea(new Dimension(40, 1)));
    }

}
