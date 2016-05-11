package core.handler;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import core.handler.Handler;
import core.net.ClientConnection;
import core.net.Message;

public class EchoHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		String echoMsg = _gson.fromJson((String)content.get(3), String.class);
		String echoRetMsg = "Yes. I can hear you client " + content.get(1);
		ClientConnection connection = (ClientConnection) content.get(0);
		List<Object> contents = new ArrayList<Object>();
		
		System.out.println(echoMsg);
		
		try {
			contents.add(echoRetMsg);
			connection.write(new Message("ECHO_RET", contents));
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		System.out.println(echoRetMsg);
	}
}
