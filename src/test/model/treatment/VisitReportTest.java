package model.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for VisitReport class
public class VisitReportTest {

    private VisitReport vr;

    @BeforeEach
    public void setup(){
        vr = new VisitReport(new Date(2020,10,14));
    }

    @Test
    public void testSetFee() {
        assertEquals(0.00, vr.getFee());
        vr.setFee(25.00);
        assertEquals(25.00, vr.getFee());
    }

    @Test
    public void testSetDate () {
        assertEquals(14, vr.getDate().getDay());
        assertEquals(10, vr.getDate().getMonth());
        Date d = new Date(1999, 1,31);
        vr.setDate(d);
        assertEquals(31, vr.getDate().getDay());
        assertEquals(1, vr.getDate().getMonth());
        assertEquals(1999, vr.getDate().getYear());
    }

    @Test
    public void testIncreaseFeeMultiple () {
        assertEquals(0, vr.getFee());
        vr.increaseFee(551.01);
        assertEquals(551.01, vr.getFee());
        vr.increaseFee(24.02);
        assertEquals(575.03, vr.getFee());
    }

    @Test
    public void testDecreaseFeeMultipleRegular() {
        vr.setFee(200.20);
        assertEquals(200.20, vr.getFee());
        vr.decreaseFee(10.00);
        assertEquals(190.20, vr.getFee());
        vr.decreaseFee(90.20);
        assertEquals(100.00, vr.getFee());
    }

    @Test
    public void testDecreaseFeeMoreThanFee() {
        vr.setFee(200.20);
        assertEquals(200.20, vr.getFee());
        vr.decreaseFee(10.00);
        assertEquals(190.20, vr.getFee());
        vr.decreaseFee(200.20);
        assertEquals(0.00, vr.getFee());
        vr.decreaseFee(200.20);
        assertEquals(0.00, vr.getFee());
    }

    @Test
    public void testAddRemoveMedication() {
        List<String> medicationList = vr.getMedicationsList();
        assertEquals(0, medicationList.size());
        vr.addMedication("Lorazepam");
        vr.addMedication("Methanol");
        vr.addMedication("Statin");
        medicationList = vr.getMedicationsList();
        assertEquals(3, medicationList.size());
        assertTrue("Methanol".equals(medicationList.get(1)));
        assertEquals(3, medicationList.size());
        medicationList = vr.getMedicationsList();
        vr.removeMedication(0);
        assertEquals(2, medicationList.size());
        assertTrue("Statin".equals(medicationList.get(1)));
        vr.removeMedication(0);
        vr.removeMedication(0);
        medicationList = vr.getMedicationsList();
        assertEquals(0, medicationList.size());
    }

    @Test
    public void testAddRemoveRequiredProcedure() {
        List<String> requiredList = vr.getRequiredProceduresList();
        assertEquals(0, requiredList.size());
        vr.addRequiredProcedure("Filling");
        vr.addRequiredProcedure("Cleaning");
        vr.addRequiredProcedure("Restoring");
        vr.addRequiredProcedure("x-ray");
        requiredList = vr.getRequiredProceduresList();
        assertEquals(4, requiredList.size());
        assertTrue("Cleaning".equals(requiredList.get(1)));
        requiredList = vr.getRequiredProceduresList();
        assertEquals(4, requiredList.size());
        vr.removeRequiredProcedure(0);
        requiredList = vr.getRequiredProceduresList();
        assertEquals(3, requiredList.size());
        assertTrue("Restoring".equals(requiredList.get(1)));
        vr.removeRequiredProcedure(0);
        vr.removeRequiredProcedure(0);
        vr.removeRequiredProcedure(0);
        requiredList = vr.getRequiredProceduresList();
        assertEquals(0, requiredList.size());
    }

    @Test
    public void testRemoveCompletedProcedure() {
        List<String> completedList = vr.getCompletedProceduresList();
        assertEquals(0, completedList.size());
        vr.addRequiredProcedure("Filling");
        vr.addRequiredProcedure("Cleaning");
        vr.addRequiredProcedure("Restoring");
        vr.addRequiredProcedure("x-ray");
        vr.completeProcedure(3);
        vr.completeProcedure(2);
        vr.completeProcedure(1);
        vr.completeProcedure(0);
        completedList = vr.getCompletedProceduresList();
        assertEquals(4, completedList.size());
        vr.removeCompletedProcedure(0);
        completedList = vr.getCompletedProceduresList();
        assertEquals(3, completedList.size());
        assertTrue("Restoring".equals(completedList.get(0)));
        vr.removeCompletedProcedure(0);
        completedList = vr.getCompletedProceduresList();
        assertEquals(2, completedList.size());
        assertTrue("Cleaning".equals(completedList.get(0)));
        vr.removeCompletedProcedure(0);
        vr.removeCompletedProcedure(0);
        completedList = vr.getCompletedProceduresList();
        assertEquals(0, completedList.size());
    }

    @Test
    public void testCompleteProcedureCompleteAll() {
        assertFalse(vr.isCompleted());
        List<String> completedList = vr.getCompletedProceduresList();
        assertEquals(0, completedList.size());
        vr.addRequiredProcedure("Filling");
        vr.addRequiredProcedure("Cleaning");
        vr.addRequiredProcedure("Restoring");
        vr.completeProcedure(2);
        assertFalse(vr.isCompleted());
        vr.completeProcedure(1);
        assertFalse(vr.isCompleted());
        vr.completeProcedure(0);
        assertEquals(0, vr.getRequiredProceduresList().size());
        assertEquals(3, vr.getCompletedProceduresList().size());
        assertTrue(vr.isCompleted());
    }

    @Test
    public void testCompleteProcedureAlreadyComplete() {
        vr.setCompleted(true);
        assertTrue(vr.isCompleted());
        List<String> completedList = vr.getCompletedProceduresList();
        assertEquals(0, completedList.size());
        vr.addRequiredProcedure("Filling");
        vr.addRequiredProcedure("Cleaning");
        vr.addRequiredProcedure("Restoring");
        vr.completeProcedure(2);
        assertTrue(vr.isCompleted());
        vr.completeProcedure(1);
        assertTrue(vr.isCompleted());
        vr.completeProcedure(0);
        assertEquals(0, vr.getRequiredProceduresList().size());
        assertEquals(3, vr.getCompletedProceduresList().size());
        assertTrue(vr.isCompleted());
    }

    @Test
    public void testSetCompleteMultiple() {
        assertFalse(vr.isCompleted());
        vr.setCompleted(false);
        assertFalse(vr.isCompleted());
        vr.setCompleted(true);
        assertTrue(vr.isCompleted());
        vr.setCompleted(true);
        assertTrue(vr.isCompleted());
        vr.setCompleted(false);
        assertFalse(vr.isCompleted());
    }

}
