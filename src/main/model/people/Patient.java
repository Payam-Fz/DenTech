package model.people;

import model.treatment.Date;
import model.treatment.VisitReport;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.*;

// Represents a registered patient of the clinic
public class Patient extends Person {
    private String note;
    private List<VisitReport> visitReportList;

    // EFFECTS: constructs a new empty Patient with an auto-generated ID
    //          FirstName, LastName and PhoneNumber are empty and Birthdate is today
    public Patient(Set<String> patientIDList) {
        super();
        visitReportList = new ArrayList<>();
        id = generateID(patientIDList, "p-", 6);
    }

    // REQUIRES: the suggested ID adheres to the format of: 'p-' + an integer between 100000-999999
    // EFFECTS: constructs a new empty Patient with the suggested ID
    //          if suggested ID is already taken, uses an auto-generated one
    //          by default, every field except ID is empty/null
    public Patient(String suggestedID, Set<String> patientIDList) {
        super();
        note = "";
        visitReportList = new ArrayList<>();
        if (!patientIDList.contains(suggestedID)) {
            id = suggestedID;
        } else {
            id = generateID(patientIDList, "p-", 6);
        }
    }

    // EFFECTS: constructs a new Patient given their ID
    //          this private constructor is tend to be used only by parser
    private Patient(String id) {
        visitReportList = new ArrayList<>();
        this.id = id;
    }

    // EFFECTS: returns the note for this patient
    public String getNote() {
        return note;
    }

    // MODIFIES: this
    // EFFECTS: changes the note for this patient
    public void setNote(String note) {
        this.note = note;
    }

    // REQUIRES: a visit report with the same date doesn't already exist in the patient's visit list
    // MODIFIES: this
    // EFFECTS: adds a new report to list of patient's visit reports
    public void addVisitReport(VisitReport report) {
        visitReportList.add(report);
    }

    // REQUIRES: visitReport of today already exists in the patients visit lists
    // EFFECTS: returns today's report of patient
    public VisitReport getTodayReport() {
        Date today = Date.getToday();
        for (VisitReport r : visitReportList) {
            if (r.getDate().equals(Date.getToday())) {
                return r;
            }
        }
        return null;
    }

    // EFFECTS: returns the list of dates for all visit reports with the same order as
    //          they are stored
    public List<Date> getAllVisitReportDates() {
        ArrayList<Date> visitDates = new ArrayList<>();
        for (VisitReport vr : visitReportList) {
            visitDates.add(vr.getDate());
        }
        return visitDates;
    }

    // REQUIRES: a report exists in the given index
    // EFFECTS: returns the visit report given its index in the patients visit report list
    public VisitReport getVisitReport(int index) {
        return visitReportList.get(index);
    }

    // REQUIRES: a report exists in the given index
    // MODIFIES: this
    // EFFECTS: removes the visit report from the patients visit report list given its index
    public void removeVisitReport(int index) {
        visitReportList.remove(index);
    }

    // EFFECTS: parses a patient from JSON object and returns it
    public static Patient parsePatient(JSONObject jsonPatient) {
        String jsonStringValue = jsonPatient.getString("id");
        Patient p = new Patient(jsonStringValue);
        jsonStringValue = jsonPatient.getString("firstName");
        p.setFirstName(jsonStringValue);
        jsonStringValue = jsonPatient.getString("lastName");
        p.setLastName(jsonStringValue);
        jsonStringValue = jsonPatient.getString("phoneNumber");
        p.setPhoneNumber(jsonStringValue);
        jsonStringValue = jsonPatient.getString("dateOfBirth");
        Date d = Date.parseDate(jsonStringValue);
        p.setDateOfBirth(d);
        jsonStringValue = jsonPatient.getString("note");
        p.setNote(jsonStringValue);
        JSONArray jsonVisitList = jsonPatient.getJSONArray("visitReportList");
        for (Object obj : jsonVisitList) {
            JSONObject jsonVisitReport = (JSONObject) obj;
            VisitReport vr = VisitReport.parseVisitReport(jsonVisitReport);
            p.addVisitReport(vr);
        }
        return p;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonPatient = new JSONObject();
        jsonPatient.put("id", id);
        jsonPatient.put("firstName", firstName);
        jsonPatient.put("lastName", lastName);
        jsonPatient.put("phoneNumber", phoneNumber);
        jsonPatient.put("dateOfBirth", dateOfBirth.toString());
        jsonPatient.put("note", note);
        JSONArray jsonVisitList = new JSONArray();
        for (VisitReport vr : visitReportList) {
            jsonVisitList.put(vr.toJson());
        }
        jsonPatient.put("visitReportList", jsonVisitList);
        return jsonPatient;
    }
}
