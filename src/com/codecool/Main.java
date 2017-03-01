package com.codecool;

import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);
	
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
		scanner.close();
	}
}
