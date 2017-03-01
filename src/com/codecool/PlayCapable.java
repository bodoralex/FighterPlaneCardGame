package com.codecool;

import java.util.Queue;

/*
 * majd implementálni fogja a robot & a player
*/
public interface PlayCapable {

	public Integer choose();
	public Card draw();
	public String getName();
	public void addCardToHand(Card card);
	public OurQueue<Card> getHand();
	public String toString();
	public int cardsRemaining();
	public Card peek();

}
