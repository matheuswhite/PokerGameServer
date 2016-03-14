

import croupier.Deck;

public class Main {

	public static void main(String[] args) {
		
		Deck deck = new Deck();
		
		while( 0 < deck.size()){
			System.out.println(deck.getCard().toString());
		}	
		
	}

}
