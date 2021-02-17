package model.people;

import model.treatment.Date;
import org.json.JSONObject;

import java.util.Set;

// Represents a staff member in the clinic
public class Staff extends Person {
    private StaffType type;
    private Date startWorkDate;
    private String highestDegree;
    private String school;
    private double hourlyWage;
    private int workingHoursPerWeek;


    // REQUIRES: the suggested ID adheres to the format of: 'xx-' + an integer between 100-999
    //          where xx corresponds to type of staff: dn, hy, as, cl
    // EFFECTS: constructs a new empty Staff of given type with the suggested ID
    //          if suggested ID is already taken, uses an auto-generated one
    //          by default, every field except ID and type is empty, dates are Today
    public Staff(StaffType type, String suggestedID, Set<String> staffIDList) {
        super();
        this.type = type;
        startWorkDate = Date.getToday();
        hourlyWage = 0;
        workingHoursPerWeek = 0;
        highestDegree = "";
        school = "";
        if (!staffIDList.contains(suggestedID)) {
            id = suggestedID;
        } else {
            id = generateID(staffIDList, getStaffInitialChar(type), 3);
        }
    }

    // EFFECTS: constructs a new empty Staff of given type with an auto-generated ID
    //          by default, every field except ID and type is empty
    public Staff(StaffType type, Set<String> staffIDList) {
        super();
        this.type = type;
        startWorkDate = null;
        hourlyWage = 0;
        workingHoursPerWeek = 0;
        highestDegree = "";
        school = "";
        id = generateID(staffIDList, getStaffInitialChar(type), 3);
    }

    // EFFECTS: constructs a new Staff given their ID and type
    //          this private constructor is tend to be used only by parser
    private Staff(StaffType type, String id) {
        this.type = type;
        this.id = id;
    }

    // EFFECTS: returns the initial characters of id for each type of staff
    private String getStaffInitialChar(StaffType type) {
        String initial;
        switch (type) {
            case DENTIST:
                initial = "dn-";
                break;
            case HYGIENIST:
                initial = "hy-";
                break;
            case ASSISTANT:
                initial = "as-";
                break;
            default:
                initial = "cl-";
                break;
        }
        return initial;
    }

    // EFFECTS: returns the type of this staff
    public StaffType getType() {
        return type;
    }

    // EFFECTS: returns the starting work date of this staff
    public Date getStartWorkDate() {
        return startWorkDate;
    }


    // EFFECTS: returns the hourly wage of this staff
    public double getHourlyWage() {
        return hourlyWage;
    }

    // EFFECTS: returns the current working hours per week of this staff
    public int getWorkingHoursPerWeek() {
        return workingHoursPerWeek;
    }

    // EFFECTS: returns the highest educational degree of this staff
    public String getHighestDegree() {
        return highestDegree;
    }

    // EFFECTS: returns the graduation university of this staff
    public String getSchool() {
        return school;
    }

    // MODIFIES: this
    // EFFECTS: changes the start work date of this staff
    public void setStartWorkDate(Date startWorkDate) {
        this.startWorkDate = startWorkDate;
    }

    // REQUIRES: hourly wage to be a number with 2 decimal places as cent
    // MODIFIES: this
    // EFFECTS: changes the hourly wage of this staff
    public void setHourlyWage(double hourlyWage) {
        this.hourlyWage = hourlyWage;
    }

    // REQUIRES: working hours per week to be between 0 - 168 inclusively
    // MODIFIES: this
    // EFFECTS: changes the working hours per week of this staff
    public void setWorkingHoursPerWeek(int workingHoursPerWeek) {
        this.workingHoursPerWeek = workingHoursPerWeek;
    }

    // MODIFIES: this
    // EFFECTS: changes the highest educational degree of this staff
    public void setHighestDegree(String highestDegree) {
        this.highestDegree = highestDegree;
    }

    // MODIFIES: this
    // EFFECTS: changes the graduation university of this staff
    public void setSchool(String school) {
        this.school = school;
    }

    // EFFECTS: parses a staff from JSON object and returns it
    public static Staff parseStaff(JSONObject jsonStaff) {
        StaffType type = StaffType.valueOf(jsonStaff.getString("type"));
        Staff s = new Staff(type, jsonStaff.getString("id"));
        s.setFirstName(jsonStaff.getString("firstName"));
        s.setLastName(jsonStaff.getString("lastName"));
        s.setPhoneNumber(jsonStaff.getString("phoneNumber"));
        Date d = Date.parseDate(jsonStaff.getString("dateOfBirth"));
        s.setDateOfBirth(d);
        d = Date.parseDate(jsonStaff.getString("startWorkDate"));
        s.setStartWorkDate(d);
        s.setHighestDegree(jsonStaff.getString("highestDegree"));
        s.setSchool(jsonStaff.getString("school"));
        s.setHourlyWage(jsonStaff.getDouble("hourlyWage"));
        s.setWorkingHoursPerWeek(jsonStaff.getInt("workingHoursPerWeek"));
        return s;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonStaff = new JSONObject();
        jsonStaff.put("type", type);
        jsonStaff.put("id", id);
        jsonStaff.put("firstName", firstName);
        jsonStaff.put("lastName", lastName);
        jsonStaff.put("phoneNumber", phoneNumber);
        jsonStaff.put("dateOfBirth", dateOfBirth.toString());
        jsonStaff.put("startWorkDate", startWorkDate.toString());
        jsonStaff.put("highestDegree", highestDegree);
        jsonStaff.put("school", school);
        jsonStaff.put("hourlyWage", hourlyWage);
        jsonStaff.put("workingHoursPerWeek", workingHoursPerWeek);
        return jsonStaff;
    }

    // Lists all types of staff who work in the clinic
    public enum StaffType {
        DENTIST, HYGIENIST, ASSISTANT, CLERK;
    }
}
