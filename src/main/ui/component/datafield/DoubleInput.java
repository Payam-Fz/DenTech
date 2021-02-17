package ui.component.datafield;

import javax.swing.*;

// Represents a GUI panel for inputting values of type double
public class DoubleInput extends DataPanel {

    protected JTextField input;

    // EFFECTS: constructs a new double input panel
    public DoubleInput(String labelName) {
        super(labelName);

        input = new JTextField();
        add(input);
    }

    // EFFECTS: returns the inputted double value
    public double getInput() {
        return Double.parseDouble(input.getText());
    }

    // MODIFIES: this
    // EFFECTS: sets the value to be shown in panel
    public void setInput(double previousValue) {
        input.setText(Double.toString(previousValue));
    }
}
