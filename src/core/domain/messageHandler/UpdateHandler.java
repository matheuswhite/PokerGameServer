package core.domain.messageHandler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import core.net.ClientConnection;
import core.net.Message;

public class UpdateHandler extends MessageHandler {
	
	private List<Object> _contents;
	private ClientConnection _connection;
	
	public UpdateHandler(ClientConnection connection) {
		_contents = new ArrayList<Object>();
		_connection = connection;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		
		
//		if (!_message.getHandler().equals("ECHO") || _message.getVersion() != 1.0) {
//			return ;
//		}
//		
//		System.out.println(_message.getContents().get(0));
//		
//		_contents.add("Yes. I can hear it client");
//		_message = new Message(1.0, "ECHO_RET", _contents);
//		try {
//			_connection.write(_message);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		
//		System.out.println(_contents.get(0));
	}
}
