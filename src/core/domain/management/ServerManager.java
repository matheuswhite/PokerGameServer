package core.domain.management;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import core.domain.game.Money;
import core.net.ClientConnection;
import core.service.NumberGenerator;

public class ServerManager extends Thread {

	private java.net.ServerSocket _welcomeSocket;
	private Map<Long, Client> _listOfClients;
	private Map<Long, Room> _listOfRooms;
	private NumberGenerator _numGenClient;
	private NumberGenerator _numGenRoom;
	
	public ServerManager() throws IOException {
		_listOfClients = new HashMap<Long, Client>();
		_listOfRooms = new HashMap<Long, Room>();
		_numGenClient = new NumberGenerator();
		_numGenRoom = new NumberGenerator();
		
		_welcomeSocket = new java.net.ServerSocket(1095);
		this.start();
	}
	
	public void createRoom(Money smallBlindValue, Money minimumBuyIn){
		long id = _numGenRoom.genId();
		_listOfRooms.put(id, new Room(id, smallBlindValue, minimumBuyIn));
		
	}
	
	@Override
	public void run() {
		Socket connectionSocket = null;
		ClientConnection t = null;
		Client c = null;
		
		while (true) {
			try {
				System.out.println("Listen at port 1095...");
				connectionSocket = _welcomeSocket.accept();
				
				System.out.println("Gen a unique client id...");
				long id = _numGenClient.genId();
				
				System.out.println("Trying connect with the client...");
				t = new ClientConnection(connectionSocket, id);
				t.connect();
				
				System.out.println("Creating a client instance...");
				//Criando um client
				c = new Client(id, t);
				//Add um client na lista de clientes
				_listOfClients.put(id, c);
				//Add o ServerManager ao messageHandler
				t.getMessageHandler().setServerManager(this);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
