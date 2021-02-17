package model.clinic;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Room class
public class RoomTest {

    private Room room;

    @BeforeEach
    public void setup() {
        room = new Room("test room");
    }

    @Test
    public void testSetLocation() {
        room.setLocation("main floor, north side");
        assertTrue("main floor, north side".equals(room.getLocation()));
    }

    @Test
    public void testSetName() {
        room.setName("room101");
        assertTrue("room101".equals(room.getName()));
    }

    @Test
    public void testAddMultipleEquipment() {
        Equipment eq1 = new Equipment(Equipment.EquipmentType.DENTAL_CHAIR);
        Equipment eq2 = new Equipment(Equipment.EquipmentType.MOUTH_MIRROR);
        Equipment eq3 = new Equipment(Equipment.EquipmentType.SCALAR);
        room.addEquipment(eq1);
        room.addEquipment(eq2);
        room.addEquipment(eq3);
        assertEquals(3, room.getEquipmentList().size());
        assertEquals(Equipment.EquipmentType.DENTAL_CHAIR, room.getEquipment(0).getType());
    }

    @Test
    public void testRemoveMultipleEquipment() {
        Equipment eq1 = new Equipment(Equipment.EquipmentType.DENTAL_CHAIR);
        Equipment eq2 = new Equipment(Equipment.EquipmentType.MOUTH_MIRROR);
        Equipment eq3 = new Equipment(Equipment.EquipmentType.SCALAR);
        room.addEquipment(eq1);
        room.addEquipment(eq2);
        room.addEquipment(eq3);
        assertEquals(3, room.getEquipmentList().size());
        assertEquals(Equipment.EquipmentType.DENTAL_CHAIR, room.getEquipment(0).getType());
        room.removeEquipment(0);
        assertEquals(2, room.getEquipmentList().size());
        assertNotEquals(Equipment.EquipmentType.DENTAL_CHAIR, room.getEquipment(0).getType());
        room.removeEquipment(0);
        room.removeEquipment(0);
        assertEquals(0, room.getEquipmentList().size());
    }

    @Test
    public void testSetCleanMultiple() {
        assertTrue(room.isClean());
        room.setClean();
        assertTrue(room.isClean());
    }

    @Test
    public void testSetNotCleanMultiple() {
        assertTrue(room.isClean());
        room.setNotClean();
        assertFalse(room.isClean());
        room.setNotClean();
        assertFalse(room.isClean());
    }
}
