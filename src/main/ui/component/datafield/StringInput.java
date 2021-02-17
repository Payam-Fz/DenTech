package ui.component.datafield;

import javax.swing.*;

// Represents a GUI panel for inputting values of type double
public class StringInput extends DataPanel {

    protected JTextField input;

    // EFFECTS: constructs a new string input panel
    public StringInput(String labelName) {
        super(labelName);

        input = new JTextField();
        add(input);
    }

    // EFFECTS: returns the inputted string
    public String getInput() {
        return input.getText();
    }

    // MODIFIES: this
    // EFFECTS: sets the value to be shown in panel
    public void setInput(String previousValue) {
        input.setText(previousValue);
    }
}
