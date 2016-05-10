package core.handler;

import java.util.List;

public class DisconnectHandler extends Handler {

	@Override
	public void handle(List<Object> content) {
		//Esta classe é acionada quando um jogador se desconecta do servidor
		//funciona de forma parecida que o leave_room, porém temos que retirar
		//ele da lista de clientes e liberar o id dele.
	}

}
