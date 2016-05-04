package core.domain.messageHandler;

import java.util.Observable;

import core.net.Message;

public class FoldReceiver extends MessageHandler {
	
	boolean _inGame;
	
	public FoldReceiver(boolean inGame) {
		_inGame = inGame;
	}
	
	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		if (!_message.getHandler().equals("FOLD") || _message.getVersion() != 1.0) {
			return ;
		}
		
		_inGame = false;
		
	}
}