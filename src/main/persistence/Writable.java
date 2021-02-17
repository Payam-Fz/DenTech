package persistence;

import org.json.JSONObject;

// Represents the ability of object to be written in JSON object format
//      This interface is modeled based on similar interface in "JsonSerializationDemo" accessed from:
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public interface Writable {

    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
