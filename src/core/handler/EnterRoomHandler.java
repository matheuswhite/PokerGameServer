package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.Card;
import core.domain.game.Deck;
import core.domain.game.MatchInfo;
import core.domain.game.MatchPhase;
import core.domain.game.Money;
import core.domain.game.PlayerInfo;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.Message;

public class EnterRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = _gson.fromJson((String)content.get(3), Long.class);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		
		//Adicionando o jogador a sala
		try{
			server.getRoom(roomID).addPlayer(server.getClient(playerID).getPlayerInfo());	
			server.getClient(playerID).setCurrentRoomId(roomID);
		}catch(Exception e){
			
		}
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(server.getClient(playerID).getPlayerInfo());
		
		//Enviando a mensagem JOIN
		try {
			server.sendPlayersInRoom(roomID, new Message("JOIN", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		int numOfPlayer = server.getRoom(roomID).getPlayers().size();
		
		//Se o numero de jogadores for 2 iniciar o jogo 
		if (numOfPlayer == 2) {
			contents.clear();
			List<PlayerInfo> playerInfos = server.getRoom(roomID).getPlayers();
			
			contents.add(2);
			
			//Distribuindo as cartas
			server.getRoom(roomID).set_deck(new Deck());
			Deck deck = server.getRoom(roomID).get_deck();
			deck.shuffle();
			
			Card[] hand0 = new Card[2];
			hand0[0] = deck.getCard();
			hand0[1] = deck.getCard();
			playerInfos.get(0).setHand(hand0);
			
			Card[] hand1 = new Card[2];
			hand1[0] = deck.getCard();
			hand1[1] = deck.getCard();
			playerInfos.get(1).setHand(hand1);
			
			//"setando" a flag InGame como true
			playerInfos.get(0).setInGame();
			playerInfos.get(1).setInGame();
			contents.add(playerInfos.get(0));
			contents.add(playerInfos.get(1));
			
			//Iniciando as informações da sala
			MatchInfo matchInfo = server.getRoom(roomID).getMatchInfo();
			matchInfo.setDealerPlayerId(playerInfos.get(0).getId());
			matchInfo.setBigBlindPlayerId(playerInfos.get(0).getId());
			matchInfo.setSmallBlindPlayerId(playerInfos.get(1).getId());
			matchInfo.setCurrentTurnPlayerId(playerInfos.get(1).getId());
			matchInfo.setPivotPlayerId(playerInfos.get(1).getId());
			matchInfo.setCurrentMatchPhase(MatchPhase.PRE_FLOP);
			matchInfo.setHigherCurrentBet(new Money());
			contents.add(matchInfo);
			
			//Enviando a mensagem START_GAME
			try {
				server.sendPlayersInRoom(roomID, new Message("START_GAME", contents));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
