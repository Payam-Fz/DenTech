package model.clinic;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

// Represents a room in the clinic
public class Room implements Writable {

    String name;
    String location;
    List<Equipment> equipmentList;
    boolean clean;

    // EFFECTS: constructs a new empty Room given its name. The room is clean and address is empty.
    public Room(String name) {
        this.name = name;
        location = "";
        equipmentList = new ArrayList<>();
        clean = true;
    }

    // EFFECTS: returns the name of this room
    public String getName() {
        return name;
    }

    // EFFECTS: returns the location of this room
    public String getLocation() {
        return location;
    }

    // MODIFIES: this
    // EFFECTS: changes the name of this room
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: changes the location of this room
    public void setLocation(String location) {
        this.location = location;
    }

    // REQUIRES: the same equipment doesn't already exist in equipment list
    // MODIFIES: this
    // EFFECTS: adds the given equipment to the equipment list
    public void addEquipment(Equipment equipment) {
        equipmentList.add(equipment);
    }

    // REQUIRES: the same equipment already exists in equipment list
    // MODIFIES: this
    // EFFECTS: removes the equipment in the given index from the equipment list
    public void removeEquipment(int index) {
        equipmentList.remove(index);
    }

    // REQUIRES: the same equipment already exists in equipment list
    // MODIFIES: this
    // EFFECTS: returns the equipment in the given index from the equipment list
    public Equipment getEquipment(int index) {
        return equipmentList.get(index);
    }

    // EFFECTS: returns the equipment list
    public List<Equipment> getEquipmentList() {
        return equipmentList;
    }

    // EFFECTS: returns the cleanness status of this room
    public boolean isClean() {
        return clean;
    }

    // EFFECTS: sets this room as dirty
    public void setNotClean() {
        clean = false;
    }

    // EFFECTS: sets this room as clean
    public void setClean() {
        clean = true;
    }

    // EFFECTS: parses a room from JSON object and returns it
    public static Room parseRoom(JSONObject jsonRoom) {
        Room r = new Room(jsonRoom.getString("name"));
        r.setLocation(jsonRoom.getString("location"));
        boolean clean = jsonRoom.getBoolean("clean");
        if (clean) {
            r.setClean();
        } else {
            r.setNotClean();
        }
        JSONArray jsonEquipmentList = jsonRoom.getJSONArray("equipmentList");
        for (Object obj : jsonEquipmentList) {
            JSONObject jsonEquipment = (JSONObject) obj;
            Equipment eq = Equipment.parseEquipment(jsonEquipment);
            r.addEquipment(eq);
        }
        return r;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonRoom = new JSONObject();
        jsonRoom.put("name", name);
        jsonRoom.put("location", location);
        jsonRoom.put("clean", clean);
        JSONArray jsonEquipmentList = new JSONArray();
        for (Equipment eq : equipmentList) {
            jsonEquipmentList.put(eq.toJson());
        }
        jsonRoom.put("equipmentList", jsonEquipmentList);
        return jsonRoom;
    }
}
