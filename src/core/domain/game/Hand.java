package core.domain.game;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import com.sun.org.apache.bcel.internal.generic.SWAP;

import javafx.print.Collation;

public class Hand {

	private ArrayList<Card> _playerCards;
	//Ira Representar os melhores Hanks se houver
	//[HC][FP][SP][ToK][S][F][FoK][SF]
	private ArrayList<Card> _betterCards;
	private ArrayList<Card> _comboHand;
	
	public Hand() {
		_playerCards = new ArrayList<>();
		_betterCards = new ArrayList<>();
		_comboHand = new ArrayList<>();
	}
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
			_betterCards.set(1,_playerCards.get(0));
		}else if(compNum < 0){
			Collections.swap(playerCards, 0,1);
		}
		_betterCards.set(0, _playerCards.get(0));
		_comboHand.set(0, _playerCards.get(0));
		_comboHand.set(1, _playerCards.get(1));
	}
	
	public void updateHand(ArrayList<Card> newCards){
		newCards.addAll(_playerCards);
		sortByNum(newCards);
		
		
	}
	
	public void sortByNum(ArrayList<Card> cards){
		for(int i = 0;i< cards.size();++i){
			for(int j = i+1;i< cards.size();++i){
				if(cards.get(i).compareTo(cards.get(j)) < 0)
					Collections.swap(cards, 0,1);
			}
		}
	}
	
	public int theHighCard(ArrayList<Card> cards){
		if(cards.size() > 0)
			return 0;
		return -1;
	}
	public ArrayList<Integer> thePairs(ArrayList<Card> cards){
		ArrayList<Integer> positions = new ArrayList<>();
		positions.add(-1);positions.add(-1);
		int cont = 0;
		
		for(int i = 0;i < cards.size() - 1 && positions.get(1) == -1; ++i){
			if(cards.get(i).compareTo(cards.get(i+1)) == 0){
				positions.set(cont, i);
				++cont;
			}
		}
		
		return positions;
	}
	public int theThreeOfAKind(ArrayList<Card> cards){
		
		for(int i = 0;i < cards.size() - 2; ++i){
			if(cards.get(i).compareTo(cards.get(i+1)) == 0 && cards.get(i).compareTo(cards.get(i+2)) == 0){
				return i;
			}
		}
		return -1;
	}
	public int TheStraight(ArrayList<Card> cards){
		for(int i = 0;i < cards.size() - 4; ++i){
			
		}
		return -1;
	}
	public ArrayList<Integer> theFlush(ArrayList<Card> cards){
		ArrayList<Integer> positions = new ArrayList<>();
		
		for(int i = 0;i < cards.size() - 1 && positions.size() < 5; ++i){
		
		}
		return positions;
	}
	public int theFullHouse(ArrayList<Card> cards){
		
		for(int i = 0;i < cards.size() - 4; ++i){
			
		}
		return -1;
	}
	public int theFourOfAKind(ArrayList<Card> cards){
	
	return -1;
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
