package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {

	private Set<PlayCapable> players = new HashSet<PlayCapable>();

	private Printer printer;

	public void getPlayers() {
		printer.print("Let's set up the players!");
		Scanner scanner = new Scanner(System.in);
		Set<Robot> robots = new HashSet<Robot>();
		List<String> robotAnswer = (List<String>) Arrays.asList("Robot", "robot", "ai", "Ai", "AI", "bot");

		printer.print(String.format(
				"Please enter the name of the %s. player." + "Or enter Robot, if you would like to add a robot :).",
				players.size()));
		boolean gathering = true;
		while (gathering) {
			String newName = scanner.next().trim();
			if (newName.equalsIgnoreCase("exit")) {
				gathering = false;
			} else if (robotAnswer.contains(newName)) {
				PlayCapable robot = new Robot();
				players.add(robot);

			} else {
				PlayCapable player = new Player(newName);
				if (players.contains(player)) {
					try{
						throw new NameTakenException();
					}catch(NameTakenException e){
						e.errorMessage();
					}
				}
				players.add(player);
			}

		}

	}

	public Player round() {

		return null;
	}

	public void deal(Deck deck) {
		Scanner scanner = new Scanner(System.in);
		printer.print("How many cards are you want to play with?");
		Integer cardsNumber = Integer.MIN_VALUE;
		while (cardsNumber.equals(Integer.MIN_VALUE)) {
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
