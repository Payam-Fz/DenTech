package model.people;

import model.treatment.Date;
import model.treatment.VisitReport;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Patient class
public class PatientTest {

    private Patient p;
    private Set<String> patientsIDList;

    @BeforeEach
    public void setup() {
        patientsIDList = new HashSet<String>();
        patientsIDList.add("p-222222");
        patientsIDList.add("p-999999");
        patientsIDList.add("p-123456");
        patientsIDList.add("p-987654");
        p = new Patient(patientsIDList);
    }

    @Test
    public void testSetName() {
        p.setFirstName("Michael");
        p.setLastName("Jackson");
        assertTrue("Michael".equals(p.getFirstName()));
        assertTrue("Jackson".equals(p.getLastName()));
    }

    @Test
    public void testSetDateOfBirth() {
        p.setDateOfBirth(new Date(1996,4,4));
        assertEquals(1996, p.getDateOfBirth().getYear());
        assertEquals(4, p.getDateOfBirth().getMonth());
        assertEquals(4, p.getDateOfBirth().getDay());
    }

    @Test
    public void testConstructorIdNotAlreadyExists() {
        Patient q = new Patient("p-777777",patientsIDList);
        assertTrue("p-777777".equals(q.getID()));
    }

    @Test
    public void testConstructorIdAlreadyExists() {
        Patient q = new Patient("p-222222",patientsIDList);
        assertFalse("p-777777".equals(q.getID()));
    }

    @Test
    public void testSetNote() {
        p.setNote("Gum really hurts");
        assertTrue("Gum really hurts".equals(p.getNote()));
    }

    @Test
    public void testAddMultipleVisitReport() {
        Date d = Date.getToday();
        VisitReport vr1 = new VisitReport(d);
        vr1.setFee(200.00);
        VisitReport vr2 = new VisitReport(d.getNextDate());
        VisitReport vr3 = new VisitReport(vr2.getDate().getNextDate());
        p.addVisitReport(vr1);
        p.addVisitReport(vr2);
        p.addVisitReport(vr3);
        assertEquals(3, p.getAllVisitReportDates().size());
        assertEquals(200.00, p.getVisitReport(0).getFee());
        assertEquals(0, p.getVisitReport(1).getFee());
        assertEquals(3, p.getAllVisitReportDates().size());
    }

    @Test
    public void testRemoveMultipleEquipment() {
        Date d = Date.getToday();
        VisitReport vr1 = new VisitReport(d);
        vr1.setFee(200.00);
        VisitReport vr2 = new VisitReport(d.getNextDate());
        VisitReport vr3 = new VisitReport(vr2.getDate().getNextDate());
        p.addVisitReport(vr1);
        p.addVisitReport(vr2);
        p.addVisitReport(vr3);
        assertEquals(3, p.getAllVisitReportDates().size());
        assertEquals(200.00, p.getVisitReport(0).getFee());
        p.removeVisitReport(0);
        assertEquals(2, p.getAllVisitReportDates().size());
        assertEquals(Date.getToday().getNextDate().getDay(), p.getVisitReport(0).getDate().getDay());
        p.removeVisitReport(0);
        p.removeVisitReport(0);
        assertEquals(0, p.getAllVisitReportDates().size());
    }

    @Test
    public void testGetTodayReportExists() {
        Date today = Date.getToday();
        VisitReport vr1 = new VisitReport(today);
        vr1.setFee(200.00);
        VisitReport vr2 = new VisitReport(today.getNextDate());
        VisitReport vr3 = new VisitReport(vr2.getDate().getNextDate());
        p.addVisitReport(vr1);
        p.addVisitReport(vr2);
        p.addVisitReport(vr3);
        assertEquals(200.00, p.getTodayReport().getFee());
    }

    @Test
    public void testGetTodayReportDoesntExist() {
        VisitReport vr2 = new VisitReport(Date.getToday().getNextDate());
        VisitReport vr3 = new VisitReport(vr2.getDate().getNextDate());
        vr2.setFee(200.00);
        p.addVisitReport(vr2);
        p.addVisitReport(vr3);
        assertNull(p.getTodayReport());
    }
}
