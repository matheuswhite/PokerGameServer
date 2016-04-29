package core.domain.messageHandler;

import java.util.Observable;

import core.net.Message;

public class FoldReceiver extends MessageHandler {
	
	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		if (!_message.getHandler().equals("FOLD") || _message.getVersion() != 1.0) {
			return ;
		}
		
		System.out.println("Handler " + FoldReceiver.class.getName());
		System.out.println("Handled: " + _message.getJsonString());
	}
}