package com.codecool;

import java.util.Queue;

public class Player implements PlayCapable {

	protected String name;

	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer choose() {
		return null;

	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addToHand(Card card) {

	}

	@Override
	public void setHand(Queue<Card> cards) {

	}

	@Override
	public Queue<Card> getHand() {
		return null;
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

}
