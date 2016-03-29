package core.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import core.domain.game.Card;
import core.domain.game.Hand;
import core.domain.game.HandRank;

public class Algorithms {
	
	private List<Card> _hearts;
	private List<Card> _spades;
	private List<Card> _diamonds;
	private List<Card> _clubs;
	private int[] _amountOfEachCard;
	private List<Card> _listOfCards;
	
	public Algorithms() {
		_hearts = new LinkedList<Card>();
		_spades = new LinkedList<Card>();
		_diamonds = new LinkedList<Card>();
		_clubs = new LinkedList<Card>();
		_amountOfEachCard = new int[13];
		_listOfCards = new ArrayList<Card>();
	}

	public void initListOfCards(Hand hand) {
		_listOfCards.clear();
		_listOfCards.addAll(Arrays.asList(hand.getTableCards()));
		_listOfCards.addAll(Arrays.asList(hand.getPlayerCards()));
		_listOfCards.sort((o1,o2) -> o2.compareTo(o1));
	}
	
	public void evaluateHand(Hand hand) {
		initListOfCards(hand);

		if (isFlush(hand)) {
			if (isStraight(hand, true)) {
				if (hasAs(hand)) {
					hand.setHandRank(HandRank.ROYAL_FLUSH);
				}
				else {
					hand.setHandRank(HandRank.STRAIGHT_FLUSH);
				}
			}
			else {
				hand.setHandRank(HandRank.FLUSH);
			}
		}
		else if (isStraight(hand, false)) {
			hand.setHandRank(HandRank.STRAIGHT);
		}
		else if (hasFourOfaKind(hand)) {
			hand.setHandRank(HandRank.FOUR_OF_A_KIND);
		}
		else if (hasFullHouse(hand)) {
			hand.setHandRank(HandRank.FULL_HOUSE);
		}
		else if (hasThreeOfaKind(hand)) {
			hand.setHandRank(HandRank.THREE_OF_A_KIND);
		}
		else if (hasTwoPair(hand)) {
			hand.setHandRank(HandRank.TWO_PAIR);
		}
		else if (hasOnePair(hand)) {
			hand.setHandRank(HandRank.ONE_PAIR);
		}
		else {
			List<Card> aux = new ArrayList<Card>(_listOfCards);
			aux.remove(6);
			aux.remove(5);
			Card highCard = aux.remove(0);
			hand.setHandRank(HandRank.HIGH_CARD);
			hand.setHighCard(highCard);
			hand.setKickers(aux);
		}
	}
	
	public void countingCards(Hand hand) {
		for (int i = 0; i < 13; i++) {
			_amountOfEachCard[i] = 0;
		}

		for (Card card : _listOfCards) {
			_amountOfEachCard[card.getNumber()-1]++;
		}
	}
	
	public int[] getAmountOfEachCard() {
		return _amountOfEachCard;
	}
	
	public boolean hasFullHouse(Hand hand) {
		countingCards(hand);

		int highPairCardValue = -1;
		int highTOAKCardValue = -1;
		boolean outPair = false;
		boolean outTOAK = false;

		for (int i = 0; i < 13; i++) {
			if (_amountOfEachCard[i] == 2 && highPairCardValue != 1) {
				highPairCardValue = i+1;
				outPair = true;
			}
			else if (_amountOfEachCard[i] == 3 && highTOAKCardValue != 1) {
				highTOAKCardValue = i+1;
				outTOAK = true;
			}
		}
		
		if (outPair && outTOAK) {
			List<Card> aux = new ArrayList<Card>();
			Card highPairCard = null;
			Card highTOAKCard = null;
			for (int j = 0; j < 7; j++) {
				if (_listOfCards.get(j).getNumber() == highPairCardValue) {
					highPairCard = _listOfCards.get(j);
				}
				else if (_listOfCards.get(j).getNumber() == highTOAKCardValue) {
					highTOAKCard = _listOfCards.get(j);
				}
			}
			
			aux.add(highPairCard);
			hand.setHighCard(highTOAKCard);
			hand.setKickers(aux);
		}

		return outPair && outTOAK;
	}
	
	public boolean hasTwoPair(Hand hand) {
		countingCards(hand);

		int highPairCardValue = -1;
		int secondHighPairCardValue = -1;
		boolean out1 = false;
		boolean out2 = false;

		for (int i = 0; i < 13; i++) {
			if (_amountOfEachCard[i] == 2) {
				if (highPairCardValue==-1) {
					highPairCardValue = i+1;
					out1 = true;
				}
				else {
					if (highPairCardValue!=1) {
						secondHighPairCardValue = highPairCardValue;
						highPairCardValue = i+1;
					}
					else {
						secondHighPairCardValue = i+1;
					}
					out2 = true;
				}
			}
		}
		
		if (out1 && out2) {
			List<Card> aux = new ArrayList<Card>(_listOfCards);
			Card highCard = null;
			Card secondHighCard = null;
			int size = 7;
			for (int j = 0; j < size; j++) {
				if (_listOfCards.get(j).getNumber() == highPairCardValue) {
					highCard = aux.remove(j);
					size--;
				}
				else if (_listOfCards.get(j).getNumber() == secondHighPairCardValue) {
					secondHighCard = aux.remove(j);
					size--;
				}
			}
			
			aux.remove(2);
			aux.remove(1);
			aux.add(0,secondHighCard);
			hand.setHighCard(highCard);
			hand.setKickers(aux);
		}

		return out1 && out2;
	}

	public boolean hasOnePair(Hand hand) {
		countingCards(hand);
		
		int highPairCardValue = -1;
		boolean out = false;
		
		for (int i = 0; i < 13; i++) {
			if (_amountOfEachCard[i] == 2) {
				highPairCardValue = i+1;
				out = true;
				if (i==0) i=14;
			}
		}
		
		if (out) {
			List<Card> aux = new ArrayList<Card>(_listOfCards);
			Card highCard = null;
			int size = 7;
			for (int j = 0; j < size; j++) {
				if (_listOfCards.get(j).getNumber() == highPairCardValue) {
					highCard = aux.remove(j);
					size--;
				}
			}
			
			aux.remove(4);
			aux.remove(3);
			hand.setHighCard(highCard);
			hand.setKickers(aux);
		}
		
		return out;
	}

	public boolean hasThreeOfaKind(Hand hand) {
		countingCards(hand);

		int highTOAKCardValue = -1;
		boolean out = false;

		for (int i = 0; i < 13; i++) {
			if (_amountOfEachCard[i] == 3) {
				highTOAKCardValue = i+1;
				out = true;
				if (i==0) i=14;
			}
		}

		if (out) {
			List<Card> aux = new ArrayList<Card>(_listOfCards);
			Card highCard = null;
			int size = 7;
			for (int j = 0; j < size; j++) {
				if (_listOfCards.get(j).getNumber() == highTOAKCardValue) {
					highCard = aux.remove(j);
					size--;
				}
			}
			
			aux.remove(3);
			aux.remove(2);
			hand.setHighCard(highCard);
			hand.setKickers(aux);
		}

		return out;
	}

	public boolean hasFourOfaKind(Hand hand) {
		countingCards(hand);

		List<Card> aux = new ArrayList<Card>(_listOfCards);
		Card highCard = null;
		for (int i = 0; i < 13; i++) {
			if (_amountOfEachCard[i] == 4) {
				
				int size = 7;
				for (int j = 0; j < size; j++) {
					if (_listOfCards.get(j).getNumber() == i+1) {
						highCard = aux.remove(j);
						size--;
					}
				}
				
				aux.remove(2);
				aux.remove(1);
				hand.setHighCard(highCard);
				hand.setKickers(aux);
				return true;
			}
		}
		
		return false;
	}

	public boolean hasAs(Hand hand) {
		for (Card card : _listOfCards) {
			if (card.getNumber() == 1) {	
				hand.setHighCard(card);
				return true;
			}
		}
		return false;
	}

	public boolean isStraight(Hand hand, boolean isFlush) {
		Card highCard = null;
		boolean out = true;
		
		for (int j = 0; j < 3; j++) {
			highCard = _listOfCards.get(j);
			Card prev = highCard;
			
			for (int i = j+1; i < 7; i++) {
				Card other = _listOfCards.get(i);
				if (prev.isPredecessor(other)){
					if (isFlush) {
						if (!highCard.getSuit().equals(other.getSuit())){
							out = false;
						}
						else {
							out = true;
						}
					}
				}
				else {
					out = false;
					i = 8;
				}
				//System.out.println(prev.toString() + "|" + other.toString() + "|" + out);
				prev = other;
			}
			//System.out.println("");
			if (out) {
				hand.setHighCard(highCard);
				j=4;
			}
		}
		
		return out;
	}

	public boolean isFlush(Hand hand) {
		boolean out = false;
		_hearts.clear();
		_spades.clear();
		_diamonds.clear();
		_clubs.clear();
		
		for (Card card : _listOfCards) {
			
			switch (card.getSuit()) {
			case HEARTS:
				_hearts.add(card);
				break;
			case SPADES:
				_spades.add(card);
				break;
			case DIAMONDS:
				_diamonds.add(card);
				break;
			case CLUBS:
				_clubs.add(card);
				break;
			default:
				break;
			}
		}
		
		if (_hearts.size() >= 5) {
			_hearts.sort((o1,o2) -> o2.compareTo(o1));
			hand.setHighCard(_hearts.remove(0));
			hand.setKickers(_hearts);
			out = true;
		}
		else if (_spades.size() >= 5) {
			_spades.sort((o1,o2) -> o2.compareTo(o1));
			hand.setHighCard(_spades.remove(0));
			hand.setKickers(_spades);
			out = true;
		}
		else if (_diamonds.size() >= 5) {
			_diamonds.sort((o1,o2) -> o2.compareTo(o1));
			hand.setHighCard(_diamonds.remove(0));
			hand.setKickers(_diamonds);
			out = true;
		}
		else if (_clubs.size() >= 5) {
			_clubs.sort((o1,o2) -> o2.compareTo(o1));
			hand.setHighCard(_clubs.remove(0));
			hand.setKickers(_clubs);
			out = true;
		}
		return out;
	}
}
