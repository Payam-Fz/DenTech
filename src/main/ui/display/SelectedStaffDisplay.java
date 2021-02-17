package ui.display;

import model.people.Staff;
import ui.Main;
import ui.Window;
import ui.component.datafield.DataOutput;
import ui.form.StaffDataEntryForm;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a window that shows data of selected staff and provides option to edit or delete
public class SelectedStaffDisplay extends Window {

    private JPanel dataPanel;
    private Staff staff;

    // EFFECTS: constructs a new selected staff window
    public SelectedStaffDisplay(Main main) {
        super(main, "Data");
        dataPanel = new JPanel();

        setupGraphics();
        addBottomSection();
    }

    @Override
    protected void setupGraphics() {
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setBorder(new LineBorder(Color.BLACK));
        add(dataPanel, BorderLayout.CENTER);
    }

    // EFFECTS: adds the edit and delete buttons to the bottom of the page
    private void addManipulateButtons() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));

        JButton editButton = new JButton("Edit");
        editButton.addActionListener(new EditStaffListener());
        buttonPanel.add(editButton);

        buttonPanel.add(Box.createRigidArea(new Dimension(20,1)));

        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new DeleteStaffListener());
        deleteButton.setBackground(Color.red);
        buttonPanel.add(deleteButton);

        dataPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        dataPanel.add(buttonPanel);
    }

    // MODIFIES: this
    // EFFECTS: sets the staff whose data is being shown in the window and
    //          fills the data fields with data of the staff
    public void setStaff(Staff selected) {
        dataPanel.removeAll();
        this.staff = selected;
        setTitle(selected.getFirstName() + "'s Data");
        dataPanel.add(new DataOutput("ID", selected.getID()));
        dataPanel.add(new DataOutput("First Name", selected.getFirstName()));
        dataPanel.add(new DataOutput("Last Name", selected.getLastName()));
        dataPanel.add(new DataOutput("Date Of Birth", selected.getDateOfBirth().toString()));
        dataPanel.add(new DataOutput("Start Work Date", selected.getStartWorkDate().toString()));
        dataPanel.add(new DataOutput("Highest Degree", selected.getHighestDegree()));
        dataPanel.add(new DataOutput("Latest School / University of Graduation", selected.getSchool()));
        dataPanel.add(new DataOutput("Hourly Wage", "$" + selected.getHourlyWage()));
        dataPanel.add(new DataOutput("Working Hours", selected.getWorkingHoursPerWeek() + " /week"));
        addManipulateButtons();
    }

    // Represents a listener for edit button
    private class EditStaffListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            main.getLayout().show(main.getWindowManager(), "staffDataEntry_form");
            StaffDataEntryForm w = (StaffDataEntryForm) main.getWindow("staffDataEntry_form");
            w.setStaff(staff);
        }
    }

    // Represents a listener for delete button
    private class DeleteStaffListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            main.getClinic().removeStaff(staff.getID());
            StaffListDisplay w = (StaffListDisplay) main.getWindow("staffList_display");
            w.updateList();
            w.footer.setText("Staff removed successfully.");
            main.getLayout().previous(main.getWindowManager());
        }
    }
}
