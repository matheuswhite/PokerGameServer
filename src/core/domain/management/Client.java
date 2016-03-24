package core.domain.management;

import core.domain.messageHandler.MessageHandler;
import core.net.ClientSocket;

public class Client {
	
	private long _id;
	private long _currentRoomId;
	private String _name;
	private ClientSocket _clientSocket;
	private Thread _socketThread;
	private ClientState _clientState;
	
	public Client(long id, ClientSocket clientSocket) {
		_clientSocket = clientSocket;
		_id = id;
		_socketThread = new Thread(_clientSocket, "Client" + id);
		_socketThread.start();
	}
	
	public void addMessageHandler(MessageHandler handler) {
		_clientSocket.addObserver(handler);
	}
	
	public String getName() {
		return _name;
	}
	
	public void setName(String name) {
		_name = name;
	}
	
	public ClientState getCurrentClientState() {
		return _clientState;
	}
	
	public long getId() {
		return _id;
	}
	
	public long getCurrentRoomId() {
		return _currentRoomId;
	}
	
	public void setCurrentRoomId(long id) {
		_currentRoomId = id;
	}
	
	public ClientSocket getClientSocket() {
		return _clientSocket;
	}
}
