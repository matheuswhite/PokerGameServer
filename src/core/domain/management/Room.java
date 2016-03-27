package core.domain.management;

import java.util.ArrayList;
import java.util.List;

import core.domain.game.MatchInfo;
import core.domain.game.Money;

public class Room {

	private long _id;
	private List<Long> _listOfClientsId;
	private MatchInfo _gameInfo;
	public final static int ROOM_CAPACITY = 9;
	
	public Room(long id, Money smallBlindValue, Money minimumBuyIn) {
		_id = id;
		
		_gameInfo = new MatchInfo(smallBlindValue, minimumBuyIn);
		_listOfClientsId = new ArrayList<Long>(ROOM_CAPACITY);
	}
	
	public long getId() {
		return _id;
	}
	
	public boolean hasClient(long id) {
		return _listOfClientsId.contains(id);
	}
	
	public void addClient(long id) throws Exception {
		if (_listOfClientsId.size() >= ROOM_CAPACITY)
			throw new Exception("The max capacity of the room has been reached!");
		_listOfClientsId.add(id);
	}
	
	public void removeClient(long id) {
		_listOfClientsId.remove(id);
	}
	
	public List<Long> getClients() {
		return _listOfClientsId;
	}
	
	public MatchInfo getGameInfo() {
		return _gameInfo;
	}
}
