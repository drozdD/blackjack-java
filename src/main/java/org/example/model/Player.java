package org.example.model;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Player {
    private String state;
    private int money;
    private int currentStake;
    private List<Card> playerCards;
    private static final String MONEY_FILE = "player_money.txt";
    private int points;

    public Player() {
        this.state = "betting";
        this.money = loadMoneyFromFile();
        this.currentStake = 0;
        this.playerCards = new ArrayList<>();
    }

    private int loadMoneyFromFile() {
        File file = new File(MONEY_FILE);
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                return Integer.parseInt(reader.readLine());
            } catch (IOException | NumberFormatException e) {
                System.out.println("Błąd przy wczytywaniu pieniędzy, ustawiam domyślnie 500");
            }
        } else {
            saveMoneyToFile(500); // utwórz plik z domyślną wartością
        }
        return 500;
    }

    public void saveMoneyToFile(int amount) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MONEY_FILE))) {
            writer.write(String.valueOf(amount));
        } catch (IOException e) {
            System.out.println("Błąd przy zapisywaniu pieniędzy");
        }
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

    public void resetCards(){
        this.playerCards = new ArrayList<>();
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
