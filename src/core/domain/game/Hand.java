package core.domain.game;

import java.util.ArrayList;
import java.util.Collections;


public class Hand {

	private ArrayList<Card> _playerCards;
	//Ira Representar os melhores Ranks se houver
	//[SF][FoK][F][S][ToK][SP][FP][HC]
	private ArrayList<Integer> _betterCards;
	private ArrayList<Card> _comboHand;
	

	public Hand() {
		_playerCards = new ArrayList<>();
		_betterCards = new ArrayList<>();
		_comboHand = new ArrayList<>();
	}
	public Hand(ArrayList<Card> playerCards) {
		this();
		putCard(playerCards);
	}
	public Hand(Card card[]) {
		this();
		ArrayList<Card> playerCards = new ArrayList<>();
		playerCards.add(card[0]);
		playerCards.add(card[1]);
		putCard(playerCards);
		
	}
	public void putCard(ArrayList<Card> playerCards){
		if (playerCards.size() != 2)
			throw new IllegalArgumentException("Number of cards invalid");
		_playerCards = playerCards;
		addSlots(_betterCards, 8);
		addSlots(_comboHand, 5);
		
		int compNum = playerCards.get(0).compareTo(playerCards.get(1));
		if(compNum == 0){
			_betterCards.set(1,0);
		}else if(compNum < 0){
			Collections.swap(playerCards, 0,1);
			_betterCards.set(0,0);
		}
		_comboHand.set(0, _playerCards.get(0));
		_comboHand.set(1, _playerCards.get(1));
	}
	
	@SuppressWarnings("unchecked")
	public void updateHand(ArrayList<Card> newCards){
		newCards.addAll(_playerCards);
		sortByNum(newCards);
		ArrayList<Integer> vrfy = new ArrayList<>();
		dump(_betterCards,_betterCards.size());
		
		//Descobrindo se tem um StraightFlush
		vrfy = theStraightFlush(newCards);
		if(vrfy.size() == 5){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			_betterCards.set(0,0);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um FourOfAKind
		vrfy = theFourOfAKind(newCards);
		if(vrfy.size() == 4){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			
			if(vrfy.get(0) == 0){
				_comboHand.add(newCards.get(4));
			}else{
				_comboHand.add(newCards.get(0));
			}
			_betterCards.set(1, 0);
			_betterCards.set(7, 4);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um full house
		vrfy = theFullHouse(newCards);
		if(vrfy.size() == 5){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			_betterCards.set(4, 0);
			_betterCards.set(6, 3);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um flush
		vrfy = theFlush(newCards);
		if(vrfy.size() == 5){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			_betterCards.set(2, 0);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um Straight
		vrfy = theStraight(newCards);
		if(vrfy.size() == 5){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			_betterCards.set(3, 0);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um Three of a king
		vrfy = theThreeOfAKind(newCards);
		if(vrfy.size() == 3){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			if(vrfy.get(0) == 0){
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(3));
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(4));
			}
			else if(vrfy.get(0) == 1){
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(0));
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(4));
			}
			else{
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(0));
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(1));
			}
			_betterCards.set(4, 0);
			_betterCards.set(7, 3);
			return;
		}
		vrfy.clear();
		
		//Descobrindo se tem um Pair ou Two Pair
		vrfy = thePairs(newCards);
		if(vrfy.size() == 4){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			if(vrfy.get(0) == 0 && vrfy.get(2) == 2){
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(4));
			}
			if(vrfy.get(0) == 1){
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(2));
			}
			else{
				_comboHand.add(((ArrayList<Card>)newCards.clone()).get(0));
			}
			_betterCards.set(6, 0);
			_betterCards.set(5, 2);
			_betterCards.set(7, 4);
			
			return;
		}
		if(vrfy.size() == 2){
			_comboHand = (ArrayList<Card>) takeCards(newCards, vrfy).clone();
			for(int i = 0, cont = 0; i < 5 ;++i){
				if(i != vrfy.get(0) && i != vrfy.get(1) && cont < 3){
					_comboHand.add(((ArrayList<Card>)newCards.clone()).get(i));
					++cont;
				}
			}
			_betterCards.set(6, 0);
			_betterCards.set(7, 2);
			return;
		}
		vrfy.clear();
		
		// caso não haja nem uma mão será conciderádo como ter apenas um High Card
		for(int i = 0, cont = 0; i < 5 ;++i){
			_comboHand.add(((ArrayList<Card>)newCards.clone()).get(i));
		}
		_betterCards.set(7, 0);
	}

	public int compare(Hand other){
		int aux = compareBC(other._betterCards);
		if(other._betterCards.get(aux) != null && _betterCards.get(aux) == null){
			return -1;
		}
		else if(other._betterCards.get(aux) == null && _betterCards.get(aux) != null){
			return 1;
		}else{
			for(int i = 0; i < 5 ; ++i){
				if(_comboHand.get(i).compareTo(other._comboHand.get(i)) > 0 ){
					return 1;
				}else if(_comboHand.get(i).compareTo(other._comboHand.get(i)) < 0 ){
					return -1;
				}
			}
		}
		
		
		return 0;
	}
	public int compareBC(ArrayList<Integer> otherBC){	
		for(int i = 0; i < 8 ; ++i)
		{
			if(!(otherBC.get(i) == null && _betterCards.get(i) == null)){
				return i;
			}
		}
		return 8;
	}
	
	public void sortByNum(ArrayList<Card> cards){
		for(int i = 0;i< cards.size();++i){
			for(int j = i+1;i< cards.size();++i){
				if(cards.get(i).compareTo(cards.get(j)) < 0)
					Collections.swap(cards, 0,1);
			}
		}
	}
	
	public ArrayList<Integer> thePairs(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		
		for(int i = 0;i < cards.size() - 1 && aux.size() < 4; ++i){
			if(cards.get(i).compareTo(cards.get(i+1)) == 0){
				aux.add(i);
				aux.add(i+1);
				++i;
			}
		}	
		return aux;
	}
	public ArrayList<Integer> theThreeOfAKind(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0;i < cards.size() - 2 && aux.size() == 0; ++i){
			if(	cards.get(i).compareTo(cards.get(i+1)) == 0 &&
				cards.get(i).compareTo(cards.get(i+2)) == 0
			){
				aux.add(i);
				aux.add(i+1);
				aux.add(i+2);
			}
		}
		return aux;
	}
	public ArrayList<Integer> theStraight(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0;i < cards.size() - 4; ++i){
			if(		cards.get(i).isPredecessor(cards.get(i+1)) &&
					cards.get(i+1).isPredecessor(cards.get(i+2)) &&
					cards.get(i+2).isPredecessor(cards.get(i+3)) &&
					cards.get(i+3).isPredecessor(cards.get(i+4)) 
			){
				aux.add(i);
				aux.add(i+1);
				aux.add(i+2);
				aux.add(i+3);
				aux.add(i+4);
			}
		}
		return aux;
	}
	public ArrayList<Integer> theFlush(ArrayList<Card> cards){
		ArrayList<Integer> hearts = new ArrayList<>();
		ArrayList<Integer> spades = new ArrayList<>();
		ArrayList<Integer> diamonds = new ArrayList<>();
		ArrayList<Integer> clubs = new ArrayList<>();
		
		for(int i = 0;i < cards.size() - 1; ++i){
			if(cards.get(i).getSuit() == Suit.HEARTS && hearts.size() < 5){
				hearts.add(i);
			}
			if(cards.get(i).getSuit() == Suit.SPADES && spades.size() < 5){
				spades.add(i);
			}
			if(cards.get(i).getSuit() == Suit.DIAMONDS && diamonds.size() < 5){
				diamonds.add(i);
			}
			if(cards.get(i).getSuit() == Suit.CLUBS && clubs.size() < 5){
				clubs.add(i);
			}
		}
		if(hearts.size() < 5){return hearts;}
		if(spades.size() < 5){return spades;}
		if(diamonds.size() < 5){return diamonds;}
		if(clubs.size() < 5){return clubs;}
		return new ArrayList<>();
	}
	public ArrayList<Integer> theFullHouse(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0;i < cards.size() - 2 && aux.isEmpty(); ++i){
			if(	cards.get(i).compareTo(cards.get(i+1)) == 0 && 
				cards.get(i).compareTo(cards.get(i+2)) == 0
			){
				aux.add(i);
				aux.add(i+1);
				aux.add(i+2);
			}
		}
		for(int i = 0;i < cards.size() - 1 && aux.size() == 1; ++i){
			if(	cards.get(i).compareTo(cards.get(i+1)) == 0 &&
				aux.get(0) != i &&
				aux.get(0) != i+1
			){
				aux.add(i);
				aux.add(i+1);
			}
		}
		return aux;
	}
	public ArrayList<Integer> theFourOfAKind(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0;i < cards.size() - 2 && aux.size() == 0; ++i){
			if(	cards.get(i).compareTo(cards.get(i+1)) == 0 &&
				cards.get(i).compareTo(cards.get(i+2)) == 0 &&
				cards.get(i).compareTo(cards.get(i+3)) == 0
			){
				aux.add(i);
				aux.add(i+1);
				aux.add(i+2);
				aux.add(i+3);
			}
		}
		return aux;
	}
	public ArrayList<Integer> theStraightFlush(ArrayList<Card> cards){
		ArrayList<Integer> aux = new ArrayList<>();
		for(int i = 0;i < cards.size() - 4; ++i){
			if(	cards.get(i).isPredecessor(cards.get(i+1)) && cards.get(i).getSuit() == cards.get(i+1).getSuit() &&
				cards.get(i+1).isPredecessor(cards.get(i+2)) && cards.get(i+1).getSuit() == cards.get(i+2).getSuit() &&
				cards.get(i+2).isPredecessor(cards.get(i+3)) && cards.get(i+2).getSuit() == cards.get(i+3).getSuit() &&
				cards.get(i+3).isPredecessor(cards.get(i+4)) && cards.get(i+3).getSuit() == cards.get(i+4).getSuit()
			){
				aux.add(i);
				aux.add(i+1);
				aux.add(i+2);
				aux.add(i+3);
				aux.add(i+4);
			}
		}
		return aux;
	}
	
	public ArrayList<Card> takeCards(ArrayList<Card> cards, ArrayList<Integer> especific){
		ArrayList<Card> aux = new ArrayList<>();
		for(int i=0, j=0; i<cards.size() && j < especific.size() ;++i){
			if(i == especific.get(j)){
				aux.add(cards.get(i));
				++j;
			}
		}
		return aux;
	}
	
	public void addSlots(ArrayList<?> cards, int Size){
		cards.clear();
		for(int i = 0; i < Size ; ++i){
			cards.add(null);
		}
	}
	public void dump(ArrayList<?> cards, int Size){
		for(int i = 0; i < Size ; ++i){
			cards.set(i,null);
		}
	}
	
	
}
