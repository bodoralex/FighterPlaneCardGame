package com.codecool;

public interface PlayCapable {

	Integer choose();
	Card draw();
	String getName();
	void addCardToHand(Card card);
	OurQueue<Card> getHand();
	String toString();
	int cardsRemaining();
	Card peek();

}
