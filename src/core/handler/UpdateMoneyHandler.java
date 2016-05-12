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
		ActionType action = _gson.fromJson((String)content.get(3), ActionType.class);	
		
		Room room = server.getRoom(server.getClient(playerID).getCurrentRoomId());
		
		@SuppressWarnings("rawtypes")
		List newContent = new ArrayList<Object>();
		newContent.add(server.getClient(playerID).getPlayerInfo());
		Money moneyAdd = new Money();
		
		if(action == ActionType.FOLD){
			//Espaco para invocar a funcao playerFold() em matchinfo ou Room
			server.getClient(playerID).getPlayerInfo().setInGame();
			
		}else{
			moneyAdd = _gson.fromJson((String)content.get(4), Money.class);
			
			if (action != ActionType.BUY_IN) {
				room.getMatchInfo().increasePotValue(moneyAdd);
				moneyAdd.addMoney(moneyAdd);
				server.getClient(playerID).getPlayerInfo().setMoneyBetting(moneyAdd);
				
				if (room.getMatchInfo().getHigherCurrentBet().parseToLong() < server.getClient(playerID).getPlayerInfo().getMoneyBetting().parseToLong()) {
					room.getMatchInfo().setHigherCurrentBet(server.getClient(playerID).getPlayerInfo().getMoneyBetting());
					room.getMatchInfo().setPivotPlayerId(playerID);
				}
			}
		}
		
		newContent.add(room.getMatchInfo());
		Message msg = new Message(action.toString(), newContent);
		try {
			server.sendPlayersInRoom(room.getId(),msg);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
