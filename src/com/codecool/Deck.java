package com.codecool;


import java.util.ArrayList;

/**
 * Created by pata on 2017.02.27..
 */
public class Deck {

    ArrayList<Card> cards = new ArrayList<>();

    public void listFiller() {

        for (Card card : Card.values()) {

            cards.add(card);

        }
    }

}
