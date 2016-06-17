package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map;

public class ThreeOfAKind {
	
	public int checkForThreeOfAKind(ArrayList<Card> hand, HandRanker ranker) {
		int handRank = ranker.getHandRank("Three Of A Kind");
		Map<String, Long> map = hand.stream().collect(groupingBy(Card::getCard, counting()));
		long thirdCard = map.get(hand.get(2).getCard());
		if(map.size() == 3){
			if(thirdCard == 3){
				return handRank;
			}
		}
		return 0;
	}
	
	public int findHighCard(ArrayList<Card> hand){
		return hand.get(2).getCardRank();
	}
	
}
