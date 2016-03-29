package test;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import core.domain.game.Card;
import core.domain.game.Hand;
import core.domain.game.HandRank;
import core.domain.game.Suit;
import core.service.Algorithms;

public class AlgorithmTest {
	
	private Algorithms _algorithms;
	private Card[] _playerHand;
	private Card[] _tableHand;
	
	@Before
	public void initVariables() {
		_algorithms = new Algorithms();
		_playerHand = new Card[2];
		_tableHand = new Card[5];
	}
	
	@Test
	public void should_RoyalFlush() {
		_playerHand[0] = new Card(Suit.CLUBS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 1);
		
		_tableHand[0] = new Card(Suit.CLUBS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 13);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.ROYAL_FLUSH, hand.getHandRank());
		assertEquals(new Card(Suit.CLUBS, 1), hand.getHighCard());
	}
	
	@Test
	public void should_StraightFlush() {
		_playerHand[0] = new Card(Suit.CLUBS, 8);
		_playerHand[1] = new Card(Suit.CLUBS, 7);
		
		_tableHand[0] = new Card(Suit.CLUBS, 9);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 6);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.STRAIGHT_FLUSH, hand.getHandRank());
		assertEquals(new Card(Suit.CLUBS, 10), hand.getHighCard());
	}
	
	@Test
	public void should_FourOfaKind() {
		_playerHand[0] = new Card(Suit.CLUBS, 11);
		_playerHand[1] = new Card(Suit.DIAMONDS, 7);
		
		_tableHand[0] = new Card(Suit.CLUBS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 7);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.HEARTS, 7);
		_tableHand[4] = new Card(Suit.CLUBS, 7);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.FOUR_OF_A_KIND, hand.getHandRank());
		assertEquals(7, hand.getHighCard().getNumber());
		assertEquals(new Card(Suit.CLUBS, 12), hand.getKickers().get(0));
	}
	
	@Test
	public void should_FullHouse() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 1);
		
		_tableHand[0] = new Card(Suit.HEARTS, 2);
		_tableHand[1] = new Card(Suit.SPADES, 4);
		_tableHand[2] = new Card(Suit.DIAMONDS, 4);
		_tableHand[3] = new Card(Suit.CLUBS, 2);
		_tableHand[4] = new Card(Suit.CLUBS, 13);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.FULL_HOUSE, hand.getHandRank());
		assertEquals(2, hand.getHighCard().getNumber());
		assertEquals(4, hand.getKickers().get(0).getNumber());
	}
	
	@Test
	public void should_Flush() {
		_playerHand[0] = new Card(Suit.CLUBS, 9);
		_playerHand[1] = new Card(Suit.CLUBS, 3);
		
		_tableHand[0] = new Card(Suit.CLUBS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 7);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.FLUSH, hand.getHandRank());
		assertEquals(new Card(Suit.CLUBS, 12), hand.getHighCard());
		assertEquals(new Card(Suit.CLUBS, 10), hand.getKickers().get(0));
		assertEquals(new Card(Suit.CLUBS, 9), hand.getKickers().get(1));
		assertEquals(new Card(Suit.CLUBS, 7), hand.getKickers().get(2));
		assertEquals(new Card(Suit.CLUBS, 3), hand.getKickers().get(3));
	}
	
	@Test
	public void should_Straight() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 4);
		
		_tableHand[0] = new Card(Suit.CLUBS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 8);
		_tableHand[3] = new Card(Suit.HEARTS, 10);
		_tableHand[4] = new Card(Suit.SPADES, 9);
		
		Hand hand = new Hand(_tableHand, _playerHand);

		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.STRAIGHT, hand.getHandRank());
		assertEquals(new Card(Suit.CLUBS, 12), hand.getHighCard());
	}
	
	@Test
	public void should_ThreeOfaKind() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 4);
		
		_tableHand[0] = new Card(Suit.CLUBS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.HEARTS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 2);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.THREE_OF_A_KIND, hand.getHandRank());
		assertEquals(2, hand.getHighCard().getNumber());
		assertEquals(new Card(Suit.CLUBS, 12), hand.getKickers().get(0));
		assertEquals(new Card(Suit.DIAMONDS, 11), hand.getKickers().get(1));
	}
	
	@Test
	public void should_TwoPair() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 4);
		
		_tableHand[0] = new Card(Suit.HEARTS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.HEARTS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 11);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.TWO_PAIR, hand.getHandRank());
		assertEquals(11, hand.getHighCard().getNumber());
		assertEquals(2, hand.getKickers().get(0).getNumber());
		assertEquals(new Card(Suit.HEARTS, 12), hand.getKickers().get(1));
	}
	
	@Test
	public void should_OnePair() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 4);
		
		_tableHand[0] = new Card(Suit.HEARTS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.HEARTS, 10);
		_tableHand[4] = new Card(Suit.CLUBS, 7);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.ONE_PAIR, hand.getHandRank());
		assertEquals(2, hand.getHighCard().getNumber());
		assertEquals(new Card(Suit.HEARTS, 12), hand.getKickers().get(0));
		assertEquals(new Card(Suit.DIAMONDS, 11), hand.getKickers().get(1));
		assertEquals(new Card(Suit.HEARTS, 10), hand.getKickers().get(2));
	}
	
	@Test
	public void should_HighCard() {
		_playerHand[0] = new Card(Suit.DIAMONDS, 11);
		_playerHand[1] = new Card(Suit.CLUBS, 4);
		
		_tableHand[0] = new Card(Suit.HEARTS, 12);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 9);
		_tableHand[3] = new Card(Suit.HEARTS, 5);
		_tableHand[4] = new Card(Suit.CLUBS, 7);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.evaluateHand(hand);
		
		assertEquals(HandRank.HIGH_CARD, hand.getHandRank());
		assertEquals(new Card(Suit.HEARTS, 12), hand.getHighCard().getNumber());
		assertEquals(new Card(Suit.DIAMONDS, 11), hand.getKickers().get(0));
		assertEquals(new Card(Suit.DIAMONDS, 9), hand.getKickers().get(1));
		assertEquals(new Card(Suit.CLUBS, 7), hand.getKickers().get(2));
		assertEquals(new Card(Suit.HEARTS, 5), hand.getKickers().get(3));
	}

	@Test
	public void testCountingCards() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 5);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		_algorithms.countingCards(hand);
		
		assertEquals(3, _algorithms.getAmountOfEachCard()[1]);
		assertEquals(2, _algorithms.getAmountOfEachCard()[4]);
	}

	@Test
	public void testHasFullHouse() {
		_playerHand[0] = new Card(Suit.CLUBS, 5);
		_playerHand[1] = new Card(Suit.CLUBS, 2);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 5);
		_tableHand[2] = new Card(Suit.DIAMONDS, 5);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 2);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasFullHouse(hand));
	}
	
	@Test
	public void testHasTwoPair() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 8);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 5);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasTwoPair(hand));
	}

	@Test
	public void testHasOnePair() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 13);
		_tableHand[2] = new Card(Suit.DIAMONDS, 8);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 5);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasOnePair(hand));
	}

	@Test
	public void testHasThreeOfaKind() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 8);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasThreeOfaKind(hand));
	}

	@Test
	public void testHasFourOfaKind() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 2);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 2);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasFourOfaKind(hand));
	}

	@Test
	public void testHasAs() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.SPADES, 2);
		_tableHand[2] = new Card(Suit.DIAMONDS, 1);
		_tableHand[3] = new Card(Suit.CLUBS, 11);
		_tableHand[4] = new Card(Suit.HEARTS, 5);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.hasAs(hand));
	}

	@Test
	public void testIsStraight() {
		_playerHand[0] = new Card(Suit.CLUBS, 3);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 7);
		_tableHand[1] = new Card(Suit.CLUBS, 4);
		_tableHand[2] = new Card(Suit.DIAMONDS, 4);
		_tableHand[3] = new Card(Suit.HEARTS, 3);
		_tableHand[4] = new Card(Suit.CLUBS, 6);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.isStraight(hand,false));
		assertTrue(_algorithms.isStraight(hand,true));
	}

	@Test
	public void testIsFlush() {
		_playerHand[0] = new Card(Suit.CLUBS, 2);
		_playerHand[1] = new Card(Suit.CLUBS, 5);
		
		_tableHand[0] = new Card(Suit.CLUBS, 3);
		_tableHand[1] = new Card(Suit.CLUBS, 4);
		_tableHand[2] = new Card(Suit.DIAMONDS, 4);
		_tableHand[3] = new Card(Suit.HEARTS, 11);
		_tableHand[4] = new Card(Suit.CLUBS, 6);
		
		Hand hand = new Hand(_tableHand, _playerHand);
		
		_algorithms.initListOfCards(hand);
		assertTrue(_algorithms.isFlush(hand));
	}

}
