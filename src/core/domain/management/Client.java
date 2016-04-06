package core.domain.management;

import core.domain.game.PlayerInfo;
import core.domain.messageHandler.MessageHandler;
import core.net.ClientConnection;

public class Client {
	
	private long _id;
	private PlayerInfo _playerInfo;
	private Long _currentRoomId;
	private ClientConnection _clientSocket;
	private Thread _socketThread;
	private ClientState _clientState;
	
	public Client(long id, ClientConnection clientSocket) {
		_id = id;
		_playerInfo = new PlayerInfo(id);
		_clientSocket = clientSocket;
		_currentRoomId = null;
		_socketThread = new Thread(_clientSocket, "Client" + id);
		_socketThread.start();
	}
	
	public void addMessageHandler(MessageHandler handler) {
		_clientSocket.addObserver(handler);
	}
	
	public PlayerInfo getPlayerInfo() {
		return _playerInfo;
	}
	
	public ClientState getCurrentClientState() {
		return _clientState;
	}
	
	public long getId() {
		return _id;
	}
	
	public Long getCurrentRoomId() {
		return _currentRoomId;
	}
	
	public void setCurrentRoomId(long id) {
		_currentRoomId = id;
	}
	
	public ClientConnection getClientSocket() {
		return _clientSocket;
	}
}
