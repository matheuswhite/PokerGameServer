package core.domain.game;

import core.service.PrefixMultiplier;

public class MatchInfo{
	
	private MatchPhase _currentMatchPhase;
	private PlayerInfo _currentTurnPlayer;
	private PlayerInfo _smallBlindPlayer;
	private PlayerInfo _bigBlindPlayer;
	private Card[] _cardsInTable;
	private int _numCardsInTable;
	private Money _smallBlindValue;
	private Money _minimumBuyIn;
	private Money _potValue;
	
	public MatchInfo() {
		_currentMatchPhase = MatchPhase.PRE_FLOP;
		_currentTurnPlayer = null;
		_smallBlindPlayer = null;
		_bigBlindPlayer = null;
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
	
	public PlayerInfo getCurrentTurnPlayer() {
		return _currentTurnPlayer;
	}
	public void setCurrentTurnPlayer(PlayerInfo currentTurnPlayer) {
		_currentTurnPlayer = currentTurnPlayer;
	}
	
	public PlayerInfo getSmallBlindPlayer() {
		return _smallBlindPlayer;
	}
	public void setSmallBlindPlayer(PlayerInfo smallBlindPlayer) {
		_smallBlindPlayer = smallBlindPlayer;
	}
	
	public PlayerInfo getBigBlindPlayer() {
		return _bigBlindPlayer;
	}
	public void setBigBlindPlayer(PlayerInfo bigBlindPlayer) {
		_bigBlindPlayer = bigBlindPlayer;
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
