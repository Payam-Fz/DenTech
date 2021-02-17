package model.treatment;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Contains the unit tests for Date class
public class DateTest {
    private Date d1;
    private Date d2;

    @BeforeEach
    public void setup() {
        d1 = new Date(2020,10,12);
    }

    @Test
    public void testDefaultConstructor() {
        d2 = new Date();
        assertEquals(Date.getToday().getDay(), d2.getDay());
        assertEquals(Date.getToday().getMonth(), d2.getMonth());
        assertEquals(Date.getToday().getYear(), d2.getYear());
    }

    @Test
    public void testGetToday() {
        Date today = Date.getToday();
        assertEquals(2020, today.getYear());
    }

    @Test
    public void testGetNextDayRegular() {
        Date d3 = d1.getNextDate();
        assertEquals(13, d3.getDay());
        assertEquals(10, d3.getMonth());
        assertEquals(2020, d3.getYear());
    }

    @Test
    public void testGetNextDayEndOfLongMonth() {
        Date d3 = new Date(2019,3, 31);
        Date nextD3 = d3.getNextDate();
        assertEquals(1, nextD3.getDay());
        assertEquals(4, nextD3.getMonth());
        assertEquals(2019, nextD3.getYear());
    }

    @Test
    public void testGetNextDayEndOfFebruary() {
        Date d3 = new Date(2019,2, 28);
        Date nextD3 = d3.getNextDate();
        assertEquals(1, nextD3.getDay());
        assertEquals(3, nextD3.getMonth());
        assertEquals(2019, nextD3.getYear());
    }

    @Test
    public void testGetNextDayNonFebruary() {
        Date d3 = new Date(2019,3, 28);
        Date nextD3 = d3.getNextDate();
        assertEquals(29, nextD3.getDay());
        assertEquals(3, nextD3.getMonth());
        assertEquals(2019, nextD3.getYear());
    }

    @Test
    public void testGetNextDayEndOfShortMonth() {
        Date d3 = new Date(2019,6, 30);
        Date nextD3 = d3.getNextDate();
        assertEquals(1, nextD3.getDay());
        assertEquals(7, nextD3.getMonth());
        assertEquals(2019, nextD3.getYear());
    }

    @Test
    public void testGetNextDayLongMonth() {
        Date d3 = new Date(2019,5, 30);
        Date nextD3 = d3.getNextDate();
        assertEquals(31, nextD3.getDay());
        assertEquals(5, nextD3.getMonth());
        assertEquals(2019, nextD3.getYear());
    }

    @Test
    public void testGetNextDayEndOfYear() {
        Date d3 = new Date(2019,12, 31);
        Date nextD3 = d3.getNextDate();
        assertEquals(1, nextD3.getDay());
        assertEquals(1, nextD3.getMonth());
        assertEquals(2020, nextD3.getYear());
    }

    @Test
    public void testSetYearMonthDay() {
        assertEquals(2020, d1.getYear());
        assertEquals(10, d1.getMonth());
        assertEquals(12, d1.getDay());
        d1.setYear(1990);
        d1.setMonth(8);
        d1.setDay(20);
        assertEquals(1990, d1.getYear());
        assertEquals(8, d1.getMonth());
        assertEquals(20, d1.getDay());
    }

    @Test
    public void testEqualsWrongYear() {
        Date dTest = new Date(2019,10,12);
        assertFalse(d1.equals(dTest));
    }

    @Test
    public void testEqualsWrongMonth() {
        Date dTest = new Date(2020,1,12);
        assertFalse(d1.equals(dTest));
    }


    @Test
    public void testEqualsWrongDay() {
        Date dTest = new Date(2020,10,2);
        assertFalse(d1.equals(dTest));
    }

    @Test
    public void testToString() {
        assertEquals("2020-10-12", d1.toString());
    }
}
