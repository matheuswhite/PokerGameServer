package core.service;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

public class Factory {

	private Map<String, Serializable> _factoryTable;
	
	public Factory() {
		initializeFactoryTable();
	}
	
	private void initializeFactoryTable() {
		_factoryTable = new HashMap<String, Serializable>();
		
		//add empty serializable objects
		_factoryTable.put("", new );
	}

	public void addEntry(String type, Serializable emptyObject) throws Exception {
		if (_factoryTable.containsKey(type))
			throw new Exception("This entry has been added");
		_factoryTable.put(type, emptyObject);
	}
	
	public Serializable create(String type, JSONObject object) {
		if (!_factoryTable.containsKey(type))
			throw new IllegalArgumentException("Factory table not contains " + type);
		return _factoryTable.get(type).toObject(object.toString());
	}

}
