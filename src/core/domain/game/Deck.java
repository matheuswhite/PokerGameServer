package core.domain.game;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;


public class Deck {

	private Stack<Card> cards;

	public Deck() {
		super();
		cards = new Stack<>();
		fillDeck();
		
	}
	
	public void fillDeck(){
		
		for( int i = 0; i < 4 ; ++i)
		{
			cards.clear();
			if(i == 0){
				for(int j = 0 ; j < 13; ++j){
					cards.add(new Card(Suit.HEARTS, j));
				}
			}
			if(i == 1){
				for(int j = 0 ; j < 13; ++j){
					cards.add(new Card(Suit.SPADES,j));
				}
			}
			if(i == 2){
				for(int j = 0 ; j < 13; ++j){
					cards.add(new Card(Suit.DIAMONDS,j));
				}
			}
			if(i == 3){
				for(int j = 0 ; j < 13; ++j){
					cards.add(new Card(Suit.CLUBS,j));
				}
			}
		}
		
	}
	
	public int size()
	{
		return cards.size();
	}
	
	public Card getCard(){
		return cards.pop();
	}
	
	public void swap(Card A, Card B){
		Card aux;
		aux = A;
		A = B;
		B = aux;
	}
	
	public void shuffle(){	
		
		ArrayList<Card> aux = new ArrayList<>();
		Random ran = new Random();
		
		aux.addAll(cards);
		cards.clear();
		
		for(int i = aux.size() - 1; i >= 1 ; --i)
		{
			swap(aux.get(i),aux.get(ran.nextInt(i)));
		}
		cards.addAll(aux);
	}
	
	
	
}
