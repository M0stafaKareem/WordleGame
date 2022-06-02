package com.mycompany.gui;

import java.awt.Font;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;
import javax.swing.*;

public class PlayingPanel {

    private Font font = new Font("Silkscreen", 0, 25);
    private JTextField[][] chances = new JTextField[5][5];
    private JTextField getEnter = new JTextField();
    private String typedWord = "", targetWord;
    private int counter = 0;
    private final JPanel playingPanel = new JPanel();
    private JFrame gameField;
    public static Database2 DB;
    public static String playerName;

    public PlayingPanel(JFrame gameField, int x, int y) {
        this.gameField = gameField;
        configureTextPanel(gameField, x, y);
        configureTextFields();
    }

    public class LSThread extends Thread {

        @Override
        public void run() {
            updateScore();
            Lose ls = new Lose(gameField);
        }
    }

    public class WNThread extends Thread {

        @Override
        public void run() {
            updateScore();
            Win wn = new Win(gameField);
        }
    }

    public void updateScore() {
        int score;
        String s1 = DB.getScore(playerName);

        score = Integer.parseInt(s1);
        score += ((5 - counter) * 20);
        DB.UpdateUser(playerName, Integer.toString(score));

    }

    private void configureTextFields() {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                chances[i][j] = new JTextField();
                chances[i][j].setBounds(50 * j, 50 * i, 50, 50);
                chances[i][j].setFont(font);
                chances[i][j].setOpaque(true);
                chances[i][j].setEditable(false);
                chances[i][j].setText("");
                chances[i][j].setBorder(BorderFactory.createEmptyBorder());
                chances[i][j].setBackground(playingPanel.getBackground());
                chances[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                chances[i][j].setVisible(true);
                chances[i][j].addKeyListener(specialKeys);
                if (j == 4) {
                    chances[i][j].addKeyListener(textField4);
                } else {
                    chances[i][j].addKeyListener(textField0_3);
                }
                playingPanel.add(chances[i][j]);
            }
        }
        getEnter.addKeyListener(pressEnter);
        getEnter.setBounds(250, 250, 0, 0);
        playingPanel.add(getEnter);

    }

    private void configureTextPanel(JFrame gameField, int x, int y) {
        playingPanel.setBounds(x, y, 250, 250);
        playingPanel.setLayout(null);
        playingPanel.setBackground(LetterBlock.COLOR_UNSET);
        playingPanel.setVisible(true);
        gameField.add(playingPanel);
    }

    public void setFont(Font font) {
        this.font = font;
    }

    public void startPalying() throws FileNotFoundException {
        counter = 0;
        getTargetWord();
        enableTextWords(0, 0);
        chances[0][0].grabFocus();
    }

    private boolean validateWord() throws FileNotFoundException {
        File file = new File("WordList.txt");
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            if (typedWord.toLowerCase().equals(sc.nextLine())) {
                return true;
            }
        }
        return false;
    }

    private boolean compareWord() {
        if (counter == 5) {
            return false;
        }
        if (typedWord.toLowerCase().equals(targetWord)) {
            for (int i = 0; i < 5; i++) {
                chances[counter][i].setBackground(new java.awt.Color(31, 31, 31));
                chances[counter][i].setForeground(new java.awt.Color(196, 207, 161));
            }
            WNThread Wn = new WNThread();
            Wn.start();
            return true;
        } else {
            int[] coloringMap = new int[5]; // 0 >> not found ,, 1 >> found in place ,, 2 >> found in wrong place
            String temp = targetWord;
            for (int i = 0; i < 5; i++) {
                coloringMap[i] = 0;
                if (targetWord.charAt(i) == typedWord.charAt(i)) {
                    temp = temp.replaceFirst(String.valueOf(typedWord.charAt(i)), " ");
                    coloringMap[i] = 1;
                } else if (temp.indexOf(typedWord.charAt(i)) != -1) {
                    temp = temp.replaceFirst(String.valueOf(typedWord.charAt(i)), " ");
                    coloringMap[i] = 2;
                }
            }

            for (int i = 0; i < 5; i++) {
                switch (coloringMap[i]) {
                    case 0:
                        chances[counter][i].setBackground(new java.awt.Color(139, 149, 109));
                        chances[counter][i].setForeground(new java.awt.Color(196, 207, 161));
                        break;
                    case 1:
                        chances[counter][i].setBackground(new java.awt.Color(31, 31, 31));
                        chances[counter][i].setForeground(new java.awt.Color(196, 207, 161));
                        break;

                    case 2:
                        chances[counter][i].setBackground(new java.awt.Color(77, 83, 60));
                        chances[counter][i].setForeground(new java.awt.Color(196, 207, 161));
                        break;
                    default:
                        throw new AssertionError();
                }
            }
            counter++;
        }
        return false;
    }

    private void getTargetWord() throws FileNotFoundException {
        File file = new File("WordList.txt");
        Scanner sc = new Scanner(file);
        Random rand = new Random();
        int count = 0, random = rand.nextInt(12948);
        while (sc.hasNextLine()) {
            count++;
            sc.nextLine();
            if (count == random) {
                targetWord = sc.nextLine();
                System.out.println(targetWord);
                break;
            }
        }
    }

    private void getPlayersWord() {
        typedWord = "";
        for (int i = 0; i < 5; i++) {
            typedWord += chances[counter][i].getText();
        }
    }

    private void disableTextWords(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = 0; j < 5; j++) {
                chances[i][j].setEditable(false);
            }
        }
    }

    private void enableTextWords(int firstIndex, int lastIndex) {
        for (int i = firstIndex; i <= lastIndex; i++) {
            for (int j = 0; j < 5; j++) {
                chances[i][j].setEditable(true);
            }
        }
    }

    private final KeyListener textField0_3 = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            if (!Character.isLetter(e.getKeyChar())) {
                e.consume();
            } else {
                textField.setText("");
                textField.transferFocus();
            }
        }
    };

    private final KeyListener textField4 = new KeyAdapter() {
        @Override
        public void keyTyped(KeyEvent e) {
            JTextField textField = (JTextField) e.getSource();
            textField.setCursor(null);
            if (!Character.isLetter(e.getKeyChar())) {
                e.consume();
            } else {
                textField.setText("");
                getEnter.grabFocus();
            }
        }
    };

    private KeyListener pressEnter = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_ENTER && counter < 5) {
                getPlayersWord();
                try {
                    if (!validateWord()) {
                        for (int i = 0; i < 5; i++) {
                            chances[counter][i].setText("");
                        }
                        chances[counter][0].grabFocus();
                    } else {
                        disableTextWords(counter, counter);
                        if (!compareWord() && counter < 5) {
                            chances[counter][0].grabFocus();
                            enableTextWords(counter, counter);
                        } else if (counter == 5) {
                            LSThread lose = new LSThread();
                            lose.start();
                        }
                    }

                } catch (FileNotFoundException ex) {
                }
            }
        }
    };

    private final KeyListener specialKeys = new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getExtendedKeyCode()) {
                case KeyEvent.VK_ESCAPE:
                    gameField.dispose();
                    Main_page mp = new Main_page();
                    mp.setVisible(true);
                    break;

                case KeyEvent.VK_ENTER:
                    pressEnter.keyPressed(e);
                    break;

                default:
            }
        }
    };
}
