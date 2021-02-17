package ui.menu;

import ui.Main;
import ui.component.MenuNavigationButton;
import ui.display.StaffListDisplay;
import ui.form.Form;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a menu window to handle management of staff
public class StaffMenu extends Menu {

    // EFFECTS: constructs a new staff menu window
    public StaffMenu(Main main) {
        super(main, "Staff");

        setupGraphics();
        addBottomSection("main_menu");
    }

    @Override
    protected void addOptions(JPanel optionPanel) {
        MenuNavigationButton showListButton = new MenuNavigationButton(main,"staffList_display",
                "Show List","Display list of all staff in the clinic");
        showListButton.addButtonListener(new ShowListButtonListener());
        optionPanel.add(showListButton);

        MenuNavigationButton staffDataEntryButton = new MenuNavigationButton(main,"staffDataEntry_form",
                "Hire","Register new staff in the clinic");
        staffDataEntryButton.addButtonListener(new ShowStaffFormListener());
        optionPanel.add(staffDataEntryButton);
    }

    // Represents a listener for show staff list button
    private class ShowListButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            StaffListDisplay w = (StaffListDisplay) main.getWindow("staffList_display");
            w.updateList();
            main.getLayout().show(main.getWindowManager(), "staffList_display");
        }
    }

    // Represents a listener for staff form button
    private class ShowStaffFormListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            Form f = (Form) main.getWindow("staffDataEntry_form");
            f.clearFields();
            main.getLayout().show(main.getWindowManager(), "staffDataEntry_form");
        }
    }

}