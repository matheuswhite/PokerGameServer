package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.MatchPhase;
import core.domain.game.PlayerInfo;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class LeaveRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		long roomID = _gson.fromJson((String)content.get(3), Long.class);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		ClientConnection connection = (ClientConnection) content.get(0);
		
		PlayerInfo playerInfo = server.getClient(playerID).getPlayerInfo();
		Room room = server.getRoom(roomID);
		
		room.removePlayer(server.getClient(playerID).getPlayerInfo());
		server.getClient(playerID).setCurrentRoomId(null);
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(playerInfo);
		
		if (room.getPlayers().size() == 0) {
			//apagar a sala
			server.removeRoom(roomID);
		}
		else { 
			if (room.getPlayers().size() == 1) {
				//Mudar para o estado wait_players
				room.getMatchInfo().setPhase(MatchPhase.WAIT_PLAYERS);

				//enviar mensagem ChangePhase(WAIT_Player)
			}

			try {
				server.sendPlayersInRoom(roomID, new Message("LEAVE", contents));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
