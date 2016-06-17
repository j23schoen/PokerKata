package com.manifestcorp;

import java.util.TreeMap;

public class HandRanker {

	private TreeMap<String, Integer> handRanks = new TreeMap<String, Integer>();
	public HandRanker(){
		handRanks.put("Straight Flush", 9);
		handRanks.put("Four Of A Kind", 8);
		handRanks.put("Full House", 7);
		handRanks.put("Flush", 6);
		handRanks.put("Straight", 5);
		handRanks.put("Three Of A Kind", 4);
		handRanks.put("Two Pair", 3);
		handRanks.put("Pair", 2);
	}
	
	public int getHandRank(String key){
		return handRanks.get(key);
	}
}
