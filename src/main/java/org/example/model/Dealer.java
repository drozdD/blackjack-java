package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Dealer {
    private List<Card> dealerCards;

    public Dealer(){
        this.dealerCards = new ArrayList<>();
    }

    public void addCard(Card card){
        dealerCards.add(card);
    }

    public List<Card> getDealerCards() {
        return dealerCards;
    }
}
