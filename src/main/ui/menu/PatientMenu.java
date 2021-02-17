package ui.menu;

import model.clinic.Clinic;
import model.people.Patient;
import model.treatment.Date;
import model.treatment.VisitReport;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Provides functionality for Patients menu in UI
public class PatientMenu {

    private Scanner scn;
    private Clinic clinic;

    // EFFECTS: constructs a new patient menu in UI and views this menu in console
    public PatientMenu(Scanner scn, Clinic clinic) {
        this.scn = scn;
        this.clinic = clinic;
        handlePatientMenu();
    }

    // EFFECTS: displays options of patient menu
    private void displayPatientMenu() {
        System.out.println("\n-----------------------------");
        System.out.println("\t@ [PATIENT menu] @");
        System.out.println("Select by entering the option:");
        System.out.println("\t1 > List of patients");
        System.out.println("\t2 > Register patient");
        System.out.println("\t3 > Select patient");
        System.out.println("\n\tb > Back");
    }

    // EFFECTS: handles the user choice between options of patient menu
    private void handlePatientMenu() {
        do {
            displayPatientMenu();
            String inp = scn.next();
            switch (inp) {
                case "1":
                    viewPatientsList();
                    break;
                case "2":
                    registerPatientForm();
                    break;
                case "3":
                    selectPatientForm();
                    break;
                case "b":
                case "B":
                    return;
                default:
                    System.out.println("Wrong selection! try again.");
            }
        } while (true);
    }

    // EFFECTS: displays list of all registered patients ID and name
    private void viewPatientsList() {
        System.out.println("\n-----------------------------");
        System.out.println("\t[patients LIST]");
        System.out.println("  ID \t\t\t Name");
        ArrayList<String> patientIdList = new ArrayList<>(clinic.getPatientsIDList());
        if (patientIdList.size() == 0) {
            System.out.println("There are no patients in database.");
        }
        Patient p;
        for (String id : patientIdList) {
            p = clinic.getPatient(id);
            System.out.println("" + id + "\t" + p.getFirstName() + " " + p.getLastName());
        }
    }

    // EFFECTS: registers a new patient to clinic
    private void registerPatientForm() {
        Patient p = new Patient(clinic.getPatientsIDList());
        System.out.println("\n-----------------------------");
        System.out.println("\t{patient REGISTRATION form}");
        System.out.print("First name: ");
        p.setFirstName(scn.next());
        System.out.print("\nLast name: ");
        p.setLastName(scn.next());
        System.out.print("\nPhone number: ");
        p.setPhoneNumber(scn.next());
        System.out.println("\nDate of birth: (YYYY > enter > MM > enter > DD > enter)");
        p.setDateOfBirth(getInputDate(scn));
        System.out.println("Note: (press enter if empty)");
        scn.nextLine();
        p.setNote(scn.nextLine());
        clinic.addPatient(p);
        if (clinic.getPatientsIDList().contains(p.getID())) {
            System.out.println("* Patient was registered successfully.");
        } else {
            System.out.println("*! Could not register patient.");
        }
    }

    // EFFECTS: selects a patient to perform further action on it
    private void selectPatientForm() {
        do {
            System.out.println("\n-----------------------------");
            System.out.println("\t{patient SELECT form}");
            System.out.println("Enter ID of patient to view/Remove/Edit (or enter 'B' to go back)");
            String inp = scn.next();
            if (inp.equals("B") || inp.equals("b")) {
                break;
            } else if (!clinic.getPatientsIDList().contains(inp)) {
                System.out.println("Wrong ID, try again.");
            } else {
                handleSelectedPatientMenu(inp);
            }
        } while (true);
    }

    // EFFECTS: displays the data of a patient given their patient ID
    private void viewPatientData(String patientID) {
        Patient p = clinic.getPatient(patientID);
        System.out.println("\n-----------------------------");
        System.out.println("\t[" + clinic.getPatient(patientID).getFirstName() + " data]");
        System.out.println("Name: " + p.getFirstName() + " " + p.getLastName());
        System.out.println("Phone number: " + p.getPhoneNumber());
        System.out.println("DOB: " + p.getDateOfBirth());
        System.out.println("Note: " + p.getNote());
        System.out.println("Number of visit reports: " + p.getAllVisitReportDates().size());
    }

    // EFFECTS: displays options of selected patient menu
    private void displaySelectedPatientMenu(String patientID) {
        System.out.println("\n-----------------------------");
        System.out.println("\t@ [" + clinic.getPatient(patientID).getFirstName() + "'s menu] @");
        System.out.println("Select by entering the option:");
        System.out.println("\t1 > Edit patient");
        System.out.println("\t2 > Remove patient");
        System.out.println("\t3 > Patient's visit reports");
        System.out.println("\n\tb > Back");
    }

    // EFFECTS: handles the options of selected patient menu
    private void handleSelectedPatientMenu(String patientID) {
        do {
            viewPatientData(patientID);
            displaySelectedPatientMenu(patientID);
            String inp = scn.next();
            switch (inp) {
                case "1":
                    editPatientForm(patientID);
                    break;
                case "2":
                    removePatientForm(patientID);
                    break;
                case "3":
                    handleVisitReportMenu(patientID);
                    break;
                case "b":
                case "B":
                    return;
                default:
                    System.out.println("Wrong selection! try again.");
            }
        } while (true);
    }

    // EFFECTS: edits data of a single patient given their patient ID
    private void editPatientForm(String patientID) {
        Patient p = clinic.getPatient(patientID);
        System.out.println("\n-----------------------------");
        System.out.println("\t{patient EDIT form}");
        System.out.println("For each field, enter new value or enter '/' to keep previous value.");
        System.out.print("First name: ");
        p.setFirstName(checkEmptyInputString(scn.next(), p.getFirstName()));
        System.out.print("\nLast name: ");
        p.setLastName(checkEmptyInputString(scn.next(), p.getLastName()));
        System.out.print("\nPhone number: ");
        p.setPhoneNumber(checkEmptyInputString(scn.next(), p.getPhoneNumber()));
        System.out.println("\nDate of birth: (YYYY > enter > MM > enter > DD > enter)");
        p.setDateOfBirth(checkEmptyInputDate(scn.next(), p.getDateOfBirth(), scn));
        scn.nextLine();
        System.out.println("Note: (press enter if empty)");
        p.setNote(checkEmptyInputString(scn.nextLine(), p.getNote()));
        System.out.println("* Patient was edited successfully.");
    }

    // EFFECTS: removes a patient from clinic given their patient ID
    private void removePatientForm(String patientID) {
        System.out.println("\n-----------------------------");
        System.out.println("\t{patient REMOVAL form}");
        Patient requestedPatient = clinic.getPatient(patientID);
        System.out.println("Are you sure you want to delete '" + requestedPatient.getFirstName()
                + " " + requestedPatient.getLastName() + "' ? (Y/N)");
        String inp = scn.next();
        if (inp.equals("Y") || inp.equals("y")) {
            clinic.removePatient(requestedPatient.getID());
            if (!clinic.getPatientsIDList().contains(requestedPatient.getID())) {
                System.out.println("* Patient was removed successfully.");
            } else {
                System.out.println("*! Could not remove patient.");
            }
        } else {
            System.out.println("* Removal cancelled.");
        }
    }

    // EFFECTS: displays options of visit report menu
    private void displayVisitReportMenu() {
        System.out.println("\n-----------------------------");
        System.out.println("\t@ [visit report menu] @");
        System.out.println("Select by entering the option:");
        System.out.println("\t1 > List of visit reports");
        System.out.println("\t2 > Add visit report");
        System.out.println("\t3 > Select visit report");
        System.out.println("\n\tb > Back");
    }

    // EFFECTS: handles options of visit report menu
    private void handleVisitReportMenu(String patientID) {
        do {
            displayVisitReportMenu();
            String inp = scn.next();
            switch (inp) {
                case "1":
                    viewVisitReportList(patientID);
                    break;
                case "2":
                    addVisitReportForm(patientID);
                    break;
                case "3":
                    selectVisitReportForm(patientID);
                    break;
                case "b":
                case "B":
                    return;
                default:
                    System.out.println("Wrong selection! try again.");
            }
        } while (true);
    }

    // EFFECTS: displays list of all visit reports of a patient given their patient ID
    private void viewVisitReportList(String patientID) {
        System.out.println("\n-----------------------------");
        System.out.println("[" + clinic.getPatient(patientID).getFirstName() + "'s VISIT REPORT list]");
        Patient p = clinic.getPatient(patientID);
        List<Date> vrDateList = p.getAllVisitReportDates();
        if (vrDateList.isEmpty()) {
            System.out.println("There are no visit reports for this patient.");
        } else {
            for (int i = 0; i < vrDateList.size(); i++) {
                System.out.println("[" + i + "] \t\t" + vrDateList.get(i));
            }
        }
    }

    // EFFECTS: adds a new visit report to a patient given their patient ID
    private void addVisitReportForm(String patientID) {
        System.out.println("\n-----------------------------");
        System.out.println("\t{visit report ADDITION form}");
        VisitReport vr = new VisitReport(Date.getToday());
        System.out.println("Date of visit:  (YYYY > enter > MM > enter > DD > enter)");
        vr.setDate(getInputDate(scn));
        System.out.println("Required medications: (enter one by one or enter '/' to finish)");
        scn.nextLine();
        String inp = scn.nextLine();
        while (!inp.equals("/")) {
            vr.addMedication(inp);
            inp = scn.nextLine();
        }
        System.out.println("Required procedures: (enter one by one or enter '/' to finish)");
        inp = scn.nextLine();
        while (!inp.equals("/")) {
            vr.addRequiredProcedure(inp);
            inp = scn.nextLine();
        }
        System.out.print("Visit fee:\t$");
        vr.setFee(scn.nextDouble());
        clinic.getPatient(patientID).addVisitReport(vr);
    }

    // EFFECTS: selects a specific visit report of a patient given their patient ID
    private void selectVisitReportForm(String patientID) {
        Patient p = clinic.getPatient(patientID);
        do {
            System.out.println("\n-----------------------------");
            System.out.println("\t{visit report SELECT form}");
            System.out.println("Enter number of report to view (or enter '-1' to go back)");
            int inp = scn.nextInt();
            if (inp == -1) {
                break;
            } else if (inp <= 0 || inp > p.getAllVisitReportDates().size()) {
                System.out.println("Wrong number. try again.");
            } else {
                handleSelectedVisitReportMenu(patientID, inp);
            }
        } while (true);
    }

    // EFFECTS: displays options of selected visit report menu
    private void displaySelectedVisitReportMenu(VisitReport vr) {
        System.out.println("\n-----------------------------");
        System.out.println("\t@ [" + vr.getDate() + " visit report menu] @");
        System.out.println("Select by entering the option:");
        System.out.println("\t1 > Edit visit report (not working yet)");
        System.out.println("\t2 > Remove visit report");
        System.out.println("\n\tb > Back");
    }

    // EFFECTS: handles the options of selected visit report menu
    private void handleSelectedVisitReportMenu(String patientID, int index) {
        VisitReport vr = clinic.getPatient(patientID).getVisitReport(index);
        do {
            viewVisitReport(vr);
            displaySelectedVisitReportMenu(vr);
            String inp = scn.next();
            switch (inp) {
                case "1":
                    //editVisitReportForm(vr);
                    break;
                case "2":
                    removeVisitReportForm(patientID, index);
                    break;
                case "b":
                case "B":
                    return;
                default:
                    System.out.println("Wrong selection! try again.");
            }
        } while (true);
    }

    // EFFECTS: displays the passed visit report
    private void viewVisitReport(VisitReport vr) {
        System.out.println("\n-----------------------------");
        System.out.println("\t[" + vr.getDate() + " visit report]");
        System.out.println("Is complete? " + vr.isCompleted());
        System.out.println("Date: " + vr.getDate());
        System.out.println("Fee: " + vr.getFee());
        System.out.println("\n\t[medications]");
        List<String> medications = vr.getMedicationsList();
        for (String s : medications) {
            System.out.println(medications.indexOf(s) + ".\t" + s);
        }
        viewProceduresList(vr);
    }

    // EFFECTS: displays the list of incomplete and completed procedures
    private void viewProceduresList(VisitReport vr) {
        if (!(vr.getCompletedProceduresList().isEmpty() && vr.getRequiredProceduresList().isEmpty())) {
            System.out.println("\n\t\t[procedures]");
            System.out.println("\t<required>\t|\t<completed>");
            List<String> required = vr.getRequiredProceduresList();
            List<String> complete = vr.getCompletedProceduresList();
            int index = 0;
            while (index < required.size() && index < complete.size()) {
                System.out.println(index + ". " + required.get(index) + "\t\t| " + complete.get(index));
                index++;
            }
            while (index < required.size()) {
                System.out.println(index + ". " + required.get(index) + "\t\t|");
                index++;
            }
            while (index < complete.size()) {
                System.out.println(index + ".\t\t\t\t| " + complete.get(index));
                index++;
            }
        } else {
            System.out.println("No Required or Completed procedures.");
        }
    }

    // EFFECTS: removes a visit report from a patient given their patient ID and index of visit report
    private void removeVisitReportForm(String patientID, int index) {
        Patient p = clinic.getPatient(patientID);
        int size = p.getAllVisitReportDates().size();
        System.out.println("\n-----------------------------");
        System.out.println("\t{visit report REMOVAL form}");
        System.out.println("Are you sure you want to remove visit report #" + index + " from "
                + p.getFirstName() + " " + p.getLastName() + " ? (Y/N)");
        String inp = scn.next();
        if (inp.equals("Y") || inp.equals("y")) {
            p.removeVisitReport(index);
            if (p.getAllVisitReportDates().size() == (size + 1)) {
                System.out.println("* Visit report was removed successfully.");
            } else {
                System.out.println("*! Could not remove visit report.");
            }
        } else {
            System.out.println("* Removal cancelled.");
        }
    }

    // EFFECTS: returns a date from user's input
    protected Date getInputDate(Scanner scn) {
        Date d = new Date();
        d.setYear(scn.nextInt());
        d.setMonth(scn.nextInt());
        d.setDay(scn.nextInt());
        return d;
    }

    // EFFECTS: checks whether or not the user input for a text was '/' (empty)
    //          if yes, returns previous string value. Otherwise, returns new input.
    protected String checkEmptyInputString(String newValue, String previousValue) {
        if (newValue.equals("/")) {
            return previousValue;
        } else {
            return newValue;
        }
    }

    // EFFECTS: checks whether or not the user input for a date was '/' (empty)
    //          if yes, returns previous date. Otherwise, returns new input.
    protected Date checkEmptyInputDate(String newValue, Date previousDate, Scanner scn) {
        if (newValue.equals("/")) {
            return previousDate;
        } else {
            Date d = new Date();
            d.setYear(Integer.parseInt(newValue));
            d.setMonth(scn.nextInt());
            d.setDay(scn.nextInt());
            return d;
        }
    }

    // EFFECTS: edits a visit report
    private void editVisitReportForm(VisitReport vr) {
        System.out.println("\n-----------------------------");
        System.out.println("\t{visit report EDIT form}");
        // TODO: implement
//        System.out.println("For each field, enter new value or enter '/' to keep previous value.");
//        System.out.print("First name: ");
//        p.setFirstName(checkForEmptyValueInEditForm(scn.next(), p.getFirstName()));
//        System.out.print("\nLast name: ");
//        p.setLastName(checkForEmptyValueInEditForm(scn.next(), p.getLastName()));
//        System.out.print("\nPhone number: ");
//        p.setPhoneNumber(checkForEmptyValueInEditForm(scn.next(), p.getPhoneNumber()));
//        System.out.println("\nDate of birth: (YYYY > enter > MM > enter > DD > enter)");
//        String inp = scn.next();
//        if (!inp.equals("/")) {
//            Date d = new Date();
//            d.setYear(Integer.parseInt(inp));
//            d.setMonth(scn.nextInt());
//            d.setDay(scn.nextInt());
//            p.setDateOfBirth(d);
//        }
//        System.out.println("Note: (press enter if empty)");
//        p.setNote(checkForEmptyValueInEditForm(scn.next(), p.getNote()));
//        System.out.println("* Patient was edited successfully.");
    }
}