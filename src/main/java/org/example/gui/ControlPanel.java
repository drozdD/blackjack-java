package org.example.gui;

import org.example.model.Game;

import javax.swing.*;
import java.awt.*;

public class ControlPanel extends JPanel{
    private JLabel balanceLabel;
    private JLabel stakeLabel;
    private JPanel buttonPanel;
    private JPanel buttonPanel2;
    private JPanel moneyPanel;
    private JPanel endPanel;
    private Game game;
    private Runnable updateUI;

    public ControlPanel(Game game, Runnable updateUI) {
        this.game = game;
        this.updateUI = updateUI;
        setLayout(new FlowLayout(FlowLayout.CENTER, 5, 10));
        setBackground(new Color(20, 100, 20)); // ciemnozielone tło
        generateMoneyPanel();
        generateBetButtonsPanel();
        generatePlayButtonsPanel();

        updateUIForState();
    }

    public void generateEndPanel(){
        JLabel endText = null;
        if(game.getPlayer().getState() == "win") endText = new JLabel("You won!");
        else if(game.getPlayer().getState() == "lose") endText = new JLabel("Haha - you lost!");
        else if(game.getPlayer().getState() == "push") endText = new JLabel("It's a push!");
        else endText = new JLabel("Something went wrong!");
        endText.setForeground(Color.WHITE);
        endText.setFont(new Font("Monospaced", Font.BOLD, 35));
        endText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel balance = new JLabel("Current balance: " + game.getPlayer().getMoney());
        balance.setForeground(Color.WHITE);
        balance.setFont(new Font("Monospaced", Font.BOLD, 25));
        balance.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel left = new JPanel(new GridLayout(2, 1));
        left.setOpaque(false);
        left.add(endText);
        left.add(balance);

        ImageIcon playAgain = new ImageIcon(getClass().getResource("/buttons/play_again.png"));
        Image iconPlayAgain = playAgain.getImage().getScaledInstance(222, 60, Image.SCALE_SMOOTH);
        JButton playAgainButton = new JButton(new ImageIcon(iconPlayAgain));

        playAgainButton.setBorderPainted(false);    // bez obramowania
        playAgainButton.setContentAreaFilled(false); // bez tła
        playAgainButton.setFocusPainted(false);

        playAgainButton.addActionListener(e -> {
            game.resetGame();
            game.getPlayer().setState("betting");
            updateUIForState();
            updateUI.run();
        });

        JPanel right = new JPanel();
        right.setOpaque(false);
        right.add(playAgainButton);

        endPanel = new JPanel(new GridLayout(1, 2));
        endPanel.setOpaque(false);
        endPanel.add(left);
        endPanel.add(right);
    }

    public void generatePlayButtonsPanel(){
        buttonPanel2 = new JPanel();
        buttonPanel2.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 1));
        buttonPanel2.setOpaque(false);

        ImageIcon iconHit = new ImageIcon(getClass().getResource("/buttons/hit.png"));
        JButton hitButton = new JButton(iconHit);

        hitButton.setBorderPainted(false);    // bez obramowania
        hitButton.setContentAreaFilled(false); // bez tła
        hitButton.setFocusPainted(false);

        ImageIcon iconStand = new ImageIcon(getClass().getResource("/buttons/stand.png"));
        JButton standButton = new JButton(iconStand);

        standButton.setBorderPainted(false);    // bez obramowania
        standButton.setContentAreaFilled(false); // bez tła
        standButton.setFocusPainted(false);

        ImageIcon iconDouble = new ImageIcon(getClass().getResource("/buttons/double.png"));
        JButton doubleButton = new JButton(iconDouble);

        doubleButton.setBorderPainted(false);    // bez obramowania
        doubleButton.setContentAreaFilled(false); // bez tła
        doubleButton.setFocusPainted(false);

        hitButton.addActionListener(e -> {
            game.hitBtnEvent();
            updateUIForState();
            updateUI.run();
        });

        doubleButton.addActionListener(e->{
            if(game.getPlayer().getMoney() < game.getPlayer().getCurrentStake()) return;
            game.doubleBtnEvent();
            updateUIForState();
            updateUI.run();
        });

        standButton.addActionListener(e->{
            game.standBtnEvent(updateUI);
        });

        buttonPanel2.add(hitButton);
        buttonPanel2.add(standButton);
        buttonPanel2.add(doubleButton);
    }

    public void generateBetButtonsPanel(){
        buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 1));
        buttonPanel.setOpaque(false);

        ImageIcon bet1Icon = new ImageIcon(getClass().getResource("/chips/1.png"));
        Image img1 = bet1Icon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet1Button = new JButton(new ImageIcon(img1));

        ImageIcon bet5 = new ImageIcon(getClass().getResource("/chips/5.png"));
        Image img5 = bet5.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet5Button = new JButton(new ImageIcon(img5));

        ImageIcon bet10 = new ImageIcon(getClass().getResource("/chips/10.png"));
        Image img10 = bet10.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet10Button = new JButton(new ImageIcon(img10));

        ImageIcon bet25 = new ImageIcon(getClass().getResource("/chips/25.png"));
        Image img25 = bet25.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet25Button = new JButton(new ImageIcon(img25));

        ImageIcon bet50 = new ImageIcon(getClass().getResource("/chips/50.png"));
        Image img50 = bet50.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet50Button = new JButton(new ImageIcon(img50));

        ImageIcon bet100 = new ImageIcon(getClass().getResource("/chips/100.png"));
        Image img100 = bet100.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton bet100Button = new JButton(new ImageIcon(img100));

        ImageIcon resetIcon = new ImageIcon(getClass().getResource("/chips/reset.png"));
        Image reset = resetIcon.getImage().getScaledInstance(64, 64, Image.SCALE_SMOOTH);
        JButton resetButton = new JButton(new ImageIcon(reset));

        ImageIcon betIcon = new ImageIcon(getClass().getResource("/buttons/place.png"));
        JButton betButton = new JButton(betIcon);

        bet1Button.setBorderPainted(false);    // bez obramowania
        bet1Button.setContentAreaFilled(false); // bez tła
        bet1Button.setFocusPainted(false);

        bet5Button.setBorderPainted(false);    // bez obramowania
        bet5Button.setContentAreaFilled(false); // bez tła
        bet5Button.setFocusPainted(false);

        bet10Button.setBorderPainted(false);    // bez obramowania
        bet10Button.setContentAreaFilled(false); // bez tła
        bet10Button.setFocusPainted(false);

        bet25Button.setBorderPainted(false);    // bez obramowania
        bet25Button.setContentAreaFilled(false); // bez tła
        bet25Button.setFocusPainted(false);

        bet50Button.setBorderPainted(false);    // bez obramowania
        bet50Button.setContentAreaFilled(false); // bez tła
        bet50Button.setFocusPainted(false);

        bet100Button.setBorderPainted(false);    // bez obramowania
        bet100Button.setContentAreaFilled(false); // bez tła
        bet100Button.setFocusPainted(false);

        resetButton.setBorderPainted(false);    // bez obramowania
        resetButton.setContentAreaFilled(false); // bez tła
        resetButton.setFocusPainted(false);

        betButton.setBorderPainted(false);    // bez obramowania
        betButton.setContentAreaFilled(false); // bez tła
        betButton.setFocusPainted(false);

        bet1Button.addActionListener(e -> {
            game.getPlayer().bet(1);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        bet5Button.addActionListener(e -> {
            game.getPlayer().bet(5);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        bet10Button.addActionListener(e -> {
            game.getPlayer().bet(10);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        bet25Button.addActionListener(e -> {
            game.getPlayer().bet(25);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        bet50Button.addActionListener(e -> {
            game.getPlayer().bet(50);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        bet100Button.addActionListener(e -> {
            game.getPlayer().bet(100);
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        resetButton.addActionListener(e -> {
            game.getPlayer().resetBet();
            balanceLabel.setText("Balance: " + game.getPlayer().getMoney());
            stakeLabel.setText("Current Stake: " + game.getPlayer().getCurrentStake());
            revalidate();
            repaint();
        });

        betButton.addActionListener(e -> {
            if(game.getPlayer().getCurrentStake() < 0) return;
            game.startRound();
            updateUIForState();
            updateUI.run();
        });

        JPanel chipsPanel = new JPanel(new GridLayout(2, 1));
        chipsPanel.setOpaque(false);

        JPanel chipsPanel1 = new JPanel();
        chipsPanel1.setLayout(new FlowLayout(FlowLayout.CENTER));
        chipsPanel1.setOpaque(false);

        JPanel chipsPanel2 = new JPanel();
        chipsPanel2.setLayout(new FlowLayout(FlowLayout.CENTER));
        chipsPanel2.setOpaque(false);

        chipsPanel1.add(bet1Button);
        chipsPanel1.add(bet5Button);
        chipsPanel1.add(bet10Button);
        chipsPanel2.add(bet25Button);
        chipsPanel2.add(bet50Button);
        chipsPanel2.add(bet100Button);
        chipsPanel.add(chipsPanel1);
        chipsPanel.add(chipsPanel2);
        buttonPanel.add(chipsPanel);

        JPanel placePanel = new JPanel(new GridLayout(2, 1));
        placePanel.setOpaque(false);
        placePanel.add(betButton);
        placePanel.add(resetButton);

        buttonPanel.add(placePanel);
    }

    public void generateMoneyPanel(){
        balanceLabel = new JLabel("Balance: " + game.getPlayer().getMoney());
        balanceLabel.setForeground(Color.WHITE);
        balanceLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        balanceLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        stakeLabel = new JLabel("Current Stake: " + game.getPlayer().getCurrentStake());
        stakeLabel.setForeground(Color.WHITE);
        stakeLabel.setFont(new Font("Monospaced", Font.BOLD, 25));
        stakeLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        moneyPanel = new JPanel(new GridLayout(2, 1));
        moneyPanel.setOpaque(false);
        moneyPanel.add(balanceLabel);
        moneyPanel.add(stakeLabel);
    }

    public void updateUIForState() {
        removeAll();

        switch (game.getPlayer().getState()) {
            case "betting":
                this.add(moneyPanel, BorderLayout.WEST);
                this.add(buttonPanel, BorderLayout.EAST);
                break;

            case "playing":
                this.add(moneyPanel, BorderLayout.WEST);
                this.add(buttonPanel2, BorderLayout.EAST);
                break;

            case "win", "lose", "push":
                generateEndPanel();
                this.add(endPanel, BorderLayout.CENTER);
                break;
        }

        revalidate();
        repaint();
    }

}
