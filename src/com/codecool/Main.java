package com.codecool;

import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Game game = new Game();
		Deck deck = new Deck();
		Printer printer = new Printer();
		game.setPrinter(printer);
		deck.fillDeck(Card.values());
		deck.shuffleCards();
		game.gatherPlayers();
		game.deal(deck);

		game.round();
		game.round();
		game.round();

		/*
		 *TODO befejezni a play()-t elfogyottEMárvalakinekAKártyája() metódussal
		 *felugró képek
		 *esetleg más cardokkal is kompatibilis legyen
		 *hibakezelések
		 *fun
		 *amúgyszépmunka
		 *todo-k megcsinálása 
		 */
	}
}
