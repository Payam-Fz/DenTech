package ui;

import model.clinic.Clinic;
import ui.component.Footer;
import ui.display.PatientListDisplay;
import ui.display.SelectedStaffDisplay;
import ui.display.StaffListDisplay;
import ui.form.StaffDataEntryForm;
import ui.menu.DataMenu;
import ui.menu.MainMenu;
import ui.menu.StaffMenu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedHashMap;
import java.util.Map;

// Represents the main window of the app
public class Main extends JFrame {

    public static final String SAVE_FILE_LOCATION = "./data/clinic.json";
    public static final Dimension MINIMUM_WINDOW_SIZE = new Dimension(1024,800);

    private CardLayout layout;
    private Clinic clinic;
    private JPanel windowManager;
    private Map<String, Window> windows;

    // EFFECTS: constructs an instance of the app, sets the main frame of GUI and sets up all windows
    public Main() {
        super("DenTech");
        clinic = new Clinic();
        windowManager = new JPanel();
        windows = new LinkedHashMap<>();
        setupFrame();

        layout = new CardLayout();
        windowManager.setLayout(layout);
        add(windowManager);

        addWindows();

        layout.show(windowManager,"loading");
        setVisible(true);
    }

    // MODIFIES: this
    // EFFECTS: sets the specifications of main GUI frame
    private void setupFrame() {
        setMinimumSize(MINIMUM_WINDOW_SIZE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        addWindowListener(new WindowCloseListener());
        pack();
    }

    // MODIFIES: this
    // EFFECTS: constructs and adds all required windows for the app
    private void addWindows() {
        windows.put("loading", new LoadingScreen(this));
        windows.put("main_menu", new MainMenu(this));

        //windows.put("patient_menu", new PatientMenu(this));
        windows.put("patientList_display", new PatientListDisplay(this));

        windows.put("staff_menu", new StaffMenu(this));
        windows.put("staffList_display", new StaffListDisplay(this));
        windows.put("selectedStaff_display", new SelectedStaffDisplay(this));
        windows.put("staffDataEntry_form", new StaffDataEntryForm(this));

        windows.put("data_menu", new DataMenu(this));

        addWindowsToWindowManager();
    }

    // MODIFIES: this
    // EFFECTS: adds all application windows to a list for later reference
    private void addWindowsToWindowManager() {
        for (Map.Entry<String, Window> window : windows.entrySet()) {
            windowManager.add(window.getValue(), window.getKey());
        }
    }

    @Override
    public CardLayout getLayout() {
        return this.layout;
    }


    public JPanel getWindowManager() {
        return this.windowManager;
    }

    public Window getWindow(String name) {
        return windows.get(name);
    }

    public Clinic getClinic() {
        return clinic;
    }

    // MODIFIES: this
    // EFFECTS: sets the clinic object of the app
    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    // EFFECTS: starts the application
    public static void main(String[] args) {
        new Main();
    }

    // Represents a listener for when the window is being closed
    private class WindowCloseListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent windowEvent) {
            DataMenu.saveData(Main.this, new Footer());
        }
    }
}
