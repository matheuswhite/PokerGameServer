package core.domain.game;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Hand {

	private ArrayList<Card> _playerCards;
	//Ira Representar os melhores Hanks se houver
	//[HC][FP][SP][ToK][S][F][FoK][SF]
	private ArrayList<Card> _betterCards;
	private ArrayList<Card> _comboHand;
	
	public Hand(ArrayList<Card> playerCards) {
		if (playerCards.size() != 2)
			throw new IllegalArgumentException("Number of cards invalid");
		_playerCards = playerCards;
		_betterCards = new ArrayList<Card>();
		_comboHand = new ArrayList<Card>();
		addSlots(_betterCards, 8);
		addSlots(_comboHand, 5);
		int compNum = playerCards.get(0).compareTo(playerCards.get(1));
		if(compNum == 0){
			_betterCards
			_betterCards.set(1, )
		}else if()
	}
	
	public void addSlots(ArrayList<Card> cards, int Size){
		cards.clear();
		for(int i = 0; i < Size ; ++i){
			cards.add(null);
		}
	}
	public void dump(ArrayList<Card> cards, int Size){
		for(int i = 0; i < Size ; ++i){
			cards.set(i,null);
		}
	}
	
	
}
