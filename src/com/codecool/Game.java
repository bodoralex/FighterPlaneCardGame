package com.codecool;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	private ArrayList<PlayCapable> players = new ArrayList<PlayCapable>();

	private Printer printer;

	public ArrayList<PlayCapable> getPlayers() {
		return players;
	}

	public Player round() {
		// TODO Auto-generated method stub

		// attack
		// card 0/1/2/3
		// defense
		// boolean
		return null;
	}

	public void deal(Deck deck) {
		Scanner scanner = new Scanner(System.in);
		printer.print("How many cards are you want to play with?");
		Integer cardsNumber = new Integer(null);
		while (cardsNumber == null) {
			try {
				String howMany = scanner.next();
				cardsNumber = Integer.getInteger(howMany);
			} catch (Exception e) {
				printer.print("That is not an integer.");
			}
		}
		scanner.close();
		deck.handout(getPlayers(), cardsNumber);
		printer.print("Cards are dealt.");
	}

	public void getCards(Card[] cards) {

	}

	public void setPrinter(Printer printer) {
		this.printer = printer;

	}

}
