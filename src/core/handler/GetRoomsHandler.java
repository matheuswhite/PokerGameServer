package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class GetRoomsHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ServerManager server = (ServerManager) content.get(2);
		ClientConnection client = (ClientConnection) content.get(0);
		
		List<Object> send = new ArrayList<Object>();
		send.add(server.getAllRooms());
		
		//Alterar o nome para "GET_ROOMS_RET" 
		//e enviar somente as salas dentro do range recebido
		Message msg = new Message("GET_ROOM_RET", send);
		try{
			client.write(msg);
		}catch(IOException e){
			
		}

	}

}
