package com.manifestcorp;

import java.util.TreeMap;

public class Deck {

	private TreeMap<String, Integer> deck = new TreeMap<String, Integer>();
	public Deck(){
		deck.put("2", 2);
		deck.put("3", 3);
		deck.put("4", 4);
		deck.put("5", 5);
		deck.put("6", 6);
		deck.put("7", 7);
		deck.put("8", 8);
		deck.put("9", 9);
		deck.put("10", 10);
		deck.put("J", 11);
		deck.put("Q", 12);
		deck.put("K", 13);
		deck.put("A", 14);
	}
	
	public int getCardRank(String key){
		return deck.get(key);
	}
}
