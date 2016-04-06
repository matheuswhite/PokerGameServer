package core.domain.messageHandler;

import java.util.Observable;
import java.util.Observer;

import core.net.Message;

public abstract class MessageHandler implements Observer {

	protected Message _message;
	
	@Override
	public abstract void update(Observable o, Object arg);
}
