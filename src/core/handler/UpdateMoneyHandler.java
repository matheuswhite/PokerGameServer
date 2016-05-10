package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.Money;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.Message;

public class UpdateMoneyHandler extends Handler {

	@SuppressWarnings("unchecked")
	@Override
	public void handle(List<Object> content) {
		
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		String action = (String) content.get(3);	
		
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		
		@SuppressWarnings("rawtypes")
		List newContent = new ArrayList<Object>();
		newContent.add(server.getClient(playerID).getPlayerInfo());
		
		if(action == "FOLD"){
			//Espaco para invocar a funcao playerFold() em matchinfo ou Room
			error
			
		}else{
			Money moneyAdd = (Money) content.get(4);
			room.getMatchInfo().increasePotValue(moneyAdd);
		}
		
		newContent.add(room.getMatchInfo());
		Message msg = new Message(action, newContent);
		try {
			server.sendPlayersInRoom(room.getId(),msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Enviar a todos os jogadores da sala a ação deste jogador
		//e o proximo estado da sala
		//Ex.: new Message("CALL", PlayerInfo, moneyBet); {Só mande esta se o dinheiro apostado for maior que zero}
		//	   new Message("RAISE", PlayerInfo, moneyBet);
		//	   new Message("BUY_IN", PlayerInfo, money);
		//	   new Message("FOLD", PlayerInfo);
	}

}
