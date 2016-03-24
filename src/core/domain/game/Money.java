package core.domain.game;

import core.service.PrefixMultiplier;

public class Money {
	
	private int _value;
	private PrefixMultiplier _prefixMultiplier;
	
	public Money(int value, PrefixMultiplier prefixMultiplier) {
		_value = value;
		_prefixMultiplier = prefixMultiplier;
	}
	
	public int getValue() {
		return _value;
	}
	
	public PrefixMultiplier getPrefixMultiplier() {
		return _prefixMultiplier;
	}
}
