package com.manifestcorp;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class PokerTest {
	
	private HandRanker ranker;
	Game g;
	Hand straightFlush;
	Hand fourOfAKind;
	Hand fullHouse;
	Hand flush;
	Hand straight;
	Hand threeOfAKind;
	Hand twoPairs;
	Hand pair;
	Hand twoPairVsThreeOfAKind;
	Hand highCard;
	
	@Before
	public void setUp(){
		ranker = new HandRanker();
		straightFlush = new Hand("black: JD 7D 8D 9D 10D white: 2C 3C 5H 9D 10D");
		fourOfAKind = new Hand("black: 9D 10C 10D 10H 10S white: AC 3S 4S 5S 6S");
		fullHouse = new Hand("black: QS QD JH QC JC white: 2C 3C 5S 8H JS");
		flush = new Hand("black: JC QC 4C 5C 6C white: 3S 6S 7S 9S 10S");
		straight = new Hand("black: 4C 5D 6S 7C 8H white: 10C JS QH KS AS");
		threeOfAKind = new Hand("black: 6C 7C 6D 8S 6H white: 10D JH 4H 3S 5D");
		twoPairs = new Hand("black: 2C 3C 2D 3D 4C white: 10D JH 4H 3S 5D");
		pair = new Hand("black: 4D 10H JC 3D 4S white: 10D JH 4H 3S 5D");
		twoPairVsThreeOfAKind = new Hand("black: 2C 3S 2D 3C 4H white: 6C 7C 10D 8S 9H");
		highCard = new Hand("black: 2C 10S 5D QH KD white: 4S 5C 8H KS 3H");
		g = new Game();
	}
	
	@Test
	public void returns8IfPlayerHasFourOfAKind(){
		FourOfAKind fours = new FourOfAKind();
		assertEquals(8, fours.checkFourOfAKind(fourOfAKind.getPlayer1(), ranker));
	}
	
	@Test
	public void returns0IfPlayerDoesntHaveFourOfAKind(){
		FourOfAKind fours = new FourOfAKind();
		assertEquals(0, fours.checkFourOfAKind(straightFlush.getPlayer1(), ranker));
	}
	
	@Test
	public void returns6IfPlayerHasAFlush(){
		Flush f = new Flush();
		assertEquals(6, f.checkForFlush(flush.getPlayer1(), ranker));
	}
	@Test
	public void returns0IfPlayerDoesntHaveAFlush(){
		Flush f = new Flush();
		assertEquals(0, f.checkForFlush(fullHouse.getPlayer1(), ranker));
	}
	
	@Test
	public void returns9IfPlayerHasAStraightFlush(){
		StraightFlush sf = new StraightFlush();
		assertEquals(9, sf.checkForStraightFlush(straightFlush.getPlayer1(), ranker));
	}
	
	@Test
	public void returns0IfPlayerDoesntHaveAStraightFlush(){
		StraightFlush sf = new StraightFlush();
		assertEquals(0, sf.checkForStraightFlush(fullHouse.getPlayer2(), ranker));
	}
	
	@Test 
	public void returns5IfPlayerHasAStraight(){
		Straight st = new Straight();
		assertEquals(5, st.checkForStraight(straight.getPlayer1(), ranker));
	}
	
	@Test 
	public void returns0IfPlayerDoesntHaveAStraight(){
		Straight st = new Straight();
		assertEquals(0, st.checkForStraight(fullHouse.getPlayer2(), ranker));
	}
	
	@Test
	public void returns7IfPlayerHasAFullHouse(){
		FullHouse fh = new FullHouse();
		assertEquals(7, fh.checkForFullHouse(fullHouse.getPlayer1(), ranker));
	}
	
	@Test
	public void returns0IfPlayerDoesntHaveAFullHouse(){
		FullHouse fh = new FullHouse();
		assertEquals(0, fh.checkForFullHouse(fullHouse.getPlayer2(), ranker));
	}
	
	@Test
	public void returns4IfPlayerHasThreeOfAKind(){
		ThreeOfAKind threes = new ThreeOfAKind();
		assertEquals(4, threes.checkForThreeOfAKind(threeOfAKind.getPlayer1(), ranker));
	}
	
	@Test
	public void returns0IfPlayerDoesntHaveThreeOfAKind(){
		ThreeOfAKind threes = new ThreeOfAKind();
		assertEquals(0, threes.checkForThreeOfAKind(threeOfAKind.getPlayer2(), ranker));
	}
	
	@Test 
	public void returns3IfPlayerHasTwoPairs(){
		TwoPair tp = new TwoPair();
		assertEquals(3, tp.checkForTwoPairs(twoPairs.getPlayer1(), ranker));
	}
	
	@Test 
	public void returns0IfPlayerDoesntHaveTwoPairs(){
		TwoPair tp = new TwoPair();
		assertEquals(0, tp.checkForTwoPairs(twoPairs.getPlayer2(), ranker));
	}
	
	@Test
	public void returns2IfPlayer1HasOnePair(){
		Pair p = new Pair();
		assertEquals(2, p.checkForPair(pair.getPlayer1(), ranker));
	}
	
	@Test
	public void returns0IfPlayer2DoesntHaveOnePair(){
		Pair p = new Pair();
		assertEquals(0, p.checkForPair(pair.getPlayer2(), ranker));
	}
	
	@Test
	public void whiteStraightBeatsBlackTwoPair(){
		Hand hand = new Hand("black: 2C 3S 2D 3C 4H white: 6C 7C 10D 8S 9H");
		assertEquals("white:", g.determineWinner(hand));
	}
	
	/*
	@Test
	public void determineWinnerOfPairVsPair(){
		Hand hand = new Hand("black: 5C 8D 5S JH QS white: 6S 6C 8S 9H 3S");
		assertEquals("white:", g.determineWinner(hand));
	}
	*/
	
	@Test
	public void whiteHighCardKBeatsBlackHighCardQ(){
		Hand hand = new Hand("black: 4S 7S 8C 9D QH white: KH 7S 6D 5C 4S");
		assertEquals("white:", g.determineWinner(hand));
	}
	
	@Test 
	public void whiteThreeOfAKindBeatsBlackThreeOfAKind(){
		Hand hand = new Hand("black: 3S 7D 3C 8S 3D white: 4S 5S 7D 4C 4D");
		assertEquals("white:", g.determineWinner(hand));
	}
	
	@Test
	public void blackStraightFlushBeatsWhiteFlush(){
		Hand hand = new Hand("black: 10H JH QH KH AH white: 4S 8S 9S 10S 2S");
		assertEquals("black:", g.determineWinner(hand));
	}
	
	@Test
	public void whiteFourOfAKindBeatsBlackFullHouse(){
		Hand hand = new Hand("black: 8S 7C 8D 8H 7H white: 10S JS 10D 10H 10C");
		assertEquals("white:", g.determineWinner(hand));
	}
	
	@Test
	public void whiteFlushHighCardBeatsBlackFlushHighCard(){
		Hand hand = new Hand("black: 10S 7S 8S 3S 4S white: 5D 3D JD AD QD");
		assertEquals("white:", g.determineWinner(hand));
	}
	
}
