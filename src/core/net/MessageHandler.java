package core.net;

import java.util.ArrayList;
import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;

public class MessageHandler {

	private Handler _handler;
	private List<Object> _objects;
	
	private ClientConnection _connection;
	private long _id;
	private ServerManager _serverManager;
	
	public MessageHandler(ClientConnection connection, long id) {
		_objects = new ArrayList<Object>();
		
		_connection = connection;
		_id = id;
	}
	
	public void setServerManager(ServerManager serverManager) {
		_serverManager = serverManager;
	}
	
	public void handler(Message message) {
		_handler = message.getHandler();
		
		_objects.add(0, _connection);
		_objects.add(1, _id);
		_objects.add(2, _serverManager);
		_objects.addAll(message.getContents());
		
		_handler.handle(_objects);
	}
}
