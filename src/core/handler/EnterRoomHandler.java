package core.handler;

import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;

public class EnterRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = _gson.fromJson((String)content.get(3), Long.class);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		
		try{
			server.getRoom(roomID).addPlayer(server.getClient(playerID).getPlayerInfo());	
			
		}catch(Exception e){
			
		}
		
		//Enviar new Message("JOIN", PlayerInfo);
		
		//Se o numero de jogadores aumentar para 2
		//enviar new Message("START_GAME", List<PlayerInfo>, MatchInfo);
	}

}
