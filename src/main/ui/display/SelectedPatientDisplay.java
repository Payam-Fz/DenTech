package ui.display;

import model.people.Patient;
import ui.Main;
import ui.Window;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

// Represents a window that shows data of selected patient and provides option to edit or delete
public class SelectedPatientDisplay extends Window {

    private JPanel dataPanel;
    private Patient patient;

    // EFFECTS: constructs a new selected staff window
    public SelectedPatientDisplay(Main main) {
        super(main, "Data");
    }

    @Override
    protected void setupGraphics() {
        dataPanel.setLayout(new BoxLayout(dataPanel, BoxLayout.Y_AXIS));
        dataPanel.setBorder(new LineBorder(Color.BLACK));
        add(dataPanel, BorderLayout.CENTER);
    }

    // EFFECTS: sets the patient whose data is being displayed in the window and
    //          fills the data fields with data of the staff
    public void setPatient(Patient selected) {

    }
}
