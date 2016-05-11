package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.game.Money;
import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class CreateRoomHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ClientConnection client = (ClientConnection) content.get(0);
		long playerID = (long) content.get(1);
		ServerManager server = (ServerManager) content.get(2);
		
		Money smallBlindValue = _gson.fromJson((String)content.get(3), Money.class);
		Money minimumBuyIn = _gson.fromJson((String)content.get(4), Money.class);
		
		Room room = server.createRoom(smallBlindValue, minimumBuyIn);
		try {
			room.addPlayer(server.getClient(playerID).getPlayerInfo());
			server.getClient(playerID).setCurrentRoomId(room.getId());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(room);
		
		try {
			client.write(new Message("CREATE_ROOM_RET", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
