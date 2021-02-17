package ui.form;

import model.people.Staff;
import ui.Main;
import ui.component.datafield.*;
import ui.display.StaffListDisplay;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a form window for adding or updating data of staff
public class StaffDataEntryForm extends Form {

    private boolean isForNewStaff = true;
    private Staff staff;
    private StaffTypeSelectionInput type;
    private StringInput firstName;
    private StringInput lastName;
    private StringInput phoneNumber;
    private DateInput dateOfBirth;
    private DateInput startWorkDate;
    private StringInput highestDegree;
    private StringInput school;
    private DoubleInput hourlyWage;
    private IntegerInput workingHoursPerWeek;

    // EFFECTS: constructs a new staff data entry form
    public StaffDataEntryForm(Main main) {
        super(main, "Staff Data Entry");

        initializeFields();
        setupGraphics();
        addSubmitButtonListener(new StaffDataSubmitListener());
        addBottomSection("staff_menu");
    }

    // MODIFIES: this
    // EFFECTS: initializes all fields in the form
    private void initializeFields() {
        type =  new StaffTypeSelectionInput("Staff Type");
        firstName = new StringInput("First Name");
        lastName = new StringInput("Last Name");
        phoneNumber = new StringInput("Phone Number");
        dateOfBirth = new DateInput("Date of Birth");
        startWorkDate = new DateInput("Start Work Date");
        highestDegree = new StringInput("Highest Degree");
        school = new StringInput("Latest School / University of Graduation");
        hourlyWage = new DoubleInput("Hourly Wage ($)");
        workingHoursPerWeek = new IntegerInput("Working Hours (per week)");
    }

    @Override
    protected void addFields() {
        fieldPanel.add(type);
        fieldPanel.add(firstName);
        fieldPanel.add(lastName);
        fieldPanel.add(phoneNumber);
        fieldPanel.add(dateOfBirth);
        fieldPanel.add(startWorkDate);
        fieldPanel.add(highestDegree);
        fieldPanel.add(school);
        fieldPanel.add(hourlyWage);
        fieldPanel.add(workingHoursPerWeek);
    }

    @Override
    public void clearFields() {
        fieldPanel.removeAll();
        initializeFields();
        setupGraphics();
        isForNewStaff = true;
        footer.setText(null);
    }

    public Staff getStaff() {
        return staff;
    }

    // MODIFIES: this
    // EFFECTS: sets the staff whose data is being updated
    //          changes the functionality of this form to be for updating data of existing staff
    //          represents data of the passed staff in each field
    public void setStaff(Staff staff) {
        clearFields();
        this.staff = staff;
        isForNewStaff = false;
        type.setInput(staff.getType());
        firstName.setInput(staff.getFirstName());
        lastName.setInput(staff.getLastName());
        phoneNumber.setInput(staff.getPhoneNumber());
        dateOfBirth.setInput(staff.getDateOfBirth());
        startWorkDate.setInput(staff.getStartWorkDate());
        highestDegree.setInput(staff.getHighestDegree());
        school.setInput(staff.getSchool());
        hourlyWage.setInput(staff.getHourlyWage());
        workingHoursPerWeek.setInput(staff.getWorkingHoursPerWeek());
    }

    // Represents a listener for submission button
    private class StaffDataSubmitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            if (isForNewStaff) {
                staff = new Staff(type.getInput(), main.getClinic().getStaffIDList());
            }
            try {
                staff.setFirstName(firstName.getInput());
                staff.setLastName(lastName.getInput());
                staff.setPhoneNumber(phoneNumber.getInput());
                staff.setDateOfBirth(dateOfBirth.getInput());
                staff.setStartWorkDate(startWorkDate.getInput());
                staff.setHighestDegree(highestDegree.getInput());
                staff.setSchool(school.getInput());
                staff.setHourlyWage(hourlyWage.getInput());
                staff.setWorkingHoursPerWeek(workingHoursPerWeek.getInput());
                main.getClinic().addStaff(staff);
                clearFields();
                StaffListDisplay w = (StaffListDisplay) main.getWindow("staffList_display");
                w.updateList();
                w.footer.setText("Data submitted successfully.");
                main.getLayout().show(main.getWindowManager(), "staffList_display");
            } catch (Exception exception) {
                footer.setText("Invalid or Empty field.");
            }

        }
    }
}
