package com.codecool;

public class Main {

    public static void main(String[] args) {

        Deck deck = new Deck();
        deck.listFiller();
        System.out.println(deck.cards.get(1).getSpeed());
        
//		Game game = new Game();
//		game.getplayers();
//		game.getcards(Cards);
//		game.deal();
//		game.play();

    }
}
