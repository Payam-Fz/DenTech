package ui;

import ui.menu.DataMenu;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

// Represents the loading screen and setup of the app
public class LoadingScreen extends Window {

    private JLabel middleText;
    private BufferedImage image;

    // EFFECTS: constructs a new loading window
    public LoadingScreen(Main main) {
        super(main, "");
        DataMenu.loadData(main, footer);
        addMouseListener(new ContinueMouseListener());

        setupGraphics();
        addBottomSection();
    }

    @Override
    protected void setupGraphics() {
        middleText = new JLabel();
        middleText.setText("\n\nWelcome to the Clinic");
        middleText.setFont(new Font("serif",Font.ITALIC,40));
        middleText.setHorizontalAlignment(SwingConstants.CENTER);
        try {
            image = ImageIO.read(new File("./data/media/Tooth.png"));
        } catch (IOException ex) {
            footer.setText("Image unavailable.");
        }
        add(middleText, BorderLayout.CENTER);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, this.getWidth() * 4 / 10,60,180,240, this);
    }

    @Override
    protected void addBottomSection() {
        add(footer, BorderLayout.SOUTH);
    }

    // EFFECTS: plays the audio given the path String
    private void playSound(String address) {
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(new File(address).getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (Exception ex) {
            footer.setText("Sound unavailable.");
        }
    }

    // MODIFIES: this
    // EFFECTS: displays the ending text
    private void endGraphics() {
        middleText.setText("Have a healthy day!");
    }

    // Represents listener for continue when clicked on the window
    private class ContinueMouseListener extends MouseAdapter {

        // EFFECTS: Continues from loading page to main menu after click
        public void mouseClicked(MouseEvent e) {
            playSound("./data/media/Soundeffect.wav");
            main.getLayout().show(main.getWindowManager(), "main_menu");
            endGraphics();
            removeMouseListener(LoadingScreen.this.getMouseListeners()[0]);
        }
    }
}
