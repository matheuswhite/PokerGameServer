package core.domain.game;

public class GameInfo {
	
	private GamePhase _currentGamePhase;
	private long _currentTurnPlayerId;
	private long _smallBlindPlayerId;
	private long _bigBlindPlayerId;
	private Card[] _cardsInTable;
	private int _numCardsInTable;
	private Money _smallBlindValue;
	
	public GameInfo() {
		_cardsInTable = new Card[5];
		_numCardsInTable = 0;
	}
	
	public GamePhase get_currentGamePhase() {
		return _currentGamePhase;
	}
	public void set_currentGamePhase(GamePhase _currentGamePhase) {
		this._currentGamePhase = _currentGamePhase;
	}
	public long get_currentTurnPlayerId() {
		return _currentTurnPlayerId;
	}
	public void set_currentTurnPlayerId(long _currentTurnPlayerId) {
		this._currentTurnPlayerId = _currentTurnPlayerId;
	}
	public long get_smallBlindPlayerId() {
		return _smallBlindPlayerId;
	}
	public void set_smallBlindPlayerId(long _smallBlindPlayerId) {
		this._smallBlindPlayerId = _smallBlindPlayerId;
	}
	public long get_bigBlindPlayerId() {
		return _bigBlindPlayerId;
	}
	public void set_bigBlindPlayerId(long _bigBlindPlayerId) {
		this._bigBlindPlayerId = _bigBlindPlayerId;
	}
	public Money get_smallBlindValue() {
		return _smallBlindValue;
	}
	public void set_smallBlindValue(Money _smallBlindValue) {
		this._smallBlindValue = _smallBlindValue;
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
