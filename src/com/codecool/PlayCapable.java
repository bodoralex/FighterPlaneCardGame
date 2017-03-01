package com.codecool;

import java.util.Queue;

/*
 * majd implementálni fogja a robot & a player
*/
public interface PlayCapable {
	public Integer choose();
	public void draw();
	public void addToHand(Card card);
	public void setHand(Queue<Card> cards);
	public Queue<Card> getHand();
	public String getName();
	
	//egyáltalán nem végleges
}
