package core.domain.game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Stack;


public class Deck {

	private Stack<Card> cards;

	public Deck() {
		super();
		cards = new Stack<Card>();
		fillDeck();
		
	}
	
	public void fillDeck(){
		
		cards.clear();
		for( int i = 0; i < 4 ; ++i)
		{
			if(i == 0){
				for(int j = 1 ; j <= 13; ++j){
					cards.add(new Card(Suit.HEARTS, j));
				}
			}
			else if(i == 1){
				for(int j = 1 ; j <= 13; ++j){
					cards.add(new Card(Suit.SPADES,j));
				}
			}
			else if(i == 2){
				for(int j = 1 ; j <= 13; ++j){
					cards.add(new Card(Suit.DIAMONDS,j));
				}
			}
			else if(i == 3){
				for(int j = 1 ; j <= 13; ++j){
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
	
	public void shuffle(){	
		
		ArrayList<Card> aux = new ArrayList<>();
		Random ran = new Random();
		
		aux.addAll(cards);
		cards.clear();
		
		for(int i = aux.size() - 1; i >= 1 ; --i)
		{
			Collections.swap(aux, i, ran.nextInt(i));
		}
		cards.addAll(aux);
	}
	
	
	
}
