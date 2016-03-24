package core.domain.messageHandler;

import java.util.Observable;
import java.util.Observer;

public abstract class MessageHandler implements Observer {

	@Override
	public abstract void update(Observable o, Object arg);
}
