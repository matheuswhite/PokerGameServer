package core.domain.management;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import core.net.ClientSocket;
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
	
	private void createClientHandlers(Client client) {
		
	}
	
	@Override
	public void run() {
		Socket connectionSocket = null;
		ClientSocket t = null;
		Client c = null;
		
		while (true) {
			try {
				//Ouvindo da porta 95
				connectionSocket = _welcomeSocket.accept();
				
				//Tentando conectar com o client
				t = new ClientSocket(connectionSocket);
				t.connect();
				
				//Gerando um id único
				long id = _numGenClient.genId();
				
				//Criando um client
				c = new Client(id, t);
				
				//Add all handlers necessary to client
				createClientHandlers(c);
				
				//Add um client na lista de clientes
				_listOfClients.put(id, c);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
