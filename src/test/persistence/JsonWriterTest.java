package persistence;

import model.clinic.Clinic;
import model.clinic.Equipment;
import model.clinic.Room;
import model.people.Patient;
import model.people.Staff;
import model.treatment.Date;
import model.treatment.VisitReport;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

// Contains the unit tests for JsonWriter class
//      This class is modeled based on similar class in "JsonSerializationDemo" accessed from:
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonWriterTest {

    private JsonWriter writer;
    private JsonReader reader;

    @Test
    public void testOpenNonExistingFile() {
        try {
            writer = new JsonWriter("./data/notMy\0Clinic:fileName.json");
            writer.open();
            fail("IOException was expected but was not thrown");
        } catch (IOException e) {
            // Exception is expected
        }
    }

    @Test
    public void testWriteEmptyClinic() {
        try {
            Clinic cl = new Clinic();
            writer = new JsonWriter("./data/testWriterEmptyClinic.json");
            writer.open();
            writer.write(cl);
            writer.close();
            reader = new JsonReader("./data/testWriterEmptyClinic.json");
            cl = reader.read();
            assertEquals(0, cl.getRoomsList().size());
            assertEquals(0, cl.getStaffIDList().size());
            assertEquals(0, cl.getPatientsIDList().size());
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception from JsonReader was thrown");
        }
    }

    @Test
    public void testWriteGeneralClinic() {
        try {
            Clinic cl = new Clinic();
            fillGeneralClinic(cl);
            writer = new JsonWriter("./data/testWriterGeneralClinic.json");
            writer.open();
            writer.write(cl);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralClinic.json");
            cl = reader.read();
            assertEquals(2, cl.getPatientsIDList().size());
            assertEquals(1, cl.getStaffIDList().size());
            assertEquals(1, cl.getRoomsList().size());

            checkPatient(cl.getPatient("p-224422"),"Jack","+11111111");
            checkVisitReport(cl.getPatient("p-224422").getVisitReport(0), 400.50, true, 2, 1,1);

            checkPatient(cl.getPatient("p-113311"),"Nicole","+22222222");
            checkVisitReport(cl.getPatient("p-113311").getVisitReport(0), 0, false, 0, 0,0);

            checkStaff(cl.getStaff("as-123"), "Emily", 22.50);

            checkRoom(cl.getRoom(0), "Waiting Room", "First Floor");
            checkEquipment(cl.getRoom(0).getEquipment(0),"Fine Drill", 5);
        } catch (FileNotFoundException e) {
            fail("Exception should not have been thrown");
        } catch (IOException e) {
            fail("Exception from JsonReader was thrown");
        }
    }

    private void fillGeneralClinic(Clinic cl) {
        Patient p1 = new Patient("p-224422", cl.getPatientsIDList());
        p1.setFirstName("Jack");
        p1.setLastName("Ma");
        p1.setPhoneNumber("+11111111");
        fillVisitReport(p1);

        Patient p2 = new Patient("p-113311",cl.getPatientsIDList());
        p2.setFirstName("Nicole");
        p2.setLastName("Kidman");
        p2.setPhoneNumber("+22222222");
        p2.addVisitReport(new VisitReport(Date.getToday()));

        Staff s1 = new Staff(Staff.StaffType.ASSISTANT, "as-123" , cl.getStaffIDList());
        s1.setFirstName("Emily");
        s1.setLastName("Carr");
        s1.setHourlyWage(22.50);

        Room r1 = new Room("Waiting Room");
        r1.setNotClean();
        r1.setLocation("First Floor");
        Equipment fineDrill = new Equipment(Equipment.EquipmentType.DRILL);
        fineDrill.setName("Fine Drill");
        fineDrill.setCount(5);
        r1.addEquipment(fineDrill);

        cl.addRoom(r1);
        cl.addStaff(s1);
        cl.addPatient(p1);
        cl.addPatient(p2);
    }

    private void fillVisitReport(Patient p) {
        VisitReport vr = new VisitReport(Date.getToday());
        vr.setFee(400.50);
        vr.setCompleted(true);
        vr.addMedication("Salbutamol");
        vr.addMedication("Betametazon");
        vr.addRequiredProcedure("Filling");
        vr.addRequiredProcedure("Gum cleaning");
        vr.completeProcedure(1);
        p.addVisitReport(vr);
    }

    private void checkVisitReport(VisitReport vr, double fee, boolean completed, int medSize, int reqSize, int comSize) {
        assertEquals(fee, vr.getFee());
        assertEquals(completed, vr.isCompleted());
        assertEquals(medSize, vr.getMedicationsList().size());
        assertEquals(reqSize, vr.getRequiredProceduresList().size());
        assertEquals(comSize, vr.getCompletedProceduresList().size());
    }

    private void checkPatient(Patient p, String firstName, String phoneNum) {
        assertTrue(firstName.equals(p.getFirstName()));
        assertTrue(phoneNum.equals(p.getPhoneNumber()));
    }

    private void checkStaff(Staff s, String firstName, double hourlyWage) {
        assertTrue(firstName.equals(s.getFirstName()));
        assertEquals(hourlyWage, s.getHourlyWage());
    }

    private void checkRoom(Room r, String name, String location) {
        assertEquals(name, r.getName());
        assertEquals(location, r.getLocation());
    }

    private void checkEquipment(Equipment eq, String name, int count) {
        assertEquals(name, eq.getName());
        assertEquals(count, eq.getCount());
    }
}
