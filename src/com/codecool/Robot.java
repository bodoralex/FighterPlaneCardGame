package com.codecool;

import java.util.Queue;

public class Robot implements PlayCapable {

	protected String name;
	protected static int robots = 0;

	public Robot() {
		this.name = "Robot " + robots;
		robots++;
	}

	public String getName() {
		return name;
	}

	@Override
	public void attack() {
		// TODO Auto-generated method stub

	}

	@Override
	public void defense() {
		// TODO Auto-generated method stub

	}

	@Override
	public void setHand(Queue<Card> cards) {
		// TODO Auto-generated method stub

	}

	@Override
	public Queue<Card> getHand() {
		// TODO Auto-generated method stub
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
		Robot other = (Robot) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

}
