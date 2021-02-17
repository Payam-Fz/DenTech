package ui.component;

import ui.Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a button in GUI to be inserted in menus for navigation
public class MenuNavigationButton extends ButtonWithDescription {

    private Main main;
    private String targetWindow;

    // REQUIRES: targetWindows exists in main.windows
    // EFFECTS: constructs a new navigation button given the target window, its name and
    //          description and reference to main
    public MenuNavigationButton(Main main, String targetWindow, String buttonName, String description) {
        super(buttonName, description);
        this.main = main;
        this.targetWindow = targetWindow;

        addButtonListener(new MenuNavigationButtonListener());
    }

    // Represents a listener for menu navigation button
    private class MenuNavigationButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            main.getLayout().show(main.getWindowManager(), targetWindow);
        }
    }
}
