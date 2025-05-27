package org.example.gui;

import org.example.model.Game;
import org.example.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class GameWindow extends JFrame {

    private Game game;

    public GameWindow(Game game) {
        this.game = game;
        setTitle("Blackjack");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        generateControlPanel();

        setVisible(true);
    }

    public void generateControlPanel(){
        BackgroundPanel contentPanel = new BackgroundPanel("/bg.png");
        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        ControlPanel controlPanel = new ControlPanel(game, this::updateUI);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void generateCardPanel(){
        CardPanel cardPanel = new CardPanel(game.getPlayer().getPlayerCards(), game.getDealer().getDealerCards());
        cardPanel.setOpaque(false);
        add(cardPanel, BorderLayout.CENTER);
    }

    public void updateUI(){
        switch (game.getPlayer().getState()) {
            case "betting":
                generateControlPanel();
                break;

            case "playing":
                generateControlPanel();
                generateCardPanel();
                break;

            case "finished":

                break;
        }

        revalidate();
        repaint();
    }
}
