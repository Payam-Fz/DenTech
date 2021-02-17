package model.treatment;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a patient visit report
public class VisitReport implements Writable {

    private Date date;
    private double fee;
    private boolean completed;
    private List<String> medicationsList;
    private List<String> requiredProceduresList;
    private List<String> completedProceduresList;

    // EFFECTS: constructs a new visit report given its date
    //          by default, it is considered incomplete and fee is zero
    public VisitReport(Date date) {
        this.date = date;
        completed = false;
        fee = 0.00;
        medicationsList = new ArrayList<String>();
        requiredProceduresList = new ArrayList<String>();
        completedProceduresList = new ArrayList<String>();
    }


    // EFFECTS: returns the date of this visit report
    public Date getDate() {
        return date;
    }

    // EFFECTS: returns the fee of this visit
    public double getFee() {
        return fee;
    }

    // EFFECTS: returns the completed status of this visit
    public boolean isCompleted() {
        return completed;
    }

    // REQUIRES: fee to be a positive double value with 2 decimal places as cents
    // MODIFIES: this
    // EFFECTS: changes the fee of this visit
    public void setFee(double fee) {
        this.fee = fee;
    }

    // REQUIRES: fee to be a positive double value with 2 decimal places as cents
    // MODIFIES: this
    // EFFECTS: changes the date of this visit
    public void setDate(Date date) {
        this.date = date;
    }

    // REQUIRES: amount to be a positive double value with 2 decimal places as cents
    // MODIFIES: this
    // EFFECTS: increases the fee of this visit by amount
    public void increaseFee(double amount) {
        double newFee = fee + amount;
        newFee = Math.round(newFee * 100);
        fee = newFee / 100;
    }

    // REQUIRES: amount to be a positive double value with 2 decimal places as cents
    // MODIFIES: this
    // EFFECTS: decreases the fee of this visit by amount
    //          if decrease amount > existing fee, fee will become 0
    public void decreaseFee(double amount) {
        double newFee = fee - amount;
        if (newFee < 0.00) {
            fee = 0.00;
        } else {
            newFee = Math.round(newFee * 100);
            fee = newFee / 100;
        }
    }

    // MODIFIES: this
    // EFFECTS: sets the completeness state of this visit report to state
    public void setCompleted(boolean state) {
        completed = state;
    }

    // MODIFIES: this
    // EFFECTS: adds the given medication to the list of medications
    public void addMedication(String medication) {
        medicationsList.add(medication);
    }

    // REQUIRES: a medication already exists in the given index of medication list
    // MODIFIES: this
    // EFFECTS: removes the medication at the given index from the list of medications
    public void removeMedication(int index) {
        medicationsList.remove(index);
    }

    // EFFECTS: returns the list of medications
    public List<String> getMedicationsList() {
        return medicationsList;
    }

    // MODIFIES: this
    // EFFECTS: adds the given procedure to the list of medications
    public void addRequiredProcedure(String procedure) {
        requiredProceduresList.add(procedure);
    }

    // REQUIRES: a procedure already exists in the given index of the required procedure list
    // MODIFIES: this
    // EFFECTS: removes the procedure at the given index from the list of required procedures
    public void removeRequiredProcedure(int index) {
        requiredProceduresList.remove(index);
    }

    // REQUIRES: a procedure already exists in the given index of the required procedure list
    // MODIFIES: this
    // EFFECTS: transfers the procedure at the given index of required procedures list to
    //          the list of completed procedures
    //          if the list becomes empty, completeness status changes to True
    public void completeProcedure(int index) {
        completedProceduresList.add(requiredProceduresList.get(index));
        removeRequiredProcedure(index);
        if (requiredProceduresList.isEmpty()) {
            setCompleted(true);
        }
    }

    // EFFECTS: returns the list of required procedures
    public List<String> getRequiredProceduresList() {
        return requiredProceduresList;
    }

    // REQUIRES: a procedure already exists in the given index of the completed procedure list
    // MODIFIES: this
    // EFFECTS: removes the procedure at the given index from the list of completed procedures
    public void removeCompletedProcedure(int index) {
        completedProceduresList.remove(index);
    }

    // EFFECTS: returns the list of completed procedures
    public List<String> getCompletedProceduresList() {
        return completedProceduresList;
    }

    // EFFECTS: parses a visit report from JSON object and returns it
    public static VisitReport parseVisitReport(JSONObject jsonVisitReport) {
        Date d = Date.parseDate(jsonVisitReport.getString("date"));
        VisitReport vr = new VisitReport(d);
        vr.setFee(jsonVisitReport.getDouble("fee"));
        vr.setCompleted(jsonVisitReport.getBoolean("completed"));
        JSONArray jsonMedicationList = jsonVisitReport.getJSONArray("medicationList");
        for (int i = 0; i < jsonMedicationList.length(); i++) {
            vr.addMedication(jsonMedicationList.getString(i));
        }
        JSONArray jsonRequiredProcedureList = jsonVisitReport.getJSONArray("requiredProcedureList");
        for (int i = 0; i < jsonRequiredProcedureList.length(); i++) {
            vr.addRequiredProcedure(jsonRequiredProcedureList.getString(i));
        }
        JSONArray jsonCompletedProcedureList = jsonVisitReport.getJSONArray("completedProcedureList");
        int numRequiredProcedure = vr.requiredProceduresList.size();
        for (int i = 0; i < jsonCompletedProcedureList.length(); i++) {
            vr.addRequiredProcedure(jsonCompletedProcedureList.getString(i));
            vr.completeProcedure(numRequiredProcedure + i);
        }

        return vr;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonVisitReport = new JSONObject();
        jsonVisitReport.put("date", date.toString());
        jsonVisitReport.put("fee", fee);
        jsonVisitReport.put("completed",completed);
        JSONArray jsonMedicationList = new JSONArray();
        JSONArray jsonRequiredProcedureList = new JSONArray();
        JSONArray jsonCompletedProcedureList = new JSONArray();
        for (String med : medicationsList) {
            jsonMedicationList.put(med);
        }
        for (String req : requiredProceduresList) {
            jsonRequiredProcedureList.put(req);
        }
        for (String com : completedProceduresList) {
            jsonCompletedProcedureList.put(com);
        }
        jsonVisitReport.put("medicationList", jsonMedicationList);
        jsonVisitReport.put("requiredProcedureList", jsonRequiredProcedureList);
        jsonVisitReport.put("completedProcedureList", jsonCompletedProcedureList);
        return jsonVisitReport;
    }
}
