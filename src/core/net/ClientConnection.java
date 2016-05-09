package core.net;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.ArrayList;

import core.handler.DisconnectHandler;

public class ClientConnection extends Thread {
	
	private Socket _socket;
	private BufferedReader _inputFromClient;
	private DataOutputStream _outputToClient;
	
	private MessageHandler _messageHandler;
	
	public ClientConnection(Socket connectionSocket, long id) {
		_socket = connectionSocket;
		_messageHandler = new MessageHandler(this, id);
	}
	
	public void connect() throws IOException {
		_inputFromClient = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
		_outputToClient = new DataOutputStream(_socket.getOutputStream());
	}
	
	public void disconnect() throws IOException {
		_socket.close();
	}
	
	public void write(Message message) throws IOException {
		_outputToClient.writeBytes(message.getJsonString());
	}
	
	private void listen() throws IOException {
		String clientMessage = null;
		
		System.out.println("Listen from client...");
		clientMessage = _inputFromClient.readLine();
			
		if (clientMessage == null)
			throw new IOException("Client disconnected");
		
		_messageHandler.handler(new Message(clientMessage));
	}
	
	@Override
	public void run() {
		boolean exit = false;
		while (!exit) {
			try {
				listen();
			} catch (IOException e) {
				e.printStackTrace();
				exit = true;
			}
		}
		_messageHandler.handler(new Message(new DisconnectHandler(), new ArrayList<Object>()));
	}
}
