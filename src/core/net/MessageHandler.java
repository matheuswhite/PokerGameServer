package core.net;

import java.util.ArrayList;
import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;
import core.handler.HandlerFactory;

public class MessageHandler {

	private HandlerFactory _factory;
	private Handler _handler;
	private List<Object> _objects;
	
	private ClientConnection _connection;
	private long _id;
	private ServerManager _serverManager;
	
	public MessageHandler(ClientConnection connection, long id) {
		_objects = new ArrayList<Object>();
		_factory = new HandlerFactory();
		
		_connection = connection;
		_id = id;
	}
	
	public void setServerManager(ServerManager serverManager) {
		_serverManager = serverManager;
	}
	
	public void handler(Message message) {
		_handler = _factory.getHandlerInstance(message.getHandler());
		
		_objects.clear();
		_objects.add(_connection);
		_objects.add(_id);
		_objects.add(_serverManager);
		_objects.addAll(message.getContents());
		
		_handler.handle(_objects);
	}
}
