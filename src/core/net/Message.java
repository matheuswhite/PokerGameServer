package core.net;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Message {

	private String _handler;
	private List<String> _contents;
	
	public Message(String handler, List<Object> contents) {
		Gson gson = new Gson();
		_contents = new ArrayList<String>();
		_handler = handler;
		
		for (Object object : contents) {
			_contents.add(gson.toJson(object));
		}
	}
	
	public Message(String jsonString) {
		Gson gson = new Gson();
		Message msg = gson.fromJson(jsonString, Message.class);
		
		_handler = msg.getHandler();
		_contents = msg.getContents();
	}
	
	public String getHandler() {
		return _handler;
	}
	
	public List<String> getContents() {
		return _contents;
	}
	
	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this) + "\n";
	}
}
