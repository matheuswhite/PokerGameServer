package core.domain.messageHandler;

import java.util.Observable;

import core.net.Message;

public class RaiseReceiver extends MessageHandler {
	
	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		if (!_message.getHandler().equals("RAISE") || _message.getVersion() != 1.0) {
			return ;
		}
		
		System.out.println("Handler " + RaiseReceiver.class.getName());
		System.out.println("Handled: " + _message.getJsonString());
	}
}