package org.example.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private String state;
    private int money;
    private int currentStake;
    private List<Card> playerCards;

    public Player() {
        this.state = "betting";
        this.money = 500;
        this.currentStake = 0;
        this.playerCards = new ArrayList<>();
    }

    public void addCard(Card card){
        playerCards.add(card);
    }

    public List<Card> getPlayerCards() {
        return playerCards;
    }

    public void bet(int x){
        if(money < x) return;
        money = money - x;
        currentStake = currentStake + x;
    }

    public void resetBet(){
        int x = currentStake;
        currentStake = 0;
        money = money + x;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getCurrentStake() {
        return currentStake;
    }

    public void setCurrentStake(int currentStake) {
        this.currentStake = currentStake;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }
}
