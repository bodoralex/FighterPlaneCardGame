package com.codecool;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * Created by pata on 2017.02.27..
 */
public class Deck {

	private ArrayList<Card> cards = new ArrayList<>();

	public Deck() {
	};

	public Deck(Card[] cards) {
		this.cards = (ArrayList<Card>) Arrays.asList(cards);
	}

	public void fillDeck(Card[] cardsToAdd) {
		for (Card card : cardsToAdd) {
			cards.add(card);
		}
	}

	public void shuffleCards() {
		Collections.shuffle(cards);
	}

	public void handout(List<PlayCapable> players, int cardsNumber) {
		int cardsPersPlayer = (int) Math.floor(cardsNumber / players.size());
		Iterator<Card> cardIterator = cards.iterator();
		for (int i = 0; i < players.size(); i++) {
			for (int j = 0; j < cardsPersPlayer; j++) {
				Card card = cardIterator.next();
				players.get(i).addCardToHand(card);
			}
		}
	}

	public ArrayList<Card> getCards() {
		return cards;
	}
}
