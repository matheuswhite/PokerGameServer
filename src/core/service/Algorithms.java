package core.service;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import core.domain.game.Card;
import core.domain.game.ExtendedHand;
import core.domain.game.HandRank;

public class Algorithms {
	
	private List<Card> _hearts;
	private List<Card> _spades;
	private List<Card> _diamonds;
	private List<Card> _clubs;
	private Map<Integer, Integer> _amountOfEachCard;
	
	public Algorithms() {
		_hearts = new LinkedList<Card>();
		_spades = new LinkedList<Card>();
		_diamonds = new LinkedList<Card>();
		_clubs = new LinkedList<Card>();
		_amountOfEachCard = new HashMap<Integer, Integer>();
	}
	
	public HandRank checkHandRank(ExtendedHand hand) {
		
		if (isFlush(hand)) {
			if (isStraight(hand)) {
				if (hasAs(hand)) {
					return HandRank.ROYAL_FLUSH;
				}
				else {
					return HandRank.STRAIGHT_FLUSH;
				}
			}
			else {
				return HandRank.FLUSH;
			}
		}
		else {
			if (isStraight(hand)) {
				return HandRank.STRAIGHT;
			}
			else {
				if (hasFourOfaKind(hand)) {
					return HandRank.FOUR_OF_A_KIND;
				}
				else {
					if (hasThreeOfaKind(hand)) {
						if (hasOnePair(hand)) {
							return HandRank.FULL_HOUSE;
						}
						else {
							return HandRank.THREE_OF_A_KIND;
						}
					}
					else {
						if (hasTwoPair(hand)) {
							return HandRank.TWO_PAIR;
						}
						else {
							if (hasOnePair(hand)) {
								return HandRank.ONE_PAIR;
							}
						}
					}
				}
			}
		}
		
		return HandRank.HIGH_CARD;
	}
	
	public void countingCards(ExtendedHand hand) {
		_amountOfEachCard.clear();
		for (int i = 1; i <= 13; i++) {
			_amountOfEachCard.put(i, 0);
		}
		
		for (Card card : hand.getCards()) {
			Integer amount = _amountOfEachCard.get(card.getNumber());
			amount++;
		}
	}
	
	public boolean hasTwoPair(ExtendedHand hand) {
		countingCards(hand);

		int highPairCardValue = 0;
		int secondHighPairCardValue = 0;
		boolean out = false;

		for (int i = 1; i <= 13; i++) {
			if (_amountOfEachCard.get(i) == 2) {
				if (highPairCardValue != 0) {
					secondHighPairCardValue = highPairCardValue;
					out = true;
				}
				highPairCardValue = i;
			}
		}

		if (out) {
			Card[] highCards = new Card[7];

			int j = 0;
			int k = 2;
			int l = 4;
			for (int i = 0; i < 7; i++) {
				Card card = hand.getCards()[i];
				if (card.getNumber() == highPairCardValue) {
					highCards[j] = card;
					j++;
				}
				else if (card.getNumber() == secondHighPairCardValue) {
					highCards[k] = card;
					k++;
				}
				else {
					highCards[l] = card;
					l++;
				}
			}

			hand.setHighCards(highCards);
		}

		return out;
	}

	public boolean hasOnePair(ExtendedHand hand) {
		countingCards(hand);
		
		int highPairCardValue = 0;
		boolean out = false;
		
		for (int i = 1; i <= 13; i++) {
			if (_amountOfEachCard.get(i) == 2) {
				highPairCardValue = i;
				out = true;
			}
		}
		
		if (out) {
			Card[] highCards = new Card[7];
			
			int j = 0;
			int k = 2;
			for (int i = 0; i < 7; i++) {
				Card card = hand.getCards()[i];
				if (card.getNumber() == highPairCardValue) {
					highCards[j] = card;
					j++;
				}
				else {
					highCards[k] = card;
					k++;
				}
			}
			
			hand.setHighCards(highCards);
		}
		
		return out;
	}

	public boolean hasThreeOfaKind(ExtendedHand hand) {
		countingCards(hand);

		int highTOAKCardValue = 0;
		boolean out = false;

		for (int i = 1; i <= 13; i++) {
			if (_amountOfEachCard.get(i) == 3) {
				highTOAKCardValue = i;
				out = true;
			}
		}

		if (out) {
			Card[] highCards = new Card[7];

			int j = 0;
			int k = 3;
			for (int i = 0; i < 7; i++) {
				Card card = hand.getCards()[i];
				if (card.getNumber() == highTOAKCardValue) {
					highCards[j] = card;
					j++;
				}
				else {
					highCards[k] = card;
					k++;
				}
			}
			
			hand.setHighCards(highCards);
		}

		return out;
	}

	public boolean hasFourOfaKind(ExtendedHand hand) {
		countingCards(hand);

		int highFOAKCardValue = 0;
		boolean out = false;

		for (int i = 1; i <= 13; i++) {
			if (_amountOfEachCard.get(i) == 4) {
				highFOAKCardValue = i;
				out = true;
				i = 14;
			}
		}

		if (out) {
			Card[] highCards = new Card[7];

			int j = 0;
			int k = 4;
			for (int i = 0; i < 7; i++) {
				Card card = hand.getCards()[i];
				if (card.getNumber() == highFOAKCardValue) {
					highCards[j] = card;
					j++;
				}
				else {
					highCards[k] = card;
					k++;
				}
			}
			
			hand.setHighCards(highCards);
		}

		return out;
	}

	public boolean hasAs(ExtendedHand hand) {
		for (Card card : hand.getCards()) {
			if (card.getNumber() == 1) {	
				
				Arrays.sort(hand.getCards(), Collections.reverseOrder());
				hand.setHighCards(hand.getCards());
				return true;
			}
		}
		return false;
	}

	public boolean isStraight(ExtendedHand hand) {
		
		
		return false;
	}

	public boolean isFlush(ExtendedHand hand) {
		boolean out = false;
		_hearts.clear();
		_spades.clear();
		_diamonds.clear();
		_clubs.clear();
		
		for (Card card : hand.getCards()) {
			
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
			Collections.sort(_hearts, Collections.reverseOrder());
			hand.setHighCards((Card[])_hearts.toArray());
			out = true;
		}
		else if (_spades.size() >= 5) {
			Collections.sort(_spades, Collections.reverseOrder());
			hand.setHighCards((Card[])_spades.toArray());
			out = true;
		}
		else if (_diamonds.size() >= 5) {
			Collections.sort(_diamonds, Collections.reverseOrder());
			hand.setHighCards((Card[])_diamonds.toArray());
			out = true;
		}
		else if (_clubs.size() >= 5) {
			Collections.sort(_clubs, Collections.reverseOrder());
			hand.setHighCards((Card[])_clubs.toArray());
			out = true;
		}
		return out;
	}
}
