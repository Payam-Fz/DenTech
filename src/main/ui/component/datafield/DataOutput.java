package ui.component.datafield;

import javax.swing.*;
import java.awt.*;

// Represents a panel in GUI that shows a piece of data
public class DataOutput extends DataPanel {

    // EFFETCS: constructs new data output field
    public DataOutput(String title, String data) {
        super(title);
        JLabel dataField = new JLabel(data);
        dataField.setFont(new Font(null,0,20));
        add(dataField);
    }
}
