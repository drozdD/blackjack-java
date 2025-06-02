package org.example.gui;

import org.example.model.Card;
import org.example.model.Player;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class CardPanel extends JPanel {
    private List<Card> playerCards;
    private List<Card> dealerCards;
    private Player player;
    private final Image backCard;
    private static final int CARD_WIDTH = 168;
    private static final int CARD_HEIGHT = 228;

    public CardPanel(List<Card> playerCards, List<Card> dealerCards, Player player) {
        this.playerCards = playerCards;
        this.dealerCards = dealerCards;
        this.player = player;
        setPreferredSize(new Dimension(1000, 800));

        Image img = new ImageIcon(getClass().getResource("/cards/back.png")).getImage();
        this.backCard = img.getScaledInstance(CARD_WIDTH, CARD_HEIGHT, Image.SCALE_DEFAULT);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int panelWidth = getWidth();
        int dealerTotalWidth = dealerCards.size() * (CARD_WIDTH/2) + (dealerCards.size() - 1);
        int startXDealer = (panelWidth - dealerTotalWidth) / 2;

        // Rysuj karty dealera u góry
        for (int i = 0; i < dealerCards.size(); i++) {
            Image img = null;
            if(i == dealerCards.size() - 1 && Objects.equals(player.getState(), "playing")) img = backCard;
            else img = dealerCards.get(i).getImage();
            int x = startXDealer + i * (CARD_WIDTH/2) - 20;
            g.drawImage(img, x, 20, null);  // (x, y) + odstęp
        }

        int playerTotalWidth = playerCards.size() * (CARD_WIDTH/2) + (playerCards.size() - 1);
        int startXPlayer = (panelWidth - playerTotalWidth) / 2;

        // Rysuj karty gracza na dole
        for (int i = 0; i < playerCards.size(); i++) {
            Image img = playerCards.get(i).getImage();
            int x = startXPlayer + i * (CARD_WIDTH/2) - 20;
            g.drawImage(img, x, 420, null); // niżej y = 250
        }

        g.setFont(new Font("Monospaced", Font.BOLD, 18));
        g.setColor(Color.WHITE);
        g.drawString("Player points: " + String.valueOf(player.getPoints()), panelWidth / 2 - 70, 400);

        repaint();
    }
}
