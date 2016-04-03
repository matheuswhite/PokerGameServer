package core.net;

import java.util.List;

import com.google.gson.Gson;

public class Message {

	private double _version;
	private String _handler;
	private List<Object> _contents;
	
	public Message(double version, String handler, List<Object> contents) {
		this._version = version;
		this._handler = handler;
		this._contents = contents;
	}
	
	public Message(String jsonString) {
		Gson gson = new Gson();
		Message msg = gson.fromJson(jsonString, Message.class);
		
		_version = msg.getVersion();
		_handler = msg.getHandler();
		_contents = msg.getContents();
	}
	
	public double getVersion() {
		return _version;
	}
	
	public String getHandler() {
		return _handler;
	}
	
	public List<Object> getContents() {
		return _contents;
	}
	
	public String getJsonString() {
		Gson gson = new Gson();
		return gson.toJson(this) + "\n";
	}
}
