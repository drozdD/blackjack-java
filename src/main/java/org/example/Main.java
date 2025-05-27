package org.example;

import org.example.gui.GameWindow;
import org.example.model.*;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Dealer dealer = new Dealer();
        Game game = new Game(player, dealer);
        SwingUtilities.invokeLater(() -> new GameWindow(game));

    }
}