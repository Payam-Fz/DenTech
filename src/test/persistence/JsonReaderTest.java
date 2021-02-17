package persistence;

import model.clinic.Clinic;
import model.people.Patient;
import model.people.Staff;
import model.treatment.VisitReport;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for JsonReader class
//      This class is modeled based on similar class in "JsonSerializationDemo" accessed from:
//      https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
public class JsonReaderTest {

    private JsonReader reader;

    @Test
    public void testReadFromNonExistentFile() {
        reader = new JsonReader("./data/noSuchFile.json");
        try {
            Clinic cl = reader.read();
            fail("IOException was expected but it was not thrown");
        } catch (IOException e) {
            // Exception is expected
        }
    }

    @Test
    public void testReadEmptyClinic() {
        reader = new JsonReader("./data/testReaderEmptyClinic.json");
        try {
            Clinic cl = reader.read();
            assertEquals(0, cl.getRoomsList().size());
            assertEquals(0, cl.getStaffIDList().size());
            assertEquals(0, cl.getPatientsIDList().size());
        } catch (IOException e) {
            fail("Couldn't read from empty file");
        }
    }

    @Test
    public void testReadGeneralClinic() {
        reader = new JsonReader("./data/testReaderGeneralClinic.json");
        try {
            Clinic cl = reader.read();
            assertEquals(1, cl.getRoomsList().size());
            assertEquals(1, cl.getStaffIDList().size());
            assertEquals(2, cl.getPatientsIDList().size());
            checkPatient(cl.getPatient("p-631636"), "Meredith", 1);
            checkVisitReport(cl.getPatient("p-631636").getVisitReport(0),1000,false,3,2,1);
            checkPatient(cl.getPatient("p-673111"), "Owen", 0);
            checkStaff(cl.getStaff("dn-982"), "Payam", 30.00);
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    private void checkVisitReport(VisitReport vr, double fee, boolean completed, int medSize, int reqSize, int comSize) {
        assertEquals(fee, vr.getFee());
        assertEquals(completed, vr.isCompleted());
        assertEquals(medSize, vr.getMedicationsList().size());
        assertEquals(reqSize, vr.getRequiredProceduresList().size());
        assertEquals(comSize, vr.getCompletedProceduresList().size());
    }

    private void checkPatient(Patient p, String firstName, int visitReportNum) {
        assertTrue(firstName.equals(p.getFirstName()));
        assertEquals(visitReportNum, p.getAllVisitReportDates().size());
    }

    private void checkStaff(Staff s, String firstName, double hourlyWage) {
        assertTrue(firstName.equals(s.getFirstName()));
        assertEquals(hourlyWage, s.getHourlyWage());
    }

}
