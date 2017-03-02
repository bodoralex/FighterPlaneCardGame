package com.codecool;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.util.List;

public class Game {

	private List<PlayCapable> players = new ArrayList<>();
	private List<PlayCapable> changingPlayerList = new ArrayList<>();
	public	String textInput = " ";
	public String userWord=" ";
	public JButton submit = new JButton("OK");
	public JButton submit2 = new JButton("Done");
	public JTextField textField = new JTextField("Type here");
	public void submitAction(){
		userWord = textField.getText();
	}

	public void setGathering(boolean gathering) {
		this.gathering = gathering;
	}

	public boolean gathering=false;
	private Printer printer;
	public Integer cardsNumber = 0;
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

		List<String> robotAnswer = (List<String>) Arrays.asList("lobot", "robot", "borot", "ai", "bot");
		List<String> exitAnswer = (List<String>) Arrays.asList("k", "exit", "q", "quit", "done");


		if (robotAnswer.contains(textInput))
			return "robot";
		if (exitAnswer.contains(textInput))
			return "exit";
		if (textInput!="") {
			return textInput;
		}
		return textInput;
	}

	public void gatherPlayers() {
		printer.print("Let's set up the players!");
		printer.print(String.format(
				"Please enter the name of the %s. player. " + "Or enter Robot, if you would like to add a robot :).",
				players.size()));
		printer.print("Enter 'done' if you wouldn't like to add nem player");

		while (gathering) {
			gathering = false;
			JFrame frame = new JFrame();
			frame.setLayout(new FlowLayout());
			frame.setSize(600,350);
			JLabel lbl = new JLabel("Lets set up the players!");
			frame.add(lbl);
			textField.setText("Enter player name here!");
			textField.setVisible(true);
			frame.add(textField);
			frame.add(submit);
			frame.add(submit);
			frame.setVisible(true);

			submit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					submitAction();
					setGathering(true);
					frame.setVisible(false);
				}
			});
			submit2.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					submitAction();
					setGathering(false);
				}
			});
			if (userWord.equals("exit")) {
				gathering = false;
			} else if (userWord.equals("robot")) {
				PlayCapable robot = new Robot();
				players.add(robot);
				printer.print(String.format("%s added", robot.getName()));
			} else {
				PlayCapable player = new Player(userWord);
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
		
		for (PlayCapable player : players) {
			printer.print(String.format("%s has %d card(s) left",player.getName(), player.cardsRemaining()));
		}
		
		printer.print("-----------------------------------------------------------------");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		return winner;
	}

	public PlayCapable roundAttacker() {
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

		//printer.print("How many cards do you want to play with?");
		//printer.print("The maximum card number is: " + deck.deckSize());

		while (Integer.parseInt(userWord)==0) {
			try {
				JFrame frame = new JFrame();
				frame.setLayout(new FlowLayout());
				frame.setSize(600,350);
				JLabel lbl = new JLabel("How many cards would you like to play with? (2-40)");
				frame.add(lbl);
				textField.setVisible(true);
				frame.add(textField);
				frame.add(submit);
				submit.setText("OK");
				frame.setVisible(true);
				submit.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent e) {
						submitAction();
						frame.setVisible(false);
					}
				});
		//		String howMany = Main.scanner.next().trim();
				//
				while (Integer.parseInt(userWord) > 40 || Integer.parseInt(userWord) < 1 || Integer.parseInt(userWord) == -1 || Integer.parseInt(userWord) < players.size()) {
				//	printer.print("Wrong number input! Try again!");
				//	howMany = Main.scanner.next().trim();
				//	cardsNumber = Integer.parseInt(howMany);
					lbl.setText("Wrong number input! Try again!");
				}
			} catch (Exception e) {
				printer.print("That is not an integer.");
			}
		}
		deck.handout(getPlayers(), Integer.parseInt(userWord));
	//	printer.print("Cards are dealt.");
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
		}
		printer.print("The game is ended. The winner is: " + outOfTheGame().getName());
		System.exit(0);
	}
}
