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
		String action = (String) content.get(3);
		Money moneyAdd = (Money) content.get(4);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
	
		System.out.println("O Jogador id:" + playerID + "decidiu agir com " + action);
		
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		room.getMatchInfo().increasePotValue(moneyAdd);

		//Atualizar as informações do do turno
		//Ex.: atualizar o pivô, a 'fase' da partida (PRE_FLOP, FLOP, TURN, RIVER, ...), etc. 
		
		//Enviar a todos os jogadores da sala que o jogador acabou o turno
		//e o proximo estado da sala
		//Ex.: new Message("END_TURN", MatchInfo);
		
	}

}
