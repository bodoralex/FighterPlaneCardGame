package com.codecool;

import java.util.*;

public class Robot implements PlayCapable {
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
        for(Double d : compareList.values()) {
            if(d >= currentExaminedAttribute) {
                currentExaminedAttribute = d;
                for(Map.Entry e : compareList.entrySet()) {
                    if(e.getValue() == d) {
                        bestChoice = e.getKey().toString();
                    }
                }
            }
        }

        chosenAttributeNumber = attributeIdList.get(bestChoice);
        return chosenAttributeNumber;

    }

    @Override
    public void draw() {

    }

    @Override
    public void addToHand(Card card) {
        this.hand.add(card);
    }

    @Override
    public Queue<Card> getHand() {
        return this.hand;
    }

    @Override
    public void setHand(Queue<Card> cards) {
        this.hand = cards;
    }
}
