package ui.display;

import model.people.Patient;
import ui.Main;
import ui.component.InteractiveListItem;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

// Represents a window that shows list of patients
public class PatientListDisplay extends PersonListDisplay {

    // EFFECTS: constructs new Patient List window
    public PatientListDisplay(Main main) {
        super(main, "Patient List");

        setupGraphics();
        addBottomSection();
    }

    @Override
    public void updateList() {
        List<String> idList = new ArrayList<>(main.getClinic().getPatientsIDList());
        Patient p;
        for (String id : idList) {
            p = main.getClinic().getPatient(id);
            personListPanel.add(new InteractiveListItem(id, p.getFirstName() + " " + p.getLastName(),
                    new ListItemMouseListener(p)));
        }
    }

    // Represents a button listener for each item of the list
    private class ListItemMouseListener extends MouseAdapter {

        private Patient selected;

        private ListItemMouseListener(Patient p) {
            this.selected = p;
        }

        // EFFECTS: Selects the person and redirects to data of that person
        public void mouseClicked(MouseEvent e) {
            main.getLayout().show(main.getWindowManager(), "selectedPatient_display");
            SelectedPatientDisplay w = (SelectedPatientDisplay) main.getWindow("selectedPatient_display");
            w.setPatient(selected);
        }
    }
}
