package org.example.gui;

import org.example.model.Card;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CardPanel extends JPanel {
    private List<Card> playerCards;
    private List<Card> dealerCards;

    public CardPanel(List<Card> playerCards, List<Card> dealerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        setPreferredSize(new Dimension(800, 400));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Rysuj karty dealera u góry
        for (int i = 0; i < dealerCards.size(); i++) {
            Image img = dealerCards.get(i).getImage(); // zakładamy że masz metodę getImage()
            g.drawImage(img, 100 + i * 110, 50, null);  // (x, y) + odstęp
        }

        // Rysuj karty gracza na dole
        for (int i = 0; i < playerCards.size(); i++) {
            Image img = playerCards.get(i).getImage();
            g.drawImage(img, 100 + i * 110, 250, null); // niżej y = 250
        }
        repaint();
    }

    public void updateCards(List<Card> playerCards, List<Card> dealerCards) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        repaint();
    }
}
