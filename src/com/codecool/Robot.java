package com.codecool;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Map;

public class Robot implements PlayCapable {
	protected static int robots = 0;

	public Robot() {
		this.name = "Robot " + robots;
		robots++;
	}

	public String getName() {
		return this.name;
	}

	private final Double AVGSPEED = 558.0;
	private final Double AVGHEIGHT = 10482.0;
	private final Double AVGWEIGHT = 4952.0;
	private final Double AVGRANGE = 1072.0;
	private String name;
	private Queue<Card> hand = new LinkedList<>();
	private Double afteravgSpeed, afteravgHeight, afteravgWeight, afteravgRange;
	private LinkedHashMap<String, Double> compareList = new LinkedHashMap<>();
	private LinkedHashMap<String, Integer> attributeIdList = new LinkedHashMap<>();
	private Integer chosenAttributeNumber;

	@Override
	public Integer choose() {
		Card currentExamined = hand.peek();
		afteravgSpeed = currentExamined.getSpeed() / AVGSPEED;
		afteravgHeight = currentExamined.getMaxHeight() / AVGHEIGHT;
		afteravgWeight = currentExamined.getMaxTakeoffWeight() / AVGWEIGHT;
		afteravgRange = currentExamined.getRange() / AVGRANGE;
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

		chosenAttributeNumber = attributeIdList.get(bestChoice);
		return chosenAttributeNumber;

	}

	@Override
	public Card draw() {

		return null;

	}

	public void setHand(Queue<Card> cards) {
		this.hand = cards;
	}

	public Queue<Card> getHand() {
		return this.hand;
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
	public void addToHand(Card card) {
		hand.add(card);

	}

}
