package com.codecool;

import java.util.Queue;

/*
 * majd implementálni fogja a robot & a player
*/
public interface PlayCapable {
	
	public void attack();
	public void defense();
	
	public void setHand(Queue<Card> cards);
	public Queue<Card> getHand();
	//egyáltalán nem végleges
}
