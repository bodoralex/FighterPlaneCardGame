package com.codecool;

public class Player implements PlayCapable {

    private Printer printer = new Printer();
    private OurQueue<Card> hand = new OurQueue<>();
    private String name;

	public Player(String name) {
		this.name = name;
	}

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void addCardToHand(Card card) {
        hand.add(card);
    }

    @Override
    public Card draw() {
        return hand.remove();
    }

	@Override
	public Integer choose() {
		try {
			DisplayImage dsplyImage = new DisplayImage(hand.peek());
			dsplyImage.start();
		} catch (IOException e) {
			e.printStackTrace();
		}
		checkMyCard();
		boolean goodInput = false;

        while (!goodInput) {
        	String input = Main.scanner.next().trim();
            Integer playerChoice = Integer.parseInt(input);
            if (playerChoice == 1 || playerChoice == 2 || playerChoice == 3 || playerChoice == 4) {
                return playerChoice;
            }
        }	printer.print("Wrong number.");
        return 0;
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
	public OurQueue<Card> getHand() {
		return hand;
	}

	@Override
	public int cardsRemaining() {
		return getHand().size();
	}

	@Override
	public Card peek() {
		return hand.peek();
	}

	public String toString(){
		return name;
	}

	public void checkMyCard() {
		Card topCard = hand.peek();
		printer.print(topCard);
		printer.print("Choose the attribute you want to use");
	}

}
