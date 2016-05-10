package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class RequestIDHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		ClientConnection connection = (ClientConnection) content.get(0);
		//Fazer o cast com Double (problemas com JSON)
		long id = (long) content.get(1);
		
		List<Object> contents = new ArrayList<Object>();
		contents.add(id);
		
		try {
			connection.write(new Message("REQUEST_ID_RET", contents));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
