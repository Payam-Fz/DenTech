package ui.component.datafield;

import model.treatment.Date;
import org.jdatepicker.JDatePicker;
import org.jdatepicker.impl.*;

import java.awt.*;
import java.util.Properties;


// Represents a GUI panel for date selection
// This class uses jdatepicker-1.3.4 library for date selection
public class DateInput extends DataPanel {
    private JDatePicker input;

    // EFFECTS: constructs a new date input panel
    public DateInput(String labelName) {
        super(labelName);

        UtilDateModel model = new UtilDateModel();
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        input = new JDatePickerImpl(datePanel, new DateComponentFormatter());
        add((Component) input);
    }

    // MODIFIES: this
    // EFFECTS: sets the date to be shown in the panel
    public void setInput(Date previousValue) {
        UtilDateModel model = new UtilDateModel();
        model.setDate(previousValue.getYear(), previousValue.getMonth(), previousValue.getDay());
        model.setSelected(true);
        JDatePanelImpl datePanel = new JDatePanelImpl(model, new Properties());
        input = new JDatePickerImpl(datePanel, new DateComponentFormatter());
    }

    // EFFECTS: returns the inputted date
    public Date getInput() {
        Date day = new Date();
        day.setYear(input.getModel().getYear());
        day.setMonth(input.getModel().getMonth());
        day.setDay(input.getModel().getDay());
        return day;
    }
}
