package core.handler;

import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;

public class LeaveRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = (long) content.get(3);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
	
		server.getRoom(roomID).removePlayer(server.getClient(playerID).getPlayerInfo());
		


	}

}
