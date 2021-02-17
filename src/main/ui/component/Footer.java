package ui.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a footer on the bottom left of the screen to give feedback to user
public class Footer extends JLabel {

    // EFFECTS: constructs a new footer
    public Footer() {
        super();
        setAlignmentY(BOTTOM);
        setAlignmentX(LEFT);
        setBorder(new EmptyBorder(0,10,10,0));
        setFont(new Font("courier", 0,14));
    }
}
