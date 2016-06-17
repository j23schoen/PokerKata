package com.manifestcorp;

import java.util.Comparator;

public class CardRankComparator implements Comparator<Card>{

	public int compare(Card c1, Card c2) {
		if(c1.getCardRank() >= c2.getCardRank()){
			return 1;
		}
		else{
			return -1;
		}
	}
	
}
