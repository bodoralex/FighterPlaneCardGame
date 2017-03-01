package com.codecool;

public class Main {

	public static void main(String[] args) {


		Game game = new Game();
		Deck deck = new Deck();
		Printer printer = new Printer();
		game.setPrinter(printer);
		game.gatherPlayers();
		
		deck.fillDeck(Card.values());
		deck.shuffleCards();
		game.deal(deck);
		game.round();
		
		
		
		//game.play();

	}
}
