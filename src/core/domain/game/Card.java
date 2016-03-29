package core.domain.game;

public class Card {

	protected Suit suit;
	protected int number;
	
	public Card(Suit suit, int number) {
		super();
		this.suit = suit;
		this.number = number;
	}
	
	public int compareTo(Card other) {
		int myNum = number;
		int otherNum = other.getNumber();
		if (otherNum == 1) otherNum = 14;
		if (myNum == 1) myNum = 14;
		return myNum - otherNum;
	}
	
	public boolean isPredecessor(Card other) {
		int myNum = number;
		int otherNum = other.getNumber();
		if (otherNum == 1) otherNum = 14;
		if (myNum == 1) myNum = 14;
		
		if (myNum - otherNum == 1)
			return true;
		return false;
	}
	
	public Suit getSuit() {
		return suit;
	}
	
	public int getNumber() {
		return number;
	}
	
	@Override
	public boolean equals(Object obj) {
		return number == ((Card)obj).getNumber() && suit.equals(((Card)obj).getSuit());
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
			case 1:
				num = "A";
				break;
			default:
				break;
			}
		}
		
		return num + " of " + suit.toString();
	}
}
