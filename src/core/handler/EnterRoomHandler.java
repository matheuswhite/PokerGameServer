package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
		
		List<Object> contents = new ArrayList<Object>();
		int numOfPlayerBefore = server.getRoom(roomID).getPlayers().size();
		
		try{
			server.getRoom(roomID).addPlayer(server.getClient(playerID).getPlayerInfo());	
			server.getClient(playerID).setCurrentRoomId(roomID);
		}catch(Exception e){
			
		}
		
		contents.add(server.getClient(playerID).getPlayerInfo());
		
		try {
			server.sendPlayersInRoom(roomID, new Message("JOIN", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (numOfPlayerBefore == 1) {
			contents.clear();
			
			List<PlayerInfo> playerInfos = server.getRoom(roomID).getPlayers(); 
			content.add(playerInfos.get(0));
			content.add(playerInfos.get(1));
			
			MatchInfo matchInfo = server.getRoom(roomID).getMatchInfo();
			matchInfo.setDealerPlayerId(playerInfos.get(0).getId());
			matchInfo.setBigBlindPlayerId(playerInfos.get(0).getId());
			matchInfo.setSmallBlindPlayerId(playerInfos.get(1).getId());
			matchInfo.setCurrentTurnPlayerId(playerInfos.get(1).getId());
			matchInfo.setCurrentMatchPhase(MatchPhase.PRE_FLOP);
			matchInfo.setHigherCurrentBet(new Money());
			content.add(matchInfo);
			
			try {
				server.sendPlayersInRoom(roomID, new Message("START_GAME", contents));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

}
