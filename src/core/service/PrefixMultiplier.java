package core.service;

public enum PrefixMultiplier {
	NONE(0),
	KILO(3),
	MEGA(6),
	GIGA(9),
	TERA(12);
	
	private int _value;
	
	private PrefixMultiplier(int value) {
		_value = value;
	}
	
	public int getValue() {
		return _value;
	}
	
	@Override
	public String toString() {
		String out = "";
		switch (this) {
		case KILO:
			out = "K";
			break;
		case MEGA:
			out = "M";
			break;
		case GIGA:
			out = "G";
			break;
		case TERA:
			out = "T";
			break;
		default:
			break;
		}
		
		return out;
	}
}
