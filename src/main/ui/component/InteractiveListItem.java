package ui.component;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.MouseListener;

// Represents a list item with 2 fields that could be clicked on
public class InteractiveListItem extends JPanel {

    // EFFECTS: constructs a new interactive list item given the left and right text and mouse click listener
    public InteractiveListItem(String leftField, String rightField, MouseListener listener) {
        super();
        setMaximumSize(new Dimension(500, 40));
        setBorder(new LineBorder(Color.BLACK));

        JLabel left = new JLabel(leftField);
        JLabel right = new JLabel(rightField);
        Font f = new Font(null,0,20);
        left.setFont(f);
        right.setFont(f);
        add(left);
        add(Box.createRigidArea(new Dimension(40, 1)));
        add(right);

        addMouseListener(listener);
    }
}
