package core.domain.game;

public enum MatchPhase {

	WAIT_PLAYERS,
	PRE_FLOP,
	FLOP,
	TURN,
	RIVER,
	SHOWDOWN;
	
	public String toString() {
		String out = "";
		switch (this) {
		case WAIT_PLAYERS:
			out = "wait_player";
			break;
		case PRE_FLOP:
			out = "pre_flop";
			break;
		case FLOP:
			out = "flop";
			break;
		case TURN:
			out = "turn";
			break;
		case RIVER:
			out = "river";
			break;
		case SHOWDOWN:
			out = "showdown";
			break;
		default:
			break;
		}
		
		return out;
	}
}
