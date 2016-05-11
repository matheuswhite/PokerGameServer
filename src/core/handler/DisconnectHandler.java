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
		//Esta classe é acionada quando um jogador se desconecta do servidor
		//funciona de forma parecida que o leave_room, porém temos que retirar
		//ele da lista de clientes e liberar o id dele.
		
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
