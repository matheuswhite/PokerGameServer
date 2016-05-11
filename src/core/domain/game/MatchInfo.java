package core.domain.game;

public class MatchInfo{
	
	private MatchPhase _currentMatchPhase;
	private Long _currentTurnPlayerId;
	private Long _smallBlindPlayerId;
	private Long _bigBlindPlayerId;
	private Long _dealerPlayerId;
	private Card[] _cardsInTable;
	private int _numCardsInTable;
	private Money _smallBlindValue;
	private Money _minimumBuyIn;
	private Money _potValue;
	private Money _higherCurrentBet;
	
	public MatchInfo() {
		_currentMatchPhase = MatchPhase.PRE_FLOP;
		_currentTurnPlayerId = null;
		_smallBlindPlayerId = null;
		_bigBlindPlayerId = null;
		_dealerPlayerId = null;
		_cardsInTable = new Card[5];
		_numCardsInTable = 0;
		_smallBlindValue = new Money();
		_minimumBuyIn = new Money();
		_potValue = new Money();
		_higherCurrentBet = new Money();
	}
	
	public MatchInfo(Money smallBlindValue, Money minimumBuyIn) {
		this();
		_smallBlindValue = smallBlindValue;
		_minimumBuyIn = minimumBuyIn;
	}
	
	public MatchPhase nextPhase(MatchPhase actualPhase){
		if(actualPhase == MatchPhase.PRE_FLOP)
			return MatchPhase.FLOP;
		if(actualPhase == MatchPhase.FLOP)
			return MatchPhase.TURN;
		if(actualPhase == MatchPhase.TURN)
			return MatchPhase.RIVER;
		return null;
	}
	
	public void setPhase(MatchPhase phase){
		_currentMatchPhase = phase;
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
	
	public long getDealerPlayerId() {
		return _dealerPlayerId;
	}
	public void setDealerPlayerId(long dealerPlayerId) {
		_dealerPlayerId = dealerPlayerId;
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
		if(value.getValue() > _higherCurrentBet.getValue())
			_higherCurrentBet = value;
	}
	public void clearPot() {
		_potValue = new Money();
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
	
	public Money getHigherCurrentBet() {
		return _higherCurrentBet;
	}
	public void setHigherCurrentBet(Money higherCurrentBet) {
		_higherCurrentBet = higherCurrentBet;
	}
}
