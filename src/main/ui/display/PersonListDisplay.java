package ui.display;

import ui.Main;
import ui.Window;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a window in GUI to show list of persons
public abstract class PersonListDisplay extends Window {
    protected JPanel personListPanel;

    // EFFECTS: constructs a new Person List display window
    public PersonListDisplay(Main main, String title) {
        super(main, "{ " + title + " }");
        personListPanel = new JPanel();
    }

    @Override
    protected void setupGraphics() {
        personListPanel.setLayout(new BoxLayout(personListPanel, BoxLayout.Y_AXIS));
        personListPanel.setBorder(new EmptyBorder(10,10,10,10));
        //personListPanel.add(Box.createRigidArea(new Dimension(1,10)));
        setListHeader();
        add(personListPanel, BorderLayout.CENTER);
    }

    // EFFECTS: creates and adds a header at the top of the list
    private void setListHeader() {
        JPanel header = new JPanel();
        header.setMaximumSize(new Dimension(500, 40));
        header.setBackground(Color.GRAY);

        JLabel left = new JLabel("ID");
        left.setFont(new Font(null, Font.BOLD, 20));
        JLabel right = new JLabel("Name");
        right.setFont(new Font(null, Font.BOLD, 20));
        header.add(left);
        header.add(Box.createRigidArea(new Dimension(60, 1)));
        header.add(right);
        personListPanel.add(header);
        personListPanel.add(Box.createRigidArea(new Dimension(1,10)));
    }

    // EFFECTS: updates list of persons to be shown in the list
    public abstract void updateList();
}
