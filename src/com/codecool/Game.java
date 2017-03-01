package com.codecool;

import java.util.*;

public class Game {

	public List<PlayCapable> players = new ArrayList<>(); // majd legyen
																	// private
	private List<PlayCapable> playerList = new ArrayList<>();

	private Printer printer;

	public void getPlayers() {
		printer.print("Let's set up the players!");
		Scanner scanner = new Scanner(System.in);
		Set<Robot> robots = new HashSet<Robot>();
		List<String> robotAnswer = (List<String>) Arrays.asList("lobot", "robot","borot", "ai", "bot");

		printer.print(String.format(
				"Please enter the name of the %s. player." + "Or enter Robot, if you would like to add a robot :).",
				players.size()));
		boolean gathering = true;
		while (gathering) {
			String newName = scanner.next().trim().toLowerCase();
			if (newName.equals("exit")) {
				gathering = false;
			} else if (robotAnswer.contains(newName)) {
				PlayCapable robot = new Robot();
				players.add(robot);
				printer.print(String.format("%s added", robot.getName()));
			} else {
				PlayCapable player = new Player(newName);

				if (players.contains(player)) {
					try {
						throw new NameTakenException();
					} catch (NameTakenException e) {
						printer.print(e.errorMessage());
					}
				}else{
					printer.print(String.format("%s added", player.getName()));
				}
				players.add(player);
			}
		}
	}

	public PlayCapable round() {

		Card[] cards = new Card[players.size()];
		PlayCapable roundAttacker = roundAttacker();
		roundAttacker.choose();
		for (int i=0; i< players.size(); i++) {
				players.draw();
		}

		// roundAttacker választása alapján sort egy playerCapables-listben...return 0.index...
		return null;

	}

	public PlayCapable roundAttacker() {

		if (playerList.size() == 0) {
			for (PlayCapable playCapable : players) {
				playerList.add(playCapable);
			}
		}

		PlayCapable player = playerList.get(0);
		playerList.remove(0);
		return player;

	}

	class SpeedComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			int c1Speed = c1.getSpeed()
			int c2Speed = c2.getSpeed()
			if (c1Speed > c2Speed) {
				return -1;
			} else if (c1Speed < c2Speed) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class maxHeightComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			int c1MaxHeight = c1.getMaxHeight();
			int c2MaxHeight = c2.getMaxHeight();
			if (c1MaxHeight > c2MaxHeight) {
				return -1;
			} else if (c1MaxHeight < c2MaxHeight) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class maxTakeoffWeightComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			int c1MaxTakeoffWeight = c1.getMaxTakeoffWeight();
			int c2MaxTakeoffWeight = c2.getMaxTakeoffWeight();
			if (c1MaxTakeoffWeight > c2MaxTakeoffWeight) {
				return -1;
			} else if (c1MaxTakeoffWeight < c2MaxTakeoffWeight) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class rangeComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			int c1Range = c1.getRange();
			int c2Range = c2.getRange();
			if (c1Range > c2Range) {
				return -1;
			} else if (c1Range < c2Range) {
				return 1;
			} else {
				return 0;
			}
		}
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
		// deck.handout(getPlayers(), cardsNumber);
		printer.print("Cards are dealt.");
	}

	public void getCards(Card[] cards) {

	}

	public void setPrinter(Printer printer) {
		this.printer = printer;

	}

}
