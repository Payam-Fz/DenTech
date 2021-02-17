package ui.component.datafield;

import javax.swing.*;

// Represents a GUI panel for inputting values of type integer
public class IntegerInput extends DataPanel {

    protected JTextField input;

    // EFFECTS: constructs a new integer input panel
    public IntegerInput(String labelName) {
        super(labelName);

        input = new JTextField();
        add(input);
    }

    // EFFECTS: returns the inputted integer value
    public int getInput() {
        return Integer.parseInt(input.getText());
    }

    // MODIFIES: this
    // EFFECTS: sets the value to be shown in panel
    public void setInput(int previousValue) {
        input.setText(Integer.toString(previousValue));
    }
}
