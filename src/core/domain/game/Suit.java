package core.domain.game;

public enum Suit {

	HEARTS , SPADES, DIAMONDS, CLUBS;   
	
	@Override
	public String toString() {
		String out = "";
		switch (this) {
		case HEARTS:
			out = "Hearts";
			break;
		case SPADES:
			out = "Spades";
			break;
		case DIAMONDS:
			out = "Diamonds";
			break;
		case CLUBS:
			out = "Clubs";
			break;
		default:
			break;
		}
		
		return out;
	}
}
