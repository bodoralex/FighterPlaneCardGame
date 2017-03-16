package com.codecool;

import java.util.Scanner;

public class Main {

	public static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {

		Game game = new Game();
		Printer printer = new Printer();
		game.setPrinter(printer);
		game.setUpServer();
		game.setPlayers();

		Deck deck = new Deck();
		deck.fillDeck(Card.values());
		deck.shuffleCards();
		System.out.println("deck.shuffleCards()");

		game.deal(deck);
		System.out.println("playelőttvagyok");
		game.play();

		/*
		 * TODO befejezni a play()-t elfogyottEMárvalakinekAKártyája()
		 * metódussal felugró képek esetleg más cardokkal is kompatibilis legyen
		 * hibakezelések fun amúgyszépmunka todo-k megcsinálása
		 */
	}
}
