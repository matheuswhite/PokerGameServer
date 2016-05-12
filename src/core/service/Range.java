package core.service;

public class Range {

	private int _initialIndex;
	private int _finalIndex;
	
	public Range() {
		_initialIndex = 0;
		_finalIndex = 0;
	}
	
	public Range(int initialIndex, int finalIndex) {
		_initialIndex = initialIndex;
		_finalIndex = finalIndex;
	}
	
	public int getInitialIndex() {
		return _initialIndex;
	}
	
	public int getFinalIndex() {
		return _finalIndex;
	}
}
