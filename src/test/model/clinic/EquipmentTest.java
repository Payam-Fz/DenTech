package model.clinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Equipment class
public class EquipmentTest {

    private Equipment tool;

    @BeforeEach
    public void setup() {
        tool = new Equipment(Equipment.EquipmentType.SYRINGE);
    }

    @Test
    public void testSetName() {
        assertTrue("SYRINGE".equals(tool.getName()));
        tool.setName("Big syringe");
        assertTrue("Big syringe".equals(tool.getName()));
    }

    @Test
    public void testSetCount() {
        assertEquals(1, tool.getCount());
        tool.setCount(25);
        assertEquals(25, tool.getCount());
    }

    @Test
    public void testSetNote() {
        tool.setNote("this tool needs replacement!");
        assertTrue("this tool needs replacement!".equals(tool.getNote()));
    }

    @Test
    public void testChangeWorkingStatusTrueToFalse() {
        assertTrue(tool.isWorking());
        tool.setWorkingStatus(false);
        assertFalse(tool.isWorking());
    }

    @Test
    public void testChangeWorkingStatusFalseToTrue() {
        tool.setWorkingStatus(false);
        assertFalse(tool.isWorking());
        tool.setWorkingStatus(true);
        assertTrue(tool.isWorking());
    }
}
