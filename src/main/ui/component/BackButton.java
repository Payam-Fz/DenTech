package ui.component;

import ui.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

// Represents a back button in GUI
public class BackButton extends JButton {

    private Main main;
    private String destinationWindow;

    // EFFECTS: constructs a new back button
    public BackButton(Main main) {
        super("Back");
        this.main = main;

        setBackground(Color.YELLOW);
        setMargin(new Insets(20,30,20,30));
        setFont(new Font(null,0,14));
        addActionListener(new BackButtonListener());
    }

    // REQUIRES: destination name already exist in the main.windows
    // EFFECTS: constructs a new back button with a specific destination
    public BackButton(Main main, String destination) {
        this(main);
        this.destinationWindow = destination;
    }

    // Represents a listener for the button to navigate to the desired window
    private class BackButtonListener extends AbstractAction {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (destinationWindow != null) {
                main.getLayout().show(main.getWindowManager(), destinationWindow);
            } else {
                main.getLayout().previous(main.getWindowManager());
            }
        }
    }
}
