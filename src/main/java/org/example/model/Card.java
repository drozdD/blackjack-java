package org.example.model;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class Card {
    private final String suit;  // np. "Hearts"
    private final String rank;  // np. "A", "10", "K"
    private final int points;
    private final Image image;

    public Card(String rank, String suit) {
        this.rank = rank;
        this.suit = suit;
        if(canConvertToInt(this.rank)){
            this.points = Integer.parseInt(this.rank);
        }else{
            if(Objects.equals(this.rank, "A")) this.points = 11;
            else this.points = 10;
        }
        Image img = new ImageIcon(getClass().getResource("/cards/" + suit + rank + ".png")).getImage();
        this.image = img.getScaledInstance(168, 228, Image.SCALE_DEFAULT);
    }

    public Image getImage() {
        return image;
    }

    public static boolean canConvertToInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public String getSuit() {
        return suit;
    }

    public String getRank() {
        return rank;
    }

    public int getPoints(){
        return  this.points;
    }
}
