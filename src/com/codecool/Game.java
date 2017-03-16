package com.codecool;

import java.util.*;
import java.util.Map.Entry;

public class Game {
	private Integer roundNumber = 0;
	final static String border = "-----------------------------------------------------------------";
	private Server server;
	private List<PlayCapable> players = new ArrayList<>();
	private List<PlayCapable> changingPlayerList = new ArrayList<>();
	private Printer printer;
	private final String[] attrs = { "Max Speed", "Max Height", "Max Takeoff weigth", "Maximum range" };
	private final Map<Integer, Comparator> choiceMap = new TreeMap<Integer, Comparator>() {
	
		{
			put(1, new speedComparator());
			put(2, new maxHeightComparator());
			put(3, new maxTakeoffWeightComparator());
			put(4, new rangeComparator());
		}

	};
	

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

	public void setUpServer() {
		
		ServerGUI serverGUI = new ServerGUI();
		serverGUI.sleepServer();
		
		int playersNumber = serverGUI.getPlayerNumber();
		int robotsNumber = serverGUI.getRobotNumber();
		

		server = new Server(playersNumber, robotsNumber, 6969);
		server.createServerSocket();

	}

	public void setPlayers() {

		ArrayList<Player> namelessPlayers = server.gatherPlayers();
		ArrayList<Player> players = server.setNames(namelessPlayers);
		this.players.addAll(players);
		for (int i = 0; i < server.getRobotsNumber(); i++) {
			Robot robot = new Robot();
			this.players.add(robot);
		}

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
		printer.print(border);
		broadCast(roundNumber);
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
		printer.print("\nThe winner is: " + winner.getName() + "\n");

		awardWinner(sorted);

		for (PlayCapable player : players) {
			printer.print(String.format("%s has %d card(s) left", player.getName(), player.cardsRemaining()));
		}

		printer.print(border);
		broadCast(createResultList(sorted));
		roundNumber++;
		return winner;
	}
	private ArrayList<ArrayList<String>> createResultList(TreeMap<Card, PlayCapable> list){
		
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		String first = "TRUE";
		
		for (Entry<Card, PlayCapable> entry : list.entrySet()) {
			ArrayList<String> subList = new ArrayList<String>();
			subList.add(entry.getKey().getName());
			subList.add(entry.getValue().getName());
			subList.add(first);
			
			result.add(subList);
			first = "FALSE";
		}
		
		return result;
	}
	
	
	
	private void broadCast(Object object){
		
		for (PlayCapable playCapable : players) {
			
			if(playCapable instanceof Player){
				Player player = (Player) playCapable;
				server.send(player.getOutputStream(), object);
			}
		}
	}

	private PlayCapable roundAttacker() {
		while (true) {
			try {
				for (PlayCapable playCapable : changingPlayerList) {
					if (!players.contains(playCapable)) {
						changingPlayerList.remove(playCapable);
					}
				}
				for (PlayCapable playCapable : players) {
					if (!changingPlayerList.contains(playCapable)) {
						changingPlayerList.add(playCapable);
					}
				}
				PlayCapable player = changingPlayerList.get(0);
				changingPlayerList.remove(0);
				return player;
			} catch (Exception e) {

			}
		}
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
				while (cardsNumber > 40 || cardsNumber < 1 || cardsNumber == -1 || cardsNumber < players.size()) {
					printer.print("Wrong number input! Try again!");
					howMany = Main.scanner.next().trim();
					cardsNumber = Integer.parseInt(howMany);
				}
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

	public PlayCapable outOfTheGame() {
		Iterator<PlayCapable> it = players.iterator();
		while (it.hasNext()) {
			PlayCapable player = it.next();
			if (player.cardsRemaining() == 0) {
				printer.print(player.getName() + " has zero cards left and is out of the game.");
				it.remove();
			}
		}
		if (players.size() == 1) {
			return players.get(0);
		}
		return null;
	}

	public void play() {
		while (outOfTheGame() == null) {
			round();
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printer.print("The game is ended. The winner is: " + outOfTheGame().getName());
		System.exit(0);
	}
}
