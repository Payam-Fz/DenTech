package ui.menu;

import ui.Main;
import ui.component.MenuNavigationButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

// Represents the main menu window
public class MainMenu extends Menu {

    // EFFECTS: constructs a new main menu window given the main object
    public MainMenu(Main main) {
        super(main, "Main");
        backButton.setText("Quit");
        backButton.setBackground(Color.RED);
        backButton.removeActionListener(backButton.getActionListeners()[0]);
        backButton.addActionListener(new QuitButtonListener());

        setupGraphics();
        addBottomSection();
    }

    @Override
    protected void addOptions(JPanel optionPanel) {
        //optionPanel.add(new MenuNavigationButton(main, "patient_menu", "Patient", "Manage patients"));
        optionPanel.add(new MenuNavigationButton(main, "staff_menu", "Staff", "Manage staff"));
        optionPanel.add(new MenuNavigationButton(main, "data_menu", "Data", "Manage application data"));
        MenuNavigationButton patientMenuButton =
                new MenuNavigationButton(main, "", "Patient", "Manage Patients (* Not Graphical)");
        patientMenuButton.addButtonListener(new PatientMenuListener());
        optionPanel.add(patientMenuButton);
    }

    // Represents a listener for quit button
    private class QuitButtonListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            main.getLayout().previous(main.getWindowManager());
            DataMenu.saveData(main, main.getWindow("loading").footer);
        }
    }

    private class PatientMenuListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            new PatientMenu(new Scanner(System.in), main.getClinic());
        }
    }

}
