package com.manifestcorp;

import java.util.ArrayList;

public class Game {
	
	ThreeOfAKind threeOfAKind;
	public Game(){
		threeOfAKind = new ThreeOfAKind();
	}

	public String determineWinner(Hand hand) {
		if(hand.player1Rank() > hand.player2Rank()){
			return hand.player1Name();
		}
		else if(hand.player1Rank() == hand.player2Rank()){
			return breakTie(hand);
		}
		else{
			return hand.player2Name();
		}
	}
	
	private String breakTie(Hand hand){
		int hand1HighCard;
		int hand2HighCard;
		
		int threeOfAKindTieWinner = 0;
		if(hand.player1Rank() == 4){
			threeOfAKindTieWinner = threeOfAKindTieBreak(hand);
		}
		
		if(threeOfAKindTieWinner == 1){
			return hand.player1Name();
		}
		else if(threeOfAKindTieWinner == 2){
			return hand.player2Name();
		}
		
		for(int i = 4; i >= 0; i--){
			hand1HighCard = findHighCard(hand.getPlayer1(), i);
			hand2HighCard = findHighCard(hand.getPlayer2(), i);
			if(hand1HighCard > hand2HighCard){
				return hand.player1Name();
			}
			else if(hand2HighCard > hand1HighCard){
				return hand.player2Name();
			}
		}
		return "tie";
	}
	
	private int threeOfAKindTieBreak(Hand hand){
		int hand1High = threeOfAKind.findHighCard(hand.getPlayer1());
		int hand2High = threeOfAKind.findHighCard(hand.getPlayer2());
		
		if(hand1High > hand2High){
			return 1;
		}
		else if(hand2High > hand1High){
			return 2;
		}
		else{
			return 3;
		}
	}
	
	private int findHighCard(ArrayList<Card> hand, int index){
		return hand.get(index).getCardRank();
	}
	
}
