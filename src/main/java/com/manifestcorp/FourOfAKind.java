package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;

public class FourOfAKind {

	public int checkFourOfAKind(ArrayList<Card> hand, HandRanker ranker) {
		int handRank = ranker.getHandRank("Four Of A Kind");
		Map<String, Long> map = hand.stream().collect(groupingBy(Card::getCard, counting()));
		long secondCard = map.get(hand.get(1).getCard());
		
		if(secondCard == 4){
			return handRank;
		}
		return 0;
	}
	
}
