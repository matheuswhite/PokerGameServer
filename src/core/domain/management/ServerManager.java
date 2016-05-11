package core.domain.management;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import core.domain.game.Money;
import core.domain.game.PlayerInfo;
import core.net.ClientConnection;
import core.net.Message;
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
	
	public Room createRoom(Money smallBlindValue, Money minimumBuyIn){
		long id = _numGenRoom.genId();
		Room room = new Room(id, smallBlindValue, minimumBuyIn);
		_listOfRooms.put(id, room);
		
		return room;
	}
	public void removeRoom(long roomId) {
		_listOfRooms.remove(_listOfRooms.get(roomId));
		_numGenRoom.removeId(roomId);
	}
	public Room getRoom(long id){
		return _listOfRooms.get(id);
	}
	public Map<Long, Room> getAllRooms(){
		return _listOfRooms;
	}
	public Client getClient(long id){
		return _listOfClients.get(id);
	}
	public void removeClient(long id) {
		_listOfClients.remove(_listOfClients.get(id));
		_numGenClient.removeId(id);
	}
	public void sendPlayersInRoom(long id, Message msg) throws IOException{
		List<PlayerInfo> players = getRoom(id).getPlayers();
		
		for(int i=0; i < players.size(); ++i){
			
			Client player = getClient(players.get(i).getId());
			player.getClientSocket().write(msg);
		}
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
