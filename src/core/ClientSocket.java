package core;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;
import java.util.LinkedList;
import java.util.Queue;

public class ClientSocket extends Thread {
	private Socket _socket;
	private Queue<String> _clientMessages;
	private BufferedReader _inputFromClient;
	private DataOutputStream _outputToClient;
	
	public ClientSocket(Socket connectionSocket) {
		_clientMessages = new LinkedList<String>();
		_socket = connectionSocket;
	}
	
	public boolean connect() {
		try {
			_inputFromClient = new BufferedReader(new InputStreamReader(_socket.getInputStream()));
			_outputToClient = new DataOutputStream(_socket.getOutputStream());
			this.start();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean disconnect() {
		try {
			_socket.close();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean write(String message) {
		try {
			_outputToClient.writeBytes(message);
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public Queue<String> readAll() {
		if (!_clientMessages.isEmpty()) {
			Queue<String> out = _clientMessages;
			_clientMessages.clear();
			return out;
		}
		return null;
	}
	
	public String read() {
		if (!_clientMessages.isEmpty()) {
			return _clientMessages.poll();
		}
		
		return null;
	}
	
	@Override
	public void run() {
		boolean exit = false;
		
		while (!exit) {
			String checker = null;
			String serverMessage = null;
			
			do {
				try {
					checker = _inputFromClient.readLine();
					if (checker != null) 
						serverMessage += checker;
				} catch (IOException e) {
					e.printStackTrace();
					exit = true;
				}
				
			} while (checker != null && !exit);
			
			if (!exit)
				_clientMessages.add(serverMessage);
		}
	}
}
