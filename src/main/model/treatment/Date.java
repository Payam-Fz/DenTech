package model.treatment;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

// Represents a date
public class Date {
    private int year;
    private int month;
    private int day;

    // REQUIRES: year, month and day to be valid and in format of: yyyy-mm-dd
    // EFFECTS: constructs a new date given the year, month and day
    public Date(int y, int m, int d) {
        year = y;
        month = m;
        day = d;
    }

    // EFFECT: constructs a new date for today
    public Date() {
        Date today = Date.getToday();
        year = today.getYear();
        month = today.getMonth();
        day = today.getDay();
    }

    // EFFECTS: returns the year
    public int getYear() {
        return year;
    }

    // EFFECTS: returns the month
    public int getMonth() {
        return month;
    }

    // EFFECTS: returns the day
    public int getDay() {
        return day;
    }

    // REQUIRES: a positive integer between 2000 and 2500
    // MODIFIES: this
    // EFFECTS: changes the year of this date to the passed year
    public void setYear(int year) {
        this.year = year;
    }

    // REQUIRES: a positive integer between 1 and 12 inclusively
    // MODIFIES: this
    // EFFECTS: changes the month of this date to the passed month
    public void setMonth(int month) {
        this.month = month;
    }

    // REQUIRES: a positive integer that properly represents a day in corresponding month
    // MODIFIES: this
    // EFFECTS: changes the day of this date to the passed day
    public void setDay(int day) {
        this.day = day;
    }

    // EFFECTS: returns the next day after this
    //          doesn't handle leap years
    public Date getNextDate() {
        int nextDay = day;
        int nextMonth = month;
        int nextYear = year;
        if (day == 31) {
            if (month == 12) {
                nextYear++;
                nextDay = 1;
                nextMonth = 1;
            } else {
                nextDay = 1;
                nextMonth++;
            }
        } else if (day == 30 && getListOfShortMonths().contains(month)) {
            nextDay = 1;
            nextMonth++;
        } else if (day == 28 && month == 2) {
            nextDay = 1;
            nextMonth++;
        } else {
            nextDay++;
        }
        return new Date(nextYear, nextMonth, nextDay);
    }

    private List<Integer> getListOfShortMonths() {
        ArrayList<Integer> shortMonths = new ArrayList<Integer>();
        shortMonths.add(4);
        shortMonths.add(6);
        shortMonths.add(9);
        shortMonths.add(11);
        return shortMonths;
    }

    // EFFECTS: checks if this date is equal to the passed date
    public boolean equals(Date other) {
        if (other.getYear() == getYear() && other.getMonth()
                == getMonth() && other.getDay() == getDay()) {
            return true;
        }
        return false;
    }

    //EFFECTS: returns a string representation of this date in format of: yyyy-mm-dd
    @Override
    public String toString() {
        return "" + year + "-" + month + "-" + day;
    }

    // EFFECTS: returns the date of today in local timezone
    public static Date getToday() {
        String todayString = LocalDateTime.now().toString().substring(0,10);
        String[] dateArray = todayString.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        Date today = new Date(year,month,day);
        return today;
    }

    // REQUIRES: the passed string has a valid date in the format yyyy-mm-dd
    // EFFECTS: parses a date from string in the form yyyy-mm-dd and returns it
    public static Date parseDate(String str) {
        String[] dateArray = str.split("-");
        int year = Integer.parseInt(dateArray[0]);
        int month = Integer.parseInt(dateArray[1]);
        int day = Integer.parseInt(dateArray[2]);
        Date date = new Date(year, month, day);
        return date;
    }
}
