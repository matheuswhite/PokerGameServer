package core.handler;

import java.io.IOException;
import java.util.List;

import core.domain.game.Money;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class CreateRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ClientConnection client = (ClientConnection) content.get(0);
		ServerManager server = (ServerManager) content.get(2);
		
		Money smallBlindValue = _gson.fromJson((String)content.get(3), Money.class);
		Money minimumBuyIn = _gson.fromJson((String)content.get(4), Money.class);
		
		server.createRoom(smallBlindValue, minimumBuyIn);
		try {
			client.write(new Message("CREATE_ROOM_RET", null));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		//enviar a room criada de volta para o cliente "CREATE_ROOM_RET"
	}

}
