package com.manifestcorp;

import java.util.ArrayList;

public class StraightFlush {

	private Straight straightCheck = new Straight();
	private Flush flushCheck = new Flush();
	
	public int checkForStraightFlush(ArrayList<Card> hand, HandRanker ranker){
		int handRank = ranker.getHandRank("Straight Flush");
		if(flushCheck.checkForFlush(hand, ranker) != 0 && straightCheck.checkForStraight(hand, ranker) != 0){
			return handRank;
		}
		return 0;
	}		
}