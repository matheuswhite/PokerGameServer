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
		//Ex.: new Message("CALL", PlayerInfo, moneyBet); {Só mande esta se o dinheiro apostado for maior que zero}
		//	   new Message("RAISE", PlayerInfo, moneyBet);
		//	   new Message("BUY_IN", PlayerInfo, money);
		//	   new Message("FOLD", PlayerInfo);
	}

}
