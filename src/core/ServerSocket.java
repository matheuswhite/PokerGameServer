package core;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class ServerSocket extends Thread {

	private java.net.ServerSocket _welcomeSocket;
	private Map<Long, Client> _listOfClients;
	private NumberGenerator _numGen;
	
	public ServerSocket() throws IOException {
		_listOfClients = new HashMap<Long, Client>();
		_welcomeSocket = new java.net.ServerSocket(95);
		this.start();
	}
	
	@Override
	public void run() {
		Socket connectionSocket = null;
		ClientSocket t = null;
		boolean connected = false;
		
		while (true) {
			try {
				//Ouvindo da porta 95
				connectionSocket = _welcomeSocket.accept();
				
				//Tentando conectar com o client
				while (!connected) {
					t = new ClientSocket(connectionSocket);
					connected = t.connect();
				}
				
				//Gerando um id único
				long id = _numGen.genId();
				
				//add um client na lista de clientes
				_listOfClients.put(id, new Client(id,t));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
