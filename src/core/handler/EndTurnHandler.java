package core.handler;

import java.util.List;

import core.domain.game.Money;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;

public class EndTurnHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		
		//Esta classe so trata a passagem de turno do jogador
		//Essas ações são feitas pela classe UpdateMoneyHandler
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
	
		
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		room.getMatchInfo().increasePotValue(moneyAdd);

		//Se a phase do jogo mudou mandar para todos os jogadores
		// new Message("CHANGE_PHASE", MatchInfo);
		
		//Senão, enviar a todos os jogadores da sala que o jogador acabou o turno
		//e o proximo estado da sala
		//Ex.: new Message("END_TURN", MatchInfo);
		
	}

}
