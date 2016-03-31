package core.net;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import core.service.Factory;
import core.service.Serializable;

public class Message {

	private JSONObject _jsonObject;
	private double _version;
	private String _handler;
	private List<Serializable> _contents;
	private Factory _factory;
	
	public Message(double version, String handler, List<Serializable> contents) {
		_version = version;
		_handler = handler;
		_contents = contents;
		
		_jsonObject = new JSONObject();
		_jsonObject.put("version", _version);
		_jsonObject.put("handler", _handler);
		
		JSONArray array = new JSONArray();
		for (Serializable serializable : contents) {
			JSONObject content = new JSONObject();
			content.put("type", serializable.getType());
			content.put("object", serializable.toJSON());
			
			array.put(content);
		}
		_jsonObject.put("content", array);
	}
	
	public Message(String jsonString) {
		_jsonObject = new JSONObject(jsonString);
		_contents = new ArrayList<Serializable>();
		_factory = new Factory();
		
		_version = _jsonObject.getDouble("version");
		_handler = _jsonObject.getString("handler");
		
		JSONArray array = _jsonObject.getJSONArray("content");
		
		for (int i = 0; i < array.length(); i++) {
			JSONObject content = (JSONObject) array.get(i);
			_contents.add(_factory.create(content.getString("type"), content.getJSONObject("object")));
		}
	}
	
	public String getJsonString() {
		return _jsonObject.toString();
	}
}
