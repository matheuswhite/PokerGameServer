package core.handler;

import java.util.List;

import core.domain.game.Money;
import core.domain.management.ServerManager;
import core.handler.Handler;

public class CreateRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {		
		ServerManager server = (ServerManager) content.get(2);
		
		Money smallBlindValue = _gson.fromJson((String)content.get(3), Money.class);
		Money minimumBuyIn = _gson.fromJson((String)content.get(4), Money.class);
		
		server.createRoom(smallBlindValue, minimumBuyIn);

		
		//enviar a room criada de volta para o cliente "CREATE_ROOM_RET"
	}

}
