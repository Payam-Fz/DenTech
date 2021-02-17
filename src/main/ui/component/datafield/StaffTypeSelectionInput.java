package ui.component.datafield;

import model.people.Staff;

import javax.swing.*;

// Represents a GUI panel for selecting type of staff
public class StaffTypeSelectionInput extends DataPanel {

    private Staff.StaffType[] types =
            {Staff.StaffType.DENTIST, Staff.StaffType.HYGIENIST, Staff.StaffType.ASSISTANT, Staff.StaffType.CLERK};
    private JComboBox input;

    // EFFECTS: constructs a new staff type selection panel
    public StaffTypeSelectionInput(String labelName) {
        super(labelName);

        input = new JComboBox(types);
        add(input);
    }

    // EFFECTS: returns the inputted staff type
    public Staff.StaffType getInput() {
        return (Staff.StaffType) input.getSelectedItem();
    }

    // MODIFIES: this
    // EFFECTS: sets the value to be shown as default selection
    public void setInput(Staff.StaffType previousValue) {
        input.setSelectedItem(previousValue);
    }
}
