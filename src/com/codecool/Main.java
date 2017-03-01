package com.codecool;

public class Main {

	public static void main(String[] args) {

		// Deck deck = new Deck();
		// deck.listFiller();
		// System.out.println(deck.cards.get(1).getSpeed());
		//

		Game game = new Game();
		Deck deck = new Deck();
		Printer printer = new Printer();
		game.setPrinter(printer);
		game.getPlayers();
		game.players.forEach(System.out::println);
		
		
//		deck.fillDeck(Card.values());
//		deck.shuffleCards();
//		
//		game.deal(deck);
		
		// game.play();

	}
}
