package core.domain.game;

public class GameInfo {
	
	private GamePhase _currentGamePhase;
	private long _currentTurnPlayerId;
	private long _smallBlindPlayerId;
	private long _bigBlindPlayerId;
	private Card[] _cardsInTable;
	private Money _smallBlindValue;
}
