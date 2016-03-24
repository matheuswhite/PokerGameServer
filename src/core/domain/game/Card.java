package core.domain.game;

public class Card {

	protected Suit suit;
	protected int number;
	
	public Card(Suit suit, int number) {
		super();
		this.suit = suit;
		this.number = number;
	}
	
	@Override
	public String toString() {
		String num = "";
		
		if (number > 1 && number <= 10) {
			num = Integer.toString(number);
		}
		else {
			switch (number) {
			case 11:
				num = "J";
				break;
			case 12:
				num = "Q";
				break;
			case 13:
				num = "K";
				break;
			case 14:
				num = "A";
				break;
			default:
				break;
			}
		}
		return num + "of" + suit.toString();
	}
	
}
