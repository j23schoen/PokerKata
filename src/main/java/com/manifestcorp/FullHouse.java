package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;

public class FullHouse {
	
	public int checkForFullHouse(ArrayList<Card> hand, HandRanker ranker) {
		int handRank = ranker.getHandRank("Full House");
		Map<String, Long> map = hand.stream().collect(groupingBy(Card::getCard, counting()));
		long thirdCardInHand = map.get(hand.get(2).getCard());
		
		if(thirdCardInHand == 3 && map.size() == 2){
			return handRank;
		}
		return 0;
	}
	
}
