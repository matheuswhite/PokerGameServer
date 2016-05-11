package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.PlayerInfo;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.Message;

public class EndTurnHandler extends Handler {

	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void handle(List<Object> content) {
		
		//Esta classe so trata a passagem de turno do jogador
		//Essas ações são feitas pela classe UpdateMoneyHandler
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);	
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		@SuppressWarnings("rawtypes")
		List newContent = new ArrayList<Object>();
		Message msg;
		String handler = null;
		PlayerInfo winner = null;
		
		VrfyGameSt(handler, winner, room);
		
		if(winner != null){
			newContent.add(winner);
			msg = new Message("WINNER", newContent);
		}else{
			//colocar nextplayer ou Phase aqui
			newContent.add(room.getMatchInfo());
			
			//Handler pode ser "CHANGE_PHASE" ou "END_TURN"
			msg = new Message(handler, newContent);
		}
		try {
			server.sendPlayersInRoom(room.getId(),msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return;
		
	}
	
	public void VrfyGameSt(String handler, PlayerInfo winner,Room room)
	{
		
	}
}





