package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.MatchPhase;
import core.domain.game.PlayerInfo;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.net.ClientConnection;
import core.net.Message;

public class DisconnectHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ClientConnection connection = (ClientConnection) content.get(0);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		PlayerInfo playerInfo = server.getClient(playerID).getPlayerInfo();
		
		//Se o jogador estava numa sala
		if (server.getClient(playerID).getCurrentRoomId() != null) {
			Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
			
			room.removePlayer(server.getClient(playerID).getPlayerInfo());
			server.getClient(playerID).setCurrentRoomId(null);
			
			List<Object> contents = new ArrayList<Object>();
			contents.add(playerInfo);
			
			if (room.getPlayers().size() == 0) {
				//apagar a sala
				server.removeRoom(room.getId());
			}
			else {
				if (room.getPlayers().size() == 1) {
					//Mudar para o estado wait_players
					room.getMatchInfo().setPhase(MatchPhase.WAIT_PLAYERS);

					//enviar mensagem ChangePhase(WAIT_Player)
					List<Object> changePhaseContents = new ArrayList<Object>();
					changePhaseContents.add(room.getMatchInfo());
					try {
						connection.write(new Message("CHANGE_PHASE", changePhaseContents));
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				try {
					server.sendPlayersInRoom(room.getId(), new Message("LEAVE", contents));

				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		server.removeClient(playerID);
	}

}
