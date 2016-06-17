package com.manifestcorp;


public class Card {

	private String player;
	private String card;
	private String suit;
	private int rank;
	public Card(String card, String suit, int rank, String player){
		this.card = card;
		this.suit = suit;
		this.rank = rank;
		this.player = player;
	}
	
	public String getCard(){
		return card;
	}
	
	public String getSuit(){
		return suit;
	}

	public int getCardRank(){
		return rank;
	}
	
	public String getPlayer(){
		return player;
	}
	
	public String toString(){
		return player + " " + card + " of " + suit + " with a rank of: " + rank;
	}
	
}
