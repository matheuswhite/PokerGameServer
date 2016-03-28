package core.domain.game;

import java.util.LinkedList;
import java.util.List;

public class Hand {

	private Card[] _tableCards;
	private Card[] _playerCards;
	private Card _highCard;
	private List<Card> _kickers;
	private HandRank _handRank;
	
	public Hand(Card[] tableCards, Card[] playerCards) {
		if (tableCards.length != 5 || playerCards.length != 2)
			throw new IllegalArgumentException("Number of cards invalid");

		_tableCards = tableCards;
		_playerCards = playerCards;
		_kickers = new LinkedList<Card>();
		//System.arraycopy(tableCards, 0, _allCards, 0, 5);
		//System.arraycopy(playerCards, 0, _allCards, 5, 2);
	}
	
	public Card[] getTableCards() {
		return _tableCards;
	}
	
	public Card[] getPlayerCards() {
		return _playerCards;
	}
	
	public Card getHighCard() {
		return _highCard;
	}
	
	public List<Card> getKickers() {
		return _kickers;
	}
	
	public HandRank getHandRank() {
		return _handRank;
	}
	
	public void setHighCard(Card highCard) {
		_highCard = highCard;
	}
	
	public void setKickers(List<Card> kickers) {
		if (kickers.size() > 4)
			throw new IllegalArgumentException("Number of cards invalid");
		
		_kickers = kickers;
	}
	
	public void setHandRank(HandRank handRank) {
		_handRank = handRank;
	}
}
