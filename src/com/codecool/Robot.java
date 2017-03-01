package com.codecool;

import java.util.*;

public class Robot implements PlayCapable {
	protected static int robots = 0;
	private Double avgSpeed = 558.0;
	private Double avgHeight = 10482.0;
	private Double avgWeight = 4952.0;
	private Double avgRange = 1072.0;
	private ArrayList<Card> seenCards = new ArrayList<>();
	private String name;
	private OurQueue<Card> hand = new OurQueue<>();
	private LinkedHashMap<String, Double> compareList = new LinkedHashMap<>();
	private LinkedHashMap<String, Integer> attributeIdList = new LinkedHashMap<>();

	public Robot() {
		this.name = "Robot " + robots;
		robots++;
		this.avgHeight = Math.random() * 500;
		this.avgRange = Math.random() * 500;
		this.avgWeight = Math.random() * 500;
		this.avgSpeed = Math.random() * 200;
	}

	public ArrayList<Card> getSeenCards() {
		return this.seenCards;
	}

	public void setSeenCards(Queue<Card> cards) {
		this.seenCards.addAll(cards);
		this.updateAverages();
	}

	public String getName() {
		return this.name;
	}

	@Override
	public Integer choose() {
		Card currentExamined = hand.peek();

		Double afteravgSpeed = currentExamined.getSpeed() / this.avgSpeed;
		Double afteravgHeight = currentExamined.getMaxHeight() / this.avgHeight;
		Double afteravgWeight = currentExamined.getMaxTakeoffWeight() / this.avgWeight;
		Double afteravgRange = currentExamined.getRange() / this.avgRange;
		compareList.put("avgSpeed", afteravgSpeed);
		compareList.put("avgWeight", afteravgWeight);
		compareList.put("avgHeight", afteravgHeight);
		compareList.put("avgRange", afteravgRange);
		attributeIdList.put("avgSpeed", 0);
		attributeIdList.put("avgHeight", 1);
		attributeIdList.put("avgWeight", 2);
		attributeIdList.put("avgRange", 3);
		Double currentExaminedAttribute = 0.0;
		String bestChoice = "";
		for (Double d : compareList.values()) {
			if (d >= currentExaminedAttribute) {
				currentExaminedAttribute = d;
				for (Map.Entry e : compareList.entrySet()) {
					if (e.getValue() == d) {
						bestChoice = e.getKey().toString();
					}
				}
			}
		}

		return attributeIdList.get(bestChoice);

	}

	@Override
	public Card draw() {

		return this.hand.remove();
	}

	public String toString() {

		return name;

	}

	private void updateAverages() {
		for (Card c : this.seenCards) {
			avgSpeed += c.getSpeed();
			avgRange += c.getRange();
			avgWeight += c.getMaxTakeoffWeight();
			avgHeight += c.getMaxHeight();
		}
		this.avgSpeed = avgSpeed / seenCards.size();
		this.avgRange = avgRange / seenCards.size();
		this.avgWeight = avgWeight / seenCards.size();
		this.avgHeight = avgHeight / seenCards.size();
	}

	public OurQueue<Card> getHand() {
		return this.hand;
	}

	public void setHand(OurQueue<Card> cards) {
		this.hand = cards;
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

	@Override
	public void addCardToHand(Card card) {
		hand.add(card);

	}

	@Override
	public int cardsRemaining() {
		return getHand().size();
	}

}
