package core.handler;

import java.util.List;

import com.google.gson.Gson;

public abstract class Handler {

	protected Gson _gson;
	
	public Handler() {
		_gson = new Gson();
	}
	
	public abstract void handle(List<Object> content);
}
