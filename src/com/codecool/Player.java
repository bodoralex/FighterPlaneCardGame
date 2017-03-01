package com.codecool;

import java.util.Scanner;

public class Player implements PlayCapable {

    Scanner scanner = new Scanner(System.in);
    Printer printer = new Printer();
    OurQueue<Card> hand = new OurQueue<>();
    String name;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    // Lehet, hogy kell még
    public int attack() {
        Card topCard = hand.peek();
        printer.print(topCard);
        printer.print("Choose the attribute you want to use");
        return choose();
    }

    @Override
    public Card draw() {
        return hand.remove();
    }

    @Override
    public Integer choose() {
        boolean goodInput = false;
        while (!goodInput) {
            int playerChoice = scanner.nextInt();
            if (playerChoice == 1 || playerChoice == 2 || playerChoice == 3 || playerChoice == 4) {
                return playerChoice;
            }
        }
        return 0;
    }

	@Override
	public void addToHand(Card card) {
		hand.add(card);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	@Override
	public OurQueue<Card> getDeck() {
		return hand;
	}

	
	
}
