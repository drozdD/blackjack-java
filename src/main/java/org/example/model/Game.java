package org.example.model;

import java.util.List;

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
        player.saveMoneyToFile(player.getMoney());
        player.setState("playing");
        deck.shuffle();
        player.addCard(deck.drawCard());
        player.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());
        dealer.addCard(deck.drawCard());

        if(checkBlackjack(player.getPlayerCards()) && checkBlackjack(dealer.getDealerCards())) push();
        else if (checkBlackjack(player.getPlayerCards())) playerWin();
        else if (checkBlackjack(dealer.getDealerCards())) playerLose();

        player.setPoints(countPoints(player.getPlayerCards()));
    }

    public boolean checkBlackjack(List<Card> cards){
        int sum = 0;
        for(int i = 0;i < cards.size(); i++){
            sum = sum + cards.get(i).getPoints();
        }

        return sum == 21;
    }

    public void resetGame(){
        player.resetCards();
        player.setPoints(0);
        dealer.resetCards();
        deck = new Deck();
    }

    public void hitBtnEvent(){
        deck.shuffle();
        player.addCard(deck.drawCard());
        player.setPoints(countPoints(player.getPlayerCards()));
        if(player.getPoints() > 21) playerLose();
    }

    public void doubleBtnEvent(){
        player.setMoney(player.getMoney() - player.getCurrentStake());
        player.setCurrentStake(player.getCurrentStake() * 2);
        hitBtnEvent();
    }

    public void standBtnEvent(Runnable updateFun) {
        player.setState("dealerTurn");
        deck.shuffle();
        int playerPoints = countPoints(player.getPlayerCards());
        player.setPoints(playerPoints);
        int[] dealerPoints = {countPoints(dealer.getDealerCards())};

        while (dealerPoints[0] < 17) {
            Card newCard = deck.drawCard();
            dealer.addCard(newCard);
            dealerPoints[0] = countPoints(dealer.getDealerCards());
            updateFun.run(); // odśwież panel z kartami
        }

        if (dealerPoints[0] > 21) playerWin();
        else if (dealerPoints[0] > playerPoints) playerLose();
        else if (playerPoints > dealerPoints[0]) playerWin();
        else push();

        updateFun.run(); // finalne odświeżenie

    }


    public int countPoints(List<Card> cards){
        int sum = 0;
        for(int i = 0; i < cards.size(); i++){
            sum = sum + cards.get(i).getPoints();
        }

        if(sum > 21){
            sum = 0;
            for(int i = 0; i < cards.size(); i++){
                if(cards.get(i).getRank() == "A") sum += 1;
                else sum = sum + cards.get(i).getPoints();
            }
        }

        return sum;
    }

    public void playerWin(){
        player.setMoney(player.getMoney() + player.getCurrentStake() * 2);
        player.saveMoneyToFile(player.getMoney());
        player.setCurrentStake(0);
        player.setState("win");
    }

    public void playerLose(){
        player.setCurrentStake(0);
        player.saveMoneyToFile(player.getMoney());
        player.setState("lose");
    }

    public void push(){
        player.setMoney(player.getMoney() + player.getCurrentStake());
        player.saveMoneyToFile(player.getMoney());
        player.setCurrentStake(0);
        player.setState("push");
    }

    public Player getPlayer() {
        return player;
    }

    public Dealer getDealer() {
        return dealer;
    }
}
