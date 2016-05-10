package core.handler;

import java.util.List;

import core.handler.Handler;

public class UpdateMoneyHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		// TODO Auto-generated method stub
		
		//Esta classe vai tratar as ações do jogador
		//CALL	RAISE	FOLD	BUY_IN
		
		//Enviar a todos os jogadores da sala a ação deste jogador
		//e o proximo estado da sala
		//Ex.: new Message("CALL", MatchInfo);
		//	   new Message("RAISE", MatchInfo, moneyBet);
		//	   new Message("BUY_IN", MatchInfo, money);
	}

}
