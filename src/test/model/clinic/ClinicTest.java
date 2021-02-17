package model.clinic;

import model.people.Patient;
import model.people.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Clinic class
public class ClinicTest {

    private Clinic clinic;

    @BeforeEach
    public void setup() {
        clinic = new Clinic();
    }

    @Test
    public void testAddRoomMultipleRooms() {
        clinic.addRoom(new Room("room 101"));
        clinic.addRoom(new Room ("X-Ray room"));
        clinic.addRoom(new Room("waiting room"));
        List<Room> list = clinic.getRoomsList();
        assertEquals(3, list.size());
        assertEquals("room 101", list.get(0).getName());
        assertEquals(3, list.size());
        assertEquals("waiting room", list.get(2).getName());
    }

    @Test
    public void testRemoveRoomMultipleRooms() {
        clinic.addRoom(new Room("room 101"));
        clinic.addRoom(new Room ("X-Ray room"));
        clinic.addRoom(new Room("waiting room"));
        List<Room> list = clinic.getRoomsList();
        assertEquals(3, list.size());
        assertTrue("room 101".equals(list.get(0).getName()));
        clinic.removeRoom(0);
        assertEquals(2, list.size());
        assertFalse("room 101".equals(list.get(0).getName()));
        clinic.removeRoom(0);
        assertEquals(1, list.size());
        assertTrue("waiting room".equals(list.get(0).getName()));
    }

    @Test
    public void testGetRoom(){
        clinic.addRoom(new Room("room 101"));
        clinic.addRoom(new Room ("X-Ray room"));
        clinic.addRoom(new Room("waiting room"));
        assertTrue("room 101".equals(clinic.getRoom(0).getName()));
        assertTrue("waiting room".equals(clinic.getRoom(2).getName()));
    }

    @Test
    public void testAddStaffMultipleStaff() {
        clinic.addStaff(new Staff(Staff.StaffType.DENTIST,"dn-225", clinic.getStaffIDList()));
        for (int i=0; i<398; i++){
            clinic.addStaff(new Staff(Staff.StaffType.ASSISTANT, clinic.getStaffIDList()));
        }
        clinic.addStaff(new Staff(Staff.StaffType.HYGIENIST, "hy-999", clinic.getStaffIDList()));
        Set<String> staffIDList = clinic.getStaffIDList();
        assertEquals(400, staffIDList.size());
        assertTrue(staffIDList.contains("dn-225"));
        assertTrue(staffIDList.contains("hy-999"));
    }

    @Test
    public void testRemoveStaffMultipleStaff() {
        clinic.addStaff(new Staff(Staff.StaffType.DENTIST,"dn-225", clinic.getStaffIDList()));
        clinic.addStaff(new Staff(Staff.StaffType.HYGIENIST, "hy-999", clinic.getStaffIDList()));
        assertEquals(2, clinic.getStaffIDList().size());
        assertTrue(clinic.getStaffIDList().contains("dn-225"));
        assertTrue(clinic.getStaffIDList().contains("hy-999"));
        clinic.removeStaff("dn-225");
        assertEquals(1, clinic.getStaffIDList().size());
        assertFalse(clinic.getStaffIDList().contains("dn-225"));
        assertTrue(clinic.getStaffIDList().contains("hy-999"));
        clinic.removeStaff("hy-999");
        assertEquals(0, clinic.getStaffIDList().size());
        assertFalse(clinic.getStaffIDList().contains("dn-225"));
        assertFalse(clinic.getStaffIDList().contains("hy-999"));
    }

    @Test
    public void testGetStaff(){
        Staff staffEntered = new Staff(Staff.StaffType.ASSISTANT,"as-123", clinic.getStaffIDList());
        clinic.addStaff(staffEntered);
        Staff staffReceived = clinic.getStaff("as-123");
        assertEquals(1, clinic.getStaffIDList().size());
        assertTrue(staffReceived.getID().equals("as-123"));
    }

    @Test
    public void testAddPatientMultiplePatients() {
        clinic.addPatient(new Patient("p-987654", clinic.getPatientsIDList()));
        for (int i=0; i<103; i++){
            clinic.addPatient(new Patient(clinic.getPatientsIDList()));
        }
        clinic.addPatient(new Patient("p-123456", clinic.getPatientsIDList()));
        Set<String> patientIDList = clinic.getPatientsIDList();
        assertEquals(105, patientIDList.size());
        assertTrue(patientIDList.contains("p-987654"));
        assertTrue(patientIDList.contains("p-123456"));
    }

    @Test
    public void testRemovePatientMultiplePatients() {
        clinic.addPatient(new Patient("p-987654", clinic.getPatientsIDList()));
        clinic.addPatient(new Patient("p-123456", clinic.getPatientsIDList()));
        assertEquals(2, clinic.getPatientsIDList().size());
        assertTrue(clinic.getPatientsIDList().contains("p-987654"));
        assertTrue(clinic.getPatientsIDList().contains("p-123456"));
        clinic.removePatient("p-987654");
        assertEquals(1, clinic.getPatientsIDList().size());
        assertFalse(clinic.getPatientsIDList().contains("p-987654"));
        assertTrue(clinic.getPatientsIDList().contains("p-123456"));
        clinic.removePatient("p-123456");
        assertEquals(0, clinic.getPatientsIDList().size());
        assertFalse(clinic.getPatientsIDList().contains("p-987654"));
        assertFalse(clinic.getPatientsIDList().contains("p-123456"));
    }

    @Test
    public void testGetPatient(){
        Patient patientEntered = new Patient("p-987654", clinic.getPatientsIDList());
        clinic.addPatient(patientEntered);
        Patient patientReceived = clinic.getPatient("p-987654");
        assertEquals(1, clinic.getPatientsIDList().size());
        assertTrue(patientReceived.getID().equals("p-987654"));
    }
}
