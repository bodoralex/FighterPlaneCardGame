package com.codecool;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class Game {

	private Set<PlayCapable> players = new HashSet<PlayCapable>(); // majd legyen
																	// private
	private Printer printer;

	
	public String parseKey(){
	
		List<String> robotAnswer = (List<String>) Arrays.asList("lobot", "robot","borot", "ai", "bot");
		List<String> exitAnswer = (List<String>) Arrays.asList("exit", "q", "quit", "done");
		
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next().trim().toLowerCase();
		if(robotAnswer.contains(input)) return "robot";
		if(exitAnswer.contains(input)) return "exit";
		return input;

	}

	public void gatherPlayers() {
		printer.print("Let's set up the players!");
		printer.print(String.format(
				"Please enter the name of the %s. player. " + "Or enter Robot, if you would like to add a robot :).",
				players.size()));
		printer.print("Enter done if you wouldn't like to add nem player");
		boolean gathering = true;
		while (gathering) {
			String input = parseKey();
			if (input .equals("exit")) {
				gathering = false;
			} else if (input.equals("robot")) {
				PlayCapable robot = new Robot();
				players.add(robot);
				printer.print(String.format("%s added", robot.getName()));
			} else {
				PlayCapable player = new Player(input);
				if (players.contains(player)) {
					try {
						throw new NameTakenException();
					} catch (NameTakenException error) {
						printer.print(error.errorMessage());
					}
				}else{
					printer.print(String.format("%s added", player.getName()));
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
		// deck.handout(gatherPlayers(), cardsNumber);
		printer.print("Cards are dealt.");
	}


	public void setPrinter(Printer printer) {
		this.printer = printer;

	}
	public Set<PlayCapable> getPlayers(){
		return players;
	}

}
