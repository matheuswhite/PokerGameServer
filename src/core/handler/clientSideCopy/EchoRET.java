package core.handler.clientSideCopy;

import java.util.List;

import core.handler.Handler;

public class EchoRET extends Handler {
	
	@Override
	public void handle(List<Object> content) {
		System.out.println(content.get(2));
	}
}
