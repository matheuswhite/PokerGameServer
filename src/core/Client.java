package core;

public class Client {
	
	private long _id;
	private ClientSocket _clientSocket;
	
	public Client(long id, ClientSocket clientSocket) {
		_clientSocket = clientSocket;
		_id = id;
	}
	
	public ClientSocket getClientSocket() {
		return _clientSocket;
	}
	
	public long getId() {
		return _id;
	}
}
