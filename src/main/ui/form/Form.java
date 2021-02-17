package ui.form;

import ui.Main;
import ui.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents a form window to be filled in by user
public abstract class Form extends Window {

    private JButton submitButton;
    protected JPanel fieldPanel;

    // EFFECTS: constructs a new form window given the main and title
    public Form(Main main, String title) {
        super(main, "{ " + title + " Form }");
        this.submitButton = new JButton("Submit");
        fieldPanel = new JPanel();
    }

    @Override
    protected void setupGraphics() {
        fieldPanel.setLayout(new BoxLayout(fieldPanel, BoxLayout.Y_AXIS));
        fieldPanel.setBorder(new EmptyBorder(20,100,20,100));
        addFields();
        fieldPanel.add(Box.createRigidArea(new Dimension(1, 20)));
        fieldPanel.add(submitButton);
        add(fieldPanel, BorderLayout.CENTER);
    }

    // MODIFIES: this
    // EFFECTS: add all fields in the form
    protected abstract void addFields();

    // MODIFIES: this
    // EFFECTS: clears all fields in the form
    public abstract void clearFields();

    // MODIFIES: this
    // EFFECTS: adds a listener to submit button
    public void addSubmitButtonListener(ActionListener l) {
        submitButton.addActionListener(l);
    }

}
