package org.example.gui;

import javax.swing.*;
import java.awt.*;

public class BackgroundPanel extends JPanel {
    private Image backgroundImage;

    public BackgroundPanel(String imagePath) {
        backgroundImage = new ImageIcon(getClass().getResource(imagePath)).getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Rysowanie obrazka na całej powierzchni panelu
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }
}
