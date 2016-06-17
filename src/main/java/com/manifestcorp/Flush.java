package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;

public class Flush {
	
	public int checkForFlush(ArrayList<Card> hand, HandRanker ranker){
		int handRank = ranker.getHandRank("Flush");
		Map<String, Long> map = hand.stream().collect(groupingBy(Card::getSuit, counting()));
		long firstCard = map.get(hand.get(0).getSuit());
		if(firstCard == 5){
			return handRank;
		}
		return 0;
	}
}
