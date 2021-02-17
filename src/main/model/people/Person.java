package model.people;

import model.treatment.Date;
import org.json.JSONObject;
import persistence.Writable;

import java.util.Set;

// Represents a person in clinic (staff or patient)
public abstract class Person implements Writable {
    protected String id;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected Date dateOfBirth;

    // EFFECTS: constructs a new person with empty fields except ID, Birthdate is Today
    public Person() {
        this.firstName = "";
        this.lastName = "";
        this.phoneNumber = "";
        this.dateOfBirth = Date.getToday();
    }

    // EFFECTS: returns a random ID that does not already exist in the idList
    protected String generateID(Set<String> idList, String initialCharacter, int digitLength) {
        String id = "";
        int range = (int) (Math.pow(10, digitLength) - Math.pow(10, digitLength - 1));
        int digitPart;
        do {
            id = initialCharacter;
            digitPart = (int) (Math.random() * range + Math.pow(10, digitLength - 1));
            id += digitPart;
        } while (idList.contains(id));
        return id;
    }

    // EFFECTS: returns the person's ID
    public String getID() {
        return id;
    }

    // EFFECTS: returns the person's first name
    public String getFirstName() {
        return firstName;
    }

    // EFFECTS: returns the person's last name
    public String getLastName() {
        return lastName;
    }

    // EFFECTS: returns the person's date of birth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    // EFFECTS: returns the person's phone number
    public String getPhoneNumber() {
        return phoneNumber;
    }

    // MODIFIES: this
    // EFFECTS: changes the first name of this person
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // MODIFIES: this
    // EFFECTS: changes the last name of this person
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // REQUIRES: phoneNumber has no letter or symbol except '+'
    // MODIFIES: this
    // EFFECTS: changes the phone number of this person
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    // MODIFIES: this
    // EFFECTS: changes the date of birth of this person
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

}
