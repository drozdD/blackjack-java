package org.example.model;

public class Game {
    private Dealer dealer;
    private Player player;
    private Deck deck;

    public Game(Player player, Dealer dealer){
        this.player = player;
        this.dealer = dealer;
        this.deck = new Deck();
    }

    public void startRound(){
        player.setState("playing");
        deck.shuffle();
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
