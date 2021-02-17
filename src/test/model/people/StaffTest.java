package model.people;

import model.treatment.Date;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Staff class
public class StaffTest {

    private Staff s;
    private Set<String> staffIDList;

    @BeforeEach
    public void setup() {
        staffIDList = new HashSet<String>();
        staffIDList.add("dn-222");
        staffIDList.add("hy-999");
        staffIDList.add("as-123");
        staffIDList.add("cl-987");
        s = new Staff (Staff.StaffType.DENTIST, "dn-111", staffIDList);
    }

    @Test
    public void testConstructorSameID() {
        Staff newStaff = new Staff(Staff.StaffType.DENTIST, "dn-222", staffIDList);
        assertFalse("dn-222".equals(newStaff.getID()));
    }

    @Test
    public void testSetPhoneNumber() {
        s.setPhoneNumber("+1 777 7777");
        assertTrue("+1 777 7777".equals(s.getPhoneNumber()));
    }

    @Test
    public void testSetStartWorkDate() {
        Date d = new Date(2222,12,22);
        s.setStartWorkDate(d);
        assertEquals(2222, s.getStartWorkDate().getYear());
        assertEquals(12, s.getStartWorkDate().getMonth());
        assertEquals(22, s.getStartWorkDate().getDay());
    }

    @Test
    public void testSetHourlyWage() {
        assertEquals(0, s.getHourlyWage());
        s.setHourlyWage(25.00);
        assertEquals(25.00, s.getHourlyWage());
    }

    @Test
    public void testSetWorkingHoursPerWeek() {
        assertEquals(0, s.getWorkingHoursPerWeek());
        s.setWorkingHoursPerWeek(40);
        assertEquals(40, s.getWorkingHoursPerWeek());
    }

    @Test
    public void testSetHighestDegree() {
        assertTrue("".equals(s.getHighestDegree()));
        s.setHighestDegree("Diploma");
        assertTrue("Diploma".equals(s.getHighestDegree()));
    }

    @Test
    public void testSetUniversity() {
        assertTrue("".equals(s.getSchool()));
        s.setSchool("UBC");
        assertTrue("UBC".equals(s.getSchool()));
    }

    @Test
    public void testGetType() {
        assertEquals(Staff.StaffType.DENTIST, s.getType());
    }

    @Test
    public void testGetStaffInitialCharacter() {
        Staff s1 = new Staff(Staff.StaffType.HYGIENIST, staffIDList);
        assertTrue("hy-".equals(s1.getID().substring(0, 3)));
        Staff s2 = new Staff(Staff.StaffType.CLERK, staffIDList);
        assertTrue("cl-".equals(s2.getID().substring(0, 3)));
        Staff s3 = new Staff(Staff.StaffType.ASSISTANT, staffIDList);
        assertTrue("as-".equals(s3.getID().substring(0, 3)));
        Staff s4 = new Staff(Staff.StaffType.DENTIST, staffIDList);
        assertTrue("dn-".equals(s4.getID().substring(0, 3)));
    }
}
