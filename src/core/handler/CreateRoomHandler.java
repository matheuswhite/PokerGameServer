package core.handler;

import java.util.List;

import core.domain.game.Money;
import core.domain.management.ServerManager;
import core.handler.Handler;

public class CreateRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ServerManager server = (ServerManager) content.get(2);
		
		server.createRoom((Money) content.get(3),(Money) content.get(4));

	}

}
