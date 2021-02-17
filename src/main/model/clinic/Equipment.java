package model.clinic;

import org.json.JSONObject;
import persistence.Writable;

// Represents a dental equipment
public class Equipment implements Writable {

    private EquipmentType type;
    private String name;
    private String note;
    private boolean working;
    private int count;

    // EFFECTS: constructs a new equipment given its type
    //          by default, sets name the same as type, true working condition, empty note and 1 count
    public Equipment(EquipmentType type) {
        this.type = type;
        name = type.name();
        working = true;
        note = "";
        count = 1;
    }

    // EFFECTS: returns the name of this piece of equipment
    public String getName() {
        return name;
    }

    // EFFECTS: returns the count of this type of equipment
    public int getCount() {
        return count;
    }

    // EFFECTS: returns the type of this equipment
    public EquipmentType getType() {
        return type;
    }

    // EFFECTS: returns the working status of equipment
    public boolean isWorking() {
        return working;
    }

    // EFFECTS: returns the note for this equipment
    public String getNote() {
        return note;
    }

    // MODIFIES: this
    // EFFECTS: changes the name of this equipment
    public void setName(String name) {
        this.name = name;
    }

    // MODIFIES: this
    // EFFECTS: changes the note/comment of this equipment
    public void setNote(String note) {
        this.note = note;
    }

    // MODIFIES: this
    // EFFECTS: changes the count of this equipment
    public void setCount(int count) {
        this.count = count;
    }

    // MODIFIES: this
    // EFFECTS: sets the working status to the passed boolean value
    public void setWorkingStatus(boolean status) {
        working = status;
    }

    // EFFECTS: parses an equipment from JSON object and returns it
    public static Equipment parseEquipment(JSONObject jsonRoom) {
        EquipmentType type = EquipmentType.valueOf(jsonRoom.getString("type"));
        Equipment r = new Equipment(type);
        r.setName(jsonRoom.getString("name"));
        r.setNote(jsonRoom.getString("note"));
        r.setWorkingStatus(jsonRoom.getBoolean("working"));
        r.setCount(jsonRoom.getInt("count"));
        return r;
    }

    @Override
    public JSONObject toJson() {
        JSONObject jsonEquipment = new JSONObject();
        jsonEquipment.put("name", name);
        jsonEquipment.put("type", type);
        jsonEquipment.put("note", note);
        jsonEquipment.put("working", working);
        jsonEquipment.put("count", count);
        return jsonEquipment;
    }

    // Lists all possible dental equipment types used in a clinic
    public enum EquipmentType {
        SALIVA_EJECTOR10, MOUTH_MIRROR, DRILL, XRAY_MACHINE,
        SYRINGE, DENTAL_CHAIR, SCALAR, SICKLE_PROBE;

    }
}
