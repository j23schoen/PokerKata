package com.manifestcorp;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

import java.util.ArrayList;
import java.util.Map; 

public class TwoPair {

	private boolean possibility1, possibility2, possibility3;
	private int[] card = new int[5];
	
	public int checkForTwoPairs(ArrayList<Card> hand, HandRanker ranker) {
		int handRank = ranker.getHandRank("Two Pair");
		Map<String, Long> map = hand.stream().collect(groupingBy(Card::getCard, counting()));
		if(map.size() == 3){
	
			for(int i = 0; i < hand.size(); i++){
				card[i] = hand.get(i).getCardRank();
			}
			
			possibility1 = card[0] == card[1] && card[2] == card[3];
			possibility2 = card[0] == card[1] && card[3] == card[4];
			possibility3 = card[1] == card[2] && card[3] == card[4];
			
			if((possibility1 || possibility2 || possibility3)){
				return handRank;
			}
		}
		return 0;
	}
	
}
