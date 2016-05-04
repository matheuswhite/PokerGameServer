package core.domain.messageHandler;

import java.util.Observable;

import core.net.ClientConnection;
import core.net.Message;
import core.domain.management.Room;
import java.util.List;
import java.util.ArrayList;



public class GetRooms extends MessageHandler {
	
	private List<Object> _listOfRooms;
	private ClientConnection _client;
	
	public GetRooms(List<Room> listOfRooms,ClientConnection client){
		_listOfRooms = new ArrayList<Object>();
		_listOfRooms.add(listOfRooms);
		_client = client;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		if (!_message.getHandler().equals("GET_ROOMS") || _message.getVersion() != 1.0) {
			return ;
		}
		_message = new Message(1.0, "GET_ROOMS",  _listOfRooms);
		try {
			_client.write(_message);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
}
