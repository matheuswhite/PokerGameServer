package core.domain.game;

public class ExtendedHand {

	private Card[] _cards;
	private Card[] _highCards;
	private HandRank _handRank;
	
	public ExtendedHand(Card[] tableCards, Card[] playerCards) {
		if (tableCards.length != 5 || playerCards.length != 2)
			throw new IllegalArgumentException("Number of cards invalid");

		_cards = new Card[7];
		System.arraycopy(tableCards, 0, _cards, 0, 5);
		System.arraycopy(playerCards, 0, _cards, 5, 2);
	}
	
	public Card[] getCards() {
		return _cards;
	}
	
	public Card[] getHighCards() {
		return _highCards;
	}
	
	public void setHighCards(Card[] highCards) {
		if (highCards.length > 7)
			throw new IllegalArgumentException("Number of cards invalid");
		
		_highCards = highCards;
	}
	
	public HandRank getHandRank() {
		return _handRank;
	}
	
	public void setHandRank(HandRank handRank) {
		_handRank = handRank;
	}
}
