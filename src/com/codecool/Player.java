package com.codecool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Player implements PlayCapable {

    Scanner scanner = new Scanner(System.in);
    Printer printer = new Printer();
    Queue<Card> hand = new LinkedList<>();

    @Override
    public int attack() {
        Card topCard = hand.peek();
        printer.print(topCard);
        printer.print("Choose the attribute you want to use");
        return getPlayerChoice();
    }

    @Override
    public Card draw() {
        return hand.remove();
    }

    @Override
    public void setHand(Queue<Card> cards) {

    }

    @Override
    public Queue<Card> getHand() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    public int getPlayerChoice() {
        boolean goodInput = false;
        while (!goodInput) {
            int playerChoice = scanner.nextInt();
            if (playerChoice == 1 || playerChoice == 2 || playerChoice == 3 || playerChoice == 4) {
                return playerChoice;
            }
        }
        return 0;
    }

}
