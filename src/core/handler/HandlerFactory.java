package core.handler;

import java.util.HashMap;
import java.util.Map;

public class HandlerFactory {

	private Map<String, Handler> _tableOfHandler;
	
	public HandlerFactory() {
		_tableOfHandler = new HashMap<String, Handler>();
		
		
		_tableOfHandler.put("CREATE_PLAYER_INFO", new CreatePlayerInfoHandler());
		_tableOfHandler.put("CREATE_ROOM", new CreateRoomHandler());
		_tableOfHandler.put("DISCONNECT", new DisconnectHandler());
		_tableOfHandler.put("ECHO", new EchoHandler());
		_tableOfHandler.put("END_TURN", new EndTurnHandler());
		_tableOfHandler.put("ENTER_ROOM", new EnterRoomHandler());
		_tableOfHandler.put("GET_ROOMS", new GetRoomsHandler());
		_tableOfHandler.put("LEAVE_ROOM", new LeaveRoomHandler());
		_tableOfHandler.put("REQUEST_ID", new RequestIDHandler());
		_tableOfHandler.put("UPDATE_MONEY", new UpdateMoneyHandler());
	}
	
	public Handler getHandlerInstance(String handler) {
		if (!_tableOfHandler.containsKey(handler))
			throw new IllegalArgumentException("This handler not exist in factory");
		
		return _tableOfHandler.get(handler);
	}
}
