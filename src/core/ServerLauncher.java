package core;


import java.io.IOException;

import core.domain.game.Deck;
import core.domain.management.ServerManager;

public class ServerLauncher {

	public static void main(String[] args) throws IOException {
		
		Deck deck = new Deck();
		
		System.out.println("Shuffling...");
		deck.shuffle();
		
		while( 0 < deck.size()){
			System.out.println(deck.getCard().toString());
		}	
		
		ServerManager manager = new ServerManager();
	}

}
