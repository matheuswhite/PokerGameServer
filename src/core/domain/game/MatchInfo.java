package core.domain.game;

import core.service.PrefixMultiplier;

public class MatchInfo{
	
	private MatchPhase _currentMatchPhase;
	private Long _currentTurnPlayerId;
	private Long _smallBlindPlayerId;
	private Long _bigBlindPlayerId;
	private Card[] _cardsInTable;
	private int _numCardsInTable;
	private Money _smallBlindValue;
	private Money _minimumBuyIn;
	private Money _potValue;
	
	public MatchInfo() {
		_currentMatchPhase = MatchPhase.PRE_FLOP;
		_currentTurnPlayerId = null;
		_smallBlindPlayerId = null;
		_bigBlindPlayerId = null;
		_cardsInTable = new Card[5];
		_numCardsInTable = 0;
		_smallBlindValue = new Money();
		_minimumBuyIn = new Money();
		_potValue = new Money();
	}
	
	public MatchInfo(Money smallBlindValue, Money minimumBuyIn) {
		this();
		_smallBlindValue = smallBlindValue;
		_minimumBuyIn = minimumBuyIn;
	}
	
	public MatchPhase getCurrentMatchPhase() {
		return _currentMatchPhase;
	}
	public void setCurrentMatchPhase(MatchPhase currentMatchPhase) {
		_currentMatchPhase = currentMatchPhase;
	}
	
	public long getCurrentTurnPlayerId() {
		return _currentTurnPlayerId;
	}
	public void setCurrentTurnPlayerId(long currentTurnPlayerId) {
		_currentTurnPlayerId = currentTurnPlayerId;
	}
	
	public long getSmallBlindPlayerId() {
		return _smallBlindPlayerId;
	}
	public void setSmallBlindPlayerId(long smallBlindPlayerId) {
		_smallBlindPlayerId = smallBlindPlayerId;
	}
	
	public long getBigBlindPlayerId() {
		return _bigBlindPlayerId;
	}
	public void setBigBlindPlayerId(long bigBlindPlayerId) {
		_bigBlindPlayerId = bigBlindPlayerId;
	}
	
	public Money getSmallBlindValue() {
		return _smallBlindValue;
	}
	
	public Money getMinimumBuyIn() {
		return _minimumBuyIn;
	}
	
	public Money getPotValue() {
		return _potValue;
	}
	public void increasePotValue(Money value) {
		_potValue.addMoney(value);
	}
	public void clearPot() {
		_potValue = new Money(0, PrefixMultiplier.NONE);
	}
	
	public Card[] getCardsInTable() {
		return _cardsInTable;
	}
	public void putCardInTable(Card card) throws Exception {
		if (_numCardsInTable >= 5)
			throw new Exception("Num cards max in table");
		_cardsInTable[_numCardsInTable] = card;
		_numCardsInTable++;
	}
	public void clearCardsInTable() {
		for (int i = 0; i < 5; i++) {
			_cardsInTable[i] = null;
		}
	}
}
