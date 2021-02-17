package model.clinic;

import model.people.Patient;
import model.people.Staff;
import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.*;

// Represents the whole dental clinic
public class Clinic implements Writable {

    private List<Room> roomsList;
    private HashMap<String, Staff> staffList;
    private HashMap<String, Patient> patientsList;

    // EFFECTS: constructs a new Clinic which is empty
    public Clinic() {
        roomsList = new ArrayList<>();
        staffList = new LinkedHashMap<>();
        patientsList = new LinkedHashMap<>();
    }

    // REQUIRES: room with the same room name doesn't exist in the rooms list
    // MODIFIES: this
    // EFFECTS: adds a new room to the roomsList of clinic
    public void addRoom(Room room) {
        roomsList.add(room);
    }

    // REQUIRES: a room already exists in the given index of rooms list
    // MODIFIES: this
    // EFFECTS: removes the room at the given index from the rooms list
    public void removeRoom(int index) {
        roomsList.remove(index);
    }

    // REQUIRES: a room already exists in the given index of rooms list
    // EFFECTS: returns the room at the given index from the rooms list
    public Room getRoom(int index) {
        return roomsList.get(index);
    }

    // EFFECTS: returns the clinic room list
    public List<Room> getRoomsList() {
        return roomsList;
    }

    // REQUIRES: staff with the same staffID doesn't exist in the staffList
    // MODIFIES: this
    // EFFECTS: adds a new staff member to the list of clinic staff
    public void addStaff(Staff staff) {
        staffList.putIfAbsent(staff.getID(), staff);
    }

    // REQUIRES: staff with the same staffID exists in the staff list
    // MODIFIES: this
    // EFFECTS: removes a specific staff from the list of clinic staff given their ID
    public void removeStaff(String staffID) {
        staffList.remove(staffID);
    }

    // REQUIRES: staff with the same staffID exists in the staff list
    // EFFECTS: returns the specified staff given their ID
    public Staff getStaff(String staffID) {
        return staffList.get(staffID);
    }

    // EFFECTS: returns a List of strings containing all clinic staff IDs
    public Set<String> getStaffIDList() {
        return staffList.keySet();
    }

    // REQUIRES: patient with the same patientID doesn't exist in the patientsList
    // MODIFIES: this
    // EFFECTS: adds a new patient to the list of patients
    public void addPatient(Patient patient) {
        patientsList.putIfAbsent(patient.getID(), patient);
    }

    // REQUIRES: patient with the same patientID exists in the patients list
    // MODIFIES: this
    // EFFECTS: removes a specific patient from the list of patients given their ID
    public void removePatient(String patientID) {
        patientsList.remove(patientID);
    }

    // REQUIRES: patient with the same patientID exist in the patients list
    // EFFECTS: returns a specified Patient given their ID
    public Patient getPatient(String patientID) {
        return patientsList.get(patientID);
    }

    // EFFECTS: returns a List of strings containing all patients IDs
    public Set<String> getPatientsIDList() {
        return patientsList.keySet();
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonClinic = new JSONObject();
        JSONArray jsonRoomList = new JSONArray();
        JSONArray jsonStaffList = new JSONArray();
        JSONArray jsonPatientList = new JSONArray();
        for (Room r : roomsList) {
            jsonRoomList.put(r.toJson());
        }
        for (Staff s : staffList.values()) {
            jsonStaffList.put(s.toJson());
        }
        for (Patient p : patientsList.values()) {
            jsonPatientList.put(p.toJson());
        }
        jsonClinic.put("roomList", jsonRoomList);
        jsonClinic.put("staffList", jsonStaffList);
        jsonClinic.put("patientList", jsonPatientList);
        return jsonClinic;
    }
}
