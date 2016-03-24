package core.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class NumberGenerator {

	private List<Long> _listOfIdGenerated;
	private Random _rand;
	
	public NumberGenerator() {
		_listOfIdGenerated = new LinkedList<Long>();
		_rand = new Random();
	}
	
	public long genId() {
		long id = _rand.nextLong();
		while (_listOfIdGenerated.contains(id)) {
			id = _rand.nextLong();
		}
		_listOfIdGenerated.add(id);
		return id;
	}

	public boolean removeId(long id) {
		if (_listOfIdGenerated.contains(id)) {
			_listOfIdGenerated.remove(id);
			return true;
		}
		return false;
	}
}
