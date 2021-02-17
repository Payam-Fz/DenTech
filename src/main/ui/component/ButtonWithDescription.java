package ui.component;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

// Represents a panel that has a button and a description to the right of it
public class ButtonWithDescription extends JPanel {

    private JButton button;
    private JLabel text;

    // EFFECTS: constructs a new Button with description panel
    public ButtonWithDescription(String buttonName, String description) {
        super();
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setAlignmentX(LEFT_ALIGNMENT);
        setBorder(new EmptyBorder(10,0,10,0));
        button = new JButton(buttonName);
        button.setMargin(new Insets(20,40,20,40));
        button.setBackground(Color.LIGHT_GRAY);
        button.setFont(new Font(null,0,14));
        text = new JLabel(description);
        text.setFont(new Font(null,0,20));

        add(button);
        add(Box.createRigidArea(new Dimension(40, 1)));
        add(text);
    }

    // EFFECTS: adds a listener to the button
    public void addButtonListener(ActionListener l) {
        button.addActionListener(l);
    }
}
