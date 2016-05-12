package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import core.domain.game.Card;
import core.domain.game.Deck;
import core.domain.game.MatchInfo;
import core.domain.game.MatchPhase;
import core.domain.game.Money;
import core.domain.game.PlayerInfo;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.Message;

public class EndTurnHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);	
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		
		if (playerID == room.getMatchInfo().getPivotPlayerId()) {
			
			switch (room.getMatchInfo().getCurrentMatchPhase()) {
			case PRE_FLOP:
				handlerPreFlopPhase(room, server);
				break;
			case FLOP:
				handlerFlopPhase(room, server);
				break;
			case TURN:
				handlerTurn(room, server);
				break;
			case RIVER:
				handlerRiver(room, server);
				break;
			default:
				break;
			}
		}
		
		room.getMatchInfo().setCurrentTurnPlayerId(playerID);
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room.getMatchInfo());
		
		try {
			server.sendPlayersInRoom(room.getId(), new Message("END_TURN", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void startGame(ServerManager server, Room room) {
		List<Object> contents = new ArrayList<Object>();
		List<PlayerInfo> playerInfos = server.getRoom(room.getId()).getPlayers();
		
		contents.add(playerInfos.size());
		
		room.set_deck(new Deck());
		Deck deck = server.getRoom(room.getId()).get_deck();
		deck.shuffle();
		
		for (int i = 0; i < playerInfos.size(); i++) {
			//Distribuindo as cartas
			Card[] hand = new Card[2];
			hand[0] = deck.getCard();
			hand[1] = deck.getCard();
			playerInfos.get(i).setHand(hand);
			
			//"setando" a flag InGame como true
			playerInfos.get(i).setInGame();
			contents.add(playerInfos.get(i));
		}

		//Iniciando as informações da sala
		MatchInfo matchInfo = server.getRoom(room.getId()).getMatchInfo();
		
		long nextDealer = room.nextPlayer(matchInfo.getDealerPlayerId()).getId();
		matchInfo.setDealerPlayerId(nextDealer);
		
		long nextSmallBlind = room.nextPlayer(nextDealer).getId();
		matchInfo.setSmallBlindPlayerId(nextSmallBlind);
		
		long nextBigBlind = room.nextPlayer(nextSmallBlind).getId();
		matchInfo.setBigBlindPlayerId(nextBigBlind);
		
		long nextCurrentTurnPlayer = room.nextPlayer(nextBigBlind).getId();
		matchInfo.setCurrentTurnPlayerId(nextCurrentTurnPlayer);
		matchInfo.setPivotPlayerId(nextCurrentTurnPlayer);
		
		matchInfo.setCurrentMatchPhase(MatchPhase.PRE_FLOP);
		matchInfo.setHigherCurrentBet(new Money());
		contents.add(matchInfo);
		
		//Enviando a mensagem START_GAME
		try {
			server.sendPlayersInRoom(room.getId(), new Message("START_GAME", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void handlerRiver(Room room, ServerManager serverManage) {
		room.getMatchInfo().setPhase(MatchPhase.SHOWDOWN);
		
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				startGame(serverManage, room);
			}
		}, 5000);
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room.getMatchInfo());
		
		try {
			serverManage.sendPlayersInRoom(room.getId(), new Message("CHANGE_PHASE", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handlerTurn(Room room, ServerManager serverManage) {
		room.getMatchInfo().setPhase(MatchPhase.RIVER);
		
		try {
			room.getMatchInfo().putCardInTable(room.get_deck().getCard());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room.getMatchInfo());
		
		try {
			serverManage.sendPlayersInRoom(room.getId(), new Message("CHANGE_PHASE", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handlerFlopPhase(Room room, ServerManager serverManage) {
		room.getMatchInfo().setPhase(MatchPhase.TURN);
		
		try {
			room.getMatchInfo().putCardInTable(room.get_deck().getCard());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room.getMatchInfo());
		
		try {
			serverManage.sendPlayersInRoom(room.getId(), new Message("CHANGE_PHASE", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void handlerPreFlopPhase(Room room, ServerManager serverManage) {
		room.getMatchInfo().setPhase(MatchPhase.FLOP);
		
		try {
			for (int i = 0; i < 3; i++) {
				room.getMatchInfo().putCardInTable(room.get_deck().getCard());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room.getMatchInfo());
		
		try {
			serverManage.sendPlayersInRoom(room.getId(), new Message("CHANGE_PHASE", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}





