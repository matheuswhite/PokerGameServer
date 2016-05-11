package core.handler;

import java.util.List;

import core.domain.game.PlayerInfo;
import core.domain.management.ServerManager;
import core.handler.Handler;

public class CreatePlayerInfoHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ServerManager server = (ServerManager) content.get(2);
		PlayerInfo playerInfo = _gson.fromJson((String)content.get(3), PlayerInfo.class);
		
		server.getClient(playerInfo.getId()).setPlayerInfo(playerInfo);
	}

}
