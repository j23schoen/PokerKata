package com.manifestcorp;

import java.util.ArrayList;

public class Straight {

	public int checkForStraight(ArrayList<Card> hand, HandRanker ranker){
		int handRank = ranker.getHandRank("Straight");
		int testRank = hand.get(0).getCardRank() + 1;
		for(int i = 1; i < 5; i++){
			if(hand.get(i).getCardRank() != testRank){
				return 0;
			}
			testRank++;
		}
		return handRank;
	}
	
}
