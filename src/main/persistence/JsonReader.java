package persistence;

import model.clinic.Clinic;
import model.clinic.Room;
import model.people.Patient;
import model.people.Staff;
import org.json.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads clinic data from JSON data stored in file
//      This class is modeled based on similar class in "JsonSerializationDemo" accessed from:
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReader {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads clinic from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Clinic read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonClinic = new JSONObject(jsonData);
        return parseClinic(jsonClinic);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses clinic from JSON object and returns it
    private Clinic parseClinic(JSONObject jsonClinic) {
        Clinic cl = new Clinic();
        addPatients(cl, jsonClinic);
        addStaff(cl, jsonClinic);
        addRoom(cl,jsonClinic);
        return cl;
    }

    // MODIFIES: cl
    // EFFECTS: adds all patients from JSON object to clinic
    private void addPatients(Clinic cl, JSONObject jsonClinic) {
        JSONArray jsonPatientList = jsonClinic.getJSONArray("patientList");
        for (Object obj : jsonPatientList) {
            JSONObject jsonPatient = (JSONObject) obj;
            Patient p = Patient.parsePatient(jsonPatient);
            cl.addPatient(p);
        }
    }

    // MODIFIES: cl
    // EFFECTS: adds all staff from JSON object to clinic
    private void addStaff(Clinic cl, JSONObject jsonClinic) {
        JSONArray jsonPatientList = jsonClinic.getJSONArray("staffList");
        for (Object obj : jsonPatientList) {
            JSONObject jsonStaff = (JSONObject) obj;
            Staff s = Staff.parseStaff(jsonStaff);
            cl.addStaff(s);
        }
    }

    // MODIFIES: cl
    // EFFECTS: adds all rooms from JSON object to clinic
    private void addRoom(Clinic cl, JSONObject jsonClinic) {
        JSONArray jsonPatientList = jsonClinic.getJSONArray("roomList");
        for (Object obj : jsonPatientList) {
            JSONObject jsonRoom = (JSONObject) obj;
            Room r = Room.parseRoom(jsonRoom);
            cl.addRoom(r);
        }
    }

}
