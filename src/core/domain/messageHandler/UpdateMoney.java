package core.domain.messageHandler;

import java.util.Observable;

import core.domain.game.Money;
import core.net.Message;

public class UpdateMoney extends MessageHandler {
	
	Money _money;
	ActionType _action;
	
	protected UpdateMoney(Money money, ActionType action) {
		super();
		_action = action;
		_money = money;
	}

	@Override
	public void update(Observable o, Object arg) {
		_message = (Message) arg;
		if ((!_message.getHandler().equals("RAISE") && 
			 !_message.getHandler().equals("CHECK") &&
			 !_message.getHandler().equals("CALL")) || 
			  _message.getVersion() != 1.0) {
			return ;
		}
		
		if(ActionType.RAISE.equalsName(_message.getHandler())){
			_action = ActionType.RAISE;
		}
		if(ActionType.CHECK.equalsName(_message.getHandler())){
			_action = ActionType.CHECK;
		}
		if(ActionType.CALL.equalsName(_message.getHandler())){
			_action = ActionType.CALL;
		}

		_money = (Money) _message.getContents().get(0);
		
	}
}