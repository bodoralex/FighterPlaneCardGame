package com.codecool;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Player implements PlayCapable {

    Scanner scanner = new Scanner(System.in);
    Printer printer = new Printer();
    Queue<Card> hand = new LinkedList<>();
    String name;


    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public void checkMyCard() {
        Card topCard = hand.peek();
        printer.print(topCard);
        printer.print("Choose the attribute you want to use");
    }

    @Override
    public Card draw() {
        return hand.remove();
    }

    @Override
    public Integer choose() {
        checkMyCard();
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
