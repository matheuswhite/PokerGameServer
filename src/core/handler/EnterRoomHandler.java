package core.handler;

import java.util.List;

import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;

public class EnterRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = (long) content.get(3);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		
		try{
			server.getRoom(roomID).addPlayer(server.getClient(playerID).getPlayerInfo());	
			
		}catch(Exception e){
			
		}
		
		
		//Se o numero de jogadores aumentar para 2
		//enviar new Message("START_GAME", PlayerInfo, MatchInfo);
		//Se não, enviar new Message("JOIN", PlayerInfo);
	}

}
