package com.codecool;

import java.util.*;

public class Game {

	public List<PlayCapable> players = new ArrayList<>(); // majd legyen
															// private
	private List<PlayCapable> changingPlayerList = new ArrayList<>();

	private final Map<Integer, Comparator> choiceMap = new TreeMap<Integer, Comparator>() {
		{
			put(1, new speedComparator());
			put(2, new maxHeightComparator());
			put(3, new maxTakeoffWeightComparator());
			put(4, new rangeComparator());
		};

	};
	static final String[] attrs = { "Max Speed", "Max Height", "Max Takeoff weigth", "Maximum range" };

	private Printer printer;

	public String parseKey() {
		Scanner scanner = new Scanner(System.in);

		List<String> robotAnswer = (List<String>) Arrays.asList("lobot", "robot", "borot", "ai", "bot");
		List<String> exitAnswer = (List<String>) Arrays.asList("k", "exit", "q", "quit", "done");

		String inputScan = scanner.next();
		String input = inputScan.trim().toLowerCase();

		if (robotAnswer.contains(input))
			return "robot";
		if (exitAnswer.contains(input))
			return "exit";
		return input;
	}

	public void gatherPlayers() {
		printer.print("Let's set up the players!");
		printer.print(String.format(
				"Please enter the name of the %s. player. " + "Or enter Robot, if you would like to add a robot :).",
				players.size()));
		printer.print("Enter 'done' if you wouldn't like to add nem player");
		boolean gathering = true;
		while (gathering) {
			String input = parseKey();
			if (input.equals("exit")) {
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
				} else {
					printer.print(String.format("%s added", player.getName()));
				}
				players.add(player);
			}
		}
	}

	public void fillChoiceMap() { // TODO rmovable

		choiceMap.put(1, new speedComparator());
		choiceMap.put(2, new maxHeightComparator());
		choiceMap.put(3, new maxTakeoffWeightComparator());
		choiceMap.put(4, new rangeComparator());

	}

	public void awardWinner(TreeMap<Card, PlayCapable> map) {
		int count = 0;
		PlayCapable winner = map.firstEntry().getValue();
		for (Card card : map.keySet()) {
			winner.addCardToHand(card);
			count++;
		}
		printer.print("The winner got " + count + " cards");
	}

	public PlayCapable round() {
		printer.print("-----------------------------------------------------------------");
		Map<Card, PlayCapable> cards = new HashMap<>();
		PlayCapable roundAttacker = roundAttacker();
		printer.print(roundAttacker.getName() + " is attacking!\n");
		int choice = roundAttacker.choose();
		printer.print(String.format("%s' choosen arrtibute is: %s", roundAttacker.getName(), attrs[choice - 1]));
		for (PlayCapable player : players) {
			printer.print(player.getName() + "'s card: " + player.peek());
			cards.put(player.draw(), player);
		}
		Queue<Card> robotCards = new LinkedList<>();
		robotCards.addAll(cards.keySet());

		for (PlayCapable playCapable : players) {
			if (playCapable instanceof Robot) {
				((Robot) playCapable).setSeenCards(robotCards);
			}
		}

		TreeMap<Card, PlayCapable> sorted = new TreeMap<Card, PlayCapable>(choiceMap.get(choice));
		sorted.putAll(cards);
		PlayCapable winner = sorted.firstEntry().getValue();
		printer.print("\nThe winner is: " + winner.getName());

		awardWinner(sorted);
		printer.print("-----------------------------------------------------------------");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return winner;
	}

	public PlayCapable roundAttacker() { // legyenmáriterátor

		if (changingPlayerList.size() == 0) {
			for (PlayCapable playCapable : players) {
				changingPlayerList.add(playCapable);
			}
		}

		PlayCapable player = changingPlayerList.get(0);
		changingPlayerList.remove(0);
		return player; // TODO return remove

	}

	class speedComparator implements Comparator<Card> {

		@Override
		public int compare(Card c1, Card c2) {
			int c1Speed = c1.getSpeed();
			int c2Speed = c2.getSpeed();
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
		printer.print("How many cards do you want to play with?");
		printer.print("The maximum card number is: " + deck.deckSize());
		Integer cardsNumber = new Integer(0);
		while (cardsNumber.equals(0)) {
			try {
				String howMany = Main.scanner.next().trim();
				cardsNumber = Integer.parseInt(howMany);
			} catch (Exception e) {
				printer.print("That is not an integer.");
			}
		}
		deck.handout(getPlayers(), cardsNumber);
		printer.print("Cards are dealt.");
	}

	public void setPrinter(Printer printer) {
		this.printer = printer;

	}

	public List<PlayCapable> getPlayers() {
		return players;
	}

}
