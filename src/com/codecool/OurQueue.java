package com.codecool;

/**
 * @author bodor
 *
 * @param <Card>
 * FIFO
 * queue
 * no natural order
 */

public class OurQueue<Card> {
	
	private Object[] deck = new Object[0];
	
	public void add(Card card){
		Object[] newDeck = new Object[deck.length+1];
		for (int i = 0; i < deck.length; i++) {
			newDeck[i] = deck[i];
		}
		newDeck[newDeck.length-1] = card;
		deck = newDeck;
	}

	public Card remove(){
		Object[] newDeck = new Object[deck.length-1];
		for (int i = 1; i < deck.length; i++) {
			newDeck[i-1] = deck[i];
		}
		Card firstElem = (Card) deck[0];
		deck = newDeck;
		return firstElem;
	}

	public Card peek(){
		return (Card) deck[0];
	}

	public int size(){
		return deck.length;
	}

}
