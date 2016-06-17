package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;

public class Pair {

	private int highCard;
	
	public int checkForPair(ArrayList<Card> hand, HandRanker ranker) {
		int handRank = ranker.getHandRank("Pair");
		Map<Integer, Long> mapOfRanks = hand.stream().collect(groupingBy(Card::getCardRank, counting()));
		long secondCardInHand = mapOfRanks.get(hand.get(1).getCardRank());
		long fourthCardInHand = mapOfRanks.get(hand.get(3).getCardRank());
		if(mapOfRanks.size() == 4){ 
			if(secondCardInHand == 2 || fourthCardInHand == 2){
				return handRank;
			}
		}
		return 0;
	}
	
	public int findHighCard(ArrayList<Card> hand){
		return highCard;
	}
	
}
