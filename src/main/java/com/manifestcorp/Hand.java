package com.manifestcorp;

import java.util.*;

import java.util.regex.Pattern;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class Hand {

	private ArrayList<Card> player1;
	private ArrayList<Card> player2;
	private Scanner s;
	private Deck deck;
	private final String REGEX_FOR_FINDING_CARD_PAIRS = "(\\d{1,2}|[JQKA])([SCDH])";
	private HandRanker ranker;
	private StraightFlush straightFlush;
	private Flush flush;
	private FullHouse fullHouse;
	private FourOfAKind fourOfAKind;
	private ThreeOfAKind threeOfAKind;
	private TwoPair twoPair;
	private Pair pair;
	private Straight straight;
	private int[] player1RankArray, player2RankArray;
	private int player1Rank, player2Rank;
	private String firstPlayer;
	private String secondPlayer;
	
	public Hand(String input){
		player1 = new ArrayList<Card>();
		player2 = new ArrayList<Card>();
		s = new Scanner(input);
		deck = new Deck();
		ranker = new HandRanker();
		straightFlush = new StraightFlush();
		flush = new Flush();
		fullHouse = new FullHouse();
		fourOfAKind = new FourOfAKind();
		threeOfAKind = new ThreeOfAKind();
		twoPair = new TwoPair();
		pair = new Pair();
		straight = new Straight();
		player1RankArray = new int[8];
		player2RankArray = new int[8];
		fillPlayer1ArrayList(); 
		fillPlayer2ArrayList();
		sortHandsByRankInAscendingOrder();
		setHand1Rank();
		setHand2Rank();
	}

	private void fillPlayer1ArrayList(){
		firstPlayer = s.next(); 
		int counter = 0;
		
		while(counter < 5){
			s.next(Pattern.compile(REGEX_FOR_FINDING_CARD_PAIRS));
			String card = s.match().group(1);
			String suit = s.match().group(2);
			int rank = deck.getCardRank(s.match().group(1));
			player1.add(new Card(card, suit, rank, firstPlayer));
			counter++;
		}
	}
	
	private void setHand1Rank() {
		Map<String, Long> map = player1.stream().collect(groupingBy(Card::getCard, counting()));
		if(map.size() == 4){
			player1RankArray[0] = pair.checkForPair(player1, ranker);
		}
		else if(map.size() == 5){
			player1RankArray[3] = straight.checkForStraight(player1, ranker);
			player1RankArray[4] = flush.checkForFlush(player1, ranker);
			player1RankArray[7] = straightFlush.checkForStraightFlush(player1, ranker);
		}
		else if(map.size() < 4){
			player1RankArray[1] = twoPair.checkForTwoPairs(player1, ranker);
			player1RankArray[2] = threeOfAKind.checkForThreeOfAKind(player1, ranker);
			
			if(player1RankArray[2] > 0){
				player1RankArray[6] = fourOfAKind.checkFourOfAKind(player1, ranker);
				player1RankArray[5] = fullHouse.checkForFullHouse(player1, ranker);
			}
		}
		
		Arrays.sort(player1RankArray);
		player1Rank = player1RankArray[7];	
	}

	private void fillPlayer2ArrayList(){
		secondPlayer = s.next();
		while(s.hasNext()){
			s.next(Pattern.compile(REGEX_FOR_FINDING_CARD_PAIRS));
			String card = s.match().group(1);
			String suit = s.match().group(2);
			int rank = deck.getCardRank(s.match().group(1));
			player2.add(new Card(card, suit, rank, secondPlayer));
		}
	}
	
	private void sortHandsByRankInAscendingOrder(){
		Collections.sort(player1, new CardRankComparator());
		Collections.sort(player2, new CardRankComparator());
	}
	
	private void setHand2Rank() {
		Map<String, Long> map = player2.stream().collect(groupingBy(Card::getCard, counting()));
		
		if(map.size() == 4){
			player2RankArray[0] = pair.checkForPair(player2, ranker);
		}
		else if(map.size() == 5){
			player2RankArray[3] = straight.checkForStraight(player2, ranker);
			player2RankArray[4] = flush.checkForFlush(player2, ranker);
			if(player2RankArray[3] > 0 && player2RankArray[4] > 0){
				player2RankArray[7] = straightFlush.checkForStraightFlush(player2, ranker);
			}
		}
		else if(map.size() < 4){
			player2RankArray[1] = twoPair.checkForTwoPairs(player2, ranker);
			player2RankArray[2] = threeOfAKind.checkForThreeOfAKind(player2, ranker);
			
			if(player2RankArray[2] > 0){
				player2RankArray[5] = fullHouse.checkForFullHouse(player2, ranker);
				player2RankArray[6] = fourOfAKind.checkFourOfAKind(player2, ranker);	
			}
		}
		
		Arrays.sort(player2RankArray);
		player2Rank = player2RankArray[7];	
	}

	public ArrayList<Card> getPlayer1(){
		return player1;
	}
	
	public ArrayList<Card> getPlayer2(){
		return player2;
	}
	
	public int player1Rank(){
		return player1Rank;
	}
	
	public int player2Rank(){
		return player2Rank;
	}
	
	public String player1Name(){
		return firstPlayer;
	}
	
	public String player2Name(){
		return secondPlayer;
	}
}
