package ui.menu;

import model.clinic.Clinic;
import persistence.JsonReader;
import persistence.JsonWriter;
import ui.Main;
import ui.component.ButtonWithDescription;
import ui.component.Footer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

// Represents a menu window for application data persistence
public class DataMenu extends Menu {

    // EFFECTS: constructs a new data menu window
    public DataMenu(Main main) {
        super(main,"Data");

        setupGraphics();
        addBottomSection("main_menu");
    }

    @Override
    protected void addOptions(JPanel optionPanel) {
        ButtonWithDescription load = new ButtonWithDescription("Load", "Reload previously saved data");
        load.addButtonListener(new LoadButtonActionListener());

        ButtonWithDescription save = new ButtonWithDescription("Save", "Save current data");
        save.addButtonListener(new SaveButtonActionListener());

        ButtonWithDescription clear = new ButtonWithDescription("Clear", "Clear all data (!)");
        clear.addButtonListener(new ClearButtonActionListener());

        optionPanel.add(load);
        optionPanel.add(save);
        optionPanel.add(clear);
    }

    // MODIFIES: this, mainFrame.getClinic()
    // EFFECTS: loads data of clinic from file and updates the footer
    public static void loadData(Main main, Footer footer) {
        try {
            main.setClinic(new JsonReader(main.SAVE_FILE_LOCATION).read());
            footer.setText("Data loaded successfully.");
        } catch (IOException e) {
            footer.setText("Unable to load previous data: " + e);
        }
    }

    // MODIFIES: this, mainFrame.getClinic()
    // EFFECTS: deletes data of clinic from current state of the app
    private void clearData() {
        main.setClinic(new Clinic());
        footer.setText("Data cleared successfully.");
    }

    // EFFECTS: saves data of clinic to file
    public static void saveData(Main main, Footer footer) {
        try {
            JsonWriter writer = new JsonWriter(main.SAVE_FILE_LOCATION);
            writer.open();
            writer.write(main.getClinic());
            writer.close();
            footer.setText("Data saved successfully.");
        } catch (IOException e) {
            footer.setText("Unable to save data: " + e);
        }
    }

    // Represents a listener for load button
    private class LoadButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            loadData(main, footer);
        }
    }

    // Represents a listener for save button
    private class SaveButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            saveData(main, footer);
        }
    }

    // Represents a listener for clear button
    private class ClearButtonActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            clearData();
        }
    }
}
