package com.codecool;

import java.util.*;

public class Game {

	public Set<PlayCapable> players = new HashSet<PlayCapable>(); // majd legyen
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

		PlayCapable roundAttacker = roundAttacker();
		roundAttacker.attack();
		for (PlayCapable playCapable: players) {
			if (!playCapable.equals(roundAttacker)) {
				playCapable.defense();
			}
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

	class SpeedComparator implements Comparator<PlayCapable> {

		@Override
		public int compare(PlayCapable o1, PlayCapable o2) {
			int o1Speed = o1.getHand().peek().getSpeed();
			int o2Speed = o2.getHand().peek().getSpeed();
			if (o1Speed > o2Speed) {
				return -1;
			} else if (o1Speed < o2Speed) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class maxHeightComparator implements Comparator<PlayCapable> {

		@Override
		public int compare(PlayCapable o1, PlayCapable o2) {
			int o1MaxHeight = o1.getHand().peek().getMaxHeight();
			int o2MaxHeight = o2.getHand().peek().getMaxHeight();
			if (o1MaxHeight > o2MaxHeight) {
				return -1;
			} else if (o1MaxHeight < o2MaxHeight) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class maxTakeoffWeightComparator implements Comparator<PlayCapable> {

		@Override
		public int compare(PlayCapable o1, PlayCapable o2) {
			int o1MaxTakeoffWeight = o1.getHand().peek().getMaxTakeoffWeight();
			int o2MaxTakeoffWeight = o2.getHand().peek().getMaxTakeoffWeight();
			if (o1MaxTakeoffWeight > o2MaxTakeoffWeight) {
				return -1;
			} else if (o1MaxTakeoffWeight < o2MaxTakeoffWeight) {
				return 1;
			} else {
				return 0;
			}
		}
	}

	class rangeComparator implements Comparator<PlayCapable> {

		@Override
		public int compare(PlayCapable o1, PlayCapable o2) {
			int o1Range = o1.getHand().peek().getRange();
			int o2Range = o2.getHand().peek().getRange();
			if (o1Range > o2Range) {
				return -1;
			} else if (o1Range < o2Range) {
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
