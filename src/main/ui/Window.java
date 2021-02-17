package ui;

import ui.component.BackButton;
import ui.component.Footer;
import ui.component.WindowTitle;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents a window in UI
public abstract class Window extends JPanel {

    protected Main main;
    public Footer footer;
    protected BackButton backButton;
    protected WindowTitle title;

    // EFFECTS: constructs a new window
    public Window(Main main, String title) {
        this.main = main;
        this.title = new WindowTitle(title);
        footer = new Footer();
        backButton = new BackButton(main);
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(20,20,20,20));
        add(this.title, BorderLayout.NORTH);
    }

    // MODIFIES: this
    // EFFECTS: sets up the graphics of this menu
    protected abstract void setupGraphics();

    // MODIFIES: this
    // EFFECTS: adds the footer and back button to the window
    protected void addBottomSection() {
        JPanel bottom = new JPanel();
        bottom.setLayout(new BorderLayout());
        bottom.setBorder(new EmptyBorder(10,0,0,30));
        footer.setBorder(new EmptyBorder(20,0,0,0));
        bottom.add(footer, BorderLayout.WEST);
        bottom.add(backButton, BorderLayout.EAST);
        add(bottom, BorderLayout.SOUTH);
    }

    // REQUIRES: backDestination window already exists in the main.windows
    // MODIFIES: this
    // EFFECTS: adds the footer and back button to the window with
    //          back button having specific destination
    protected void addBottomSection(String backDestination) {
        backButton = new BackButton(main, backDestination);
        addBottomSection();
    }

    // MODIFIES: this
    // EFFECTS: sets the title of window
    protected void setTitle(String newTitle) {
        title.setText(newTitle);
    }
}
