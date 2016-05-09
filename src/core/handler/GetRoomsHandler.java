package core.handler;

import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;

public class GetRoomsHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ServerManager server = (ServerManager) content.get(2);

	}

}
