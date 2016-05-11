package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.domain.management.Room;
import core.domain.management.ServerManager;
import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;
import core.service.Range;

public class GetRoomsHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ServerManager server = (ServerManager) content.get(2);
		ClientConnection client = (ClientConnection) content.get(0);
		int initIndex = ((Range)content.get(3)).getInitialIndex();
		int finalIndex = ((Range)content.get(3)).getFinalIndex();
		
		List<Object> contents = new ArrayList<Object>();
		List<Room> listOfRooms = new ArrayList<Room>(server.getAllRooms().values());
		
		//Correção do range
		if (finalIndex > server.getAllRooms().size())
			finalIndex = server.getAllRooms().size();
		if (initIndex < 0)
			initIndex = 0;
		
		//adicionando o numero de salas
		content.add(finalIndex - initIndex);
		//adicionando as salas
		for (int i = initIndex; i < finalIndex; i++) {
			contents.add(listOfRooms.get(i));
		}
		
		try{
			client.write(new Message("GET_ROOM_RET", contents));
		}catch(IOException e){
			
		}

	}

}
