package ui.display;

import model.people.Staff;
import ui.Main;
import ui.component.InteractiveListItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// Represents a window that shows list of staff
public class StaffListDisplay extends PersonListDisplay {

    // EFFECTS: constructs a new staff List window
    public StaffListDisplay(Main main) {
        super(main, "Staff List");

        setupGraphics();
        addBottomSection();
    }

    @Override
    public void updateList() {
        personListPanel.removeAll();
        setupGraphics();
        List<String> idList = new ArrayList<>(main.getClinic().getStaffIDList());
        Staff s;
        for (String id : idList) {
            s = main.getClinic().getStaff(id);
            personListPanel.add(new InteractiveListItem(id, s.getFirstName() + " " + s.getLastName(),
                    new ListItemMouseListener(s)));
            personListPanel.add(Box.createRigidArea(new Dimension(1, 10)));
        }
    }

    // Represents a new listener for each list item
    private class ListItemMouseListener extends MouseAdapter {

        private Staff selected;

        private ListItemMouseListener(Staff s) {
            this.selected = s;
        }

        // EFFECTS: Selects the person and redirects to data of that person
        public void mouseClicked(MouseEvent e) {
            main.getLayout().show(main.getWindowManager(), "selectedStaff_display");
            SelectedStaffDisplay w = (SelectedStaffDisplay) main.getWindow("selectedStaff_display");
            w.setStaff(selected);
        }
    }
}
