package com.codecool;

import java.util.Queue;

/*
 * majd implementálni fogja a robot & a player
*/
public interface PlayCapable {
	public Integer choose();
	public Card draw();
	public String getName();
	void addToHand(Card card);
	public OurQueue<Card> getDeck();
}
