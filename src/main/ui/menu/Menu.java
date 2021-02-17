package ui.menu;

import ui.Main;
import ui.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a menu window
public abstract class Menu extends Window {

    // EFFECTS: constructs a new menu window given the main object and title
    public Menu(Main main, String title) {
        super(main,"[ " + title + " Menu ]");
    }


    @Override
    protected void setupGraphics() {
        JPanel optionPanel = new JPanel();
        optionPanel.setLayout(new BoxLayout(optionPanel, BoxLayout.Y_AXIS));
        optionPanel.setBorder(new EmptyBorder(40,60,40,10));
        addOptions(optionPanel);
        add(optionPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: adds options of the menu to the passed panel
    protected abstract void addOptions(JPanel optionPanel);
}
