package org.example.gui;

import org.example.model.Game;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    private final Game game;
    public CardPanel cardPanel;

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
        contentPanel.setOpaque(false);
        contentPanel.setLayout(new BorderLayout());
        setContentPane(contentPanel);

        ControlPanel controlPanel = new ControlPanel(game, this::updateUI);
        add(controlPanel, BorderLayout.SOUTH);
    }

    public void generateCardPanel(){
        cardPanel = new CardPanel(game.getPlayer().getPlayerCards(), game.getDealer().getDealerCards(), game.getPlayer());
        cardPanel.setOpaque(false);
        add(cardPanel, BorderLayout.CENTER);
        cardPanel.revalidate();
        cardPanel.repaint();
    }

    public void updateUI(){
        switch (game.getPlayer().getState()) {
            case "betting":
                generateControlPanel();
                break;

            case "playing", "win", "push", "lose":
                generateControlPanel();
                generateCardPanel();
                break;
        }

        revalidate();
        repaint();
    }
}
