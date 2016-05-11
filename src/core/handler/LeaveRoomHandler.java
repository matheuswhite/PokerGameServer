package core.handler;

import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;

public class LeaveRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = _gson.fromJson((String)content.get(3), Long.class);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
	
		server.getRoom(roomID).removePlayer(server.getClient(playerID).getPlayerInfo());
		

		//Enviar a todos os jogadores da sala que este jogador saiu da sala
		//e o proximo estado da sala
		//Ex.: new Message("LEAVE", PlayerInfo);
	}

}
