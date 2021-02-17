package ui.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a GUI title for a window
public class WindowTitle extends JLabel {

    // EFFECTS: constructs a new title given the title string
    public WindowTitle(String name) {
        super(name);
        setHorizontalAlignment(CENTER);
        setBorder(new EmptyBorder(20,0,20,0));
        setBackground(Color.gray);
        setFont(new Font(null, 0,30));
    }
}
