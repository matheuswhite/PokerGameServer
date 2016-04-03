package core.domain.game;

import core.service.PrefixMultiplier;

public class Money {
	
	private double _value;
	private PrefixMultiplier _prefixMultiplier;
	
	public Money() {
		_value = 0;
		_prefixMultiplier = PrefixMultiplier.NONE;
	}
	
	public Money(double value, PrefixMultiplier prefixMultiplier) {
		if (_value >= 1000)
			throw new IllegalArgumentException("The value must be less that 1000");
		
		_value = value;
		_prefixMultiplier = prefixMultiplier;
	}
	
	public double getValue() {
		return _value;
	}
	
	public PrefixMultiplier getPrefixMultiplier() {
		return _prefixMultiplier;
	}
	
	public void addMoney(Money money) {
		if (_value == 1000 && _prefixMultiplier.equals(PrefixMultiplier.TERA))
			throw new IllegalArgumentException("The max value of money has been reached");
		
		double otherValue = 0;
		if (_prefixMultiplier.getValue() < money.getPrefixMultiplier().getValue()) {
			otherValue = _value / Math.pow(10, money.getPrefixMultiplier().getValue() - _prefixMultiplier.getValue());   	
			_value = otherValue + money.getValue();
			_prefixMultiplier = money._prefixMultiplier;
		}
		else {
			otherValue = money.getValue() / Math.pow(10, _prefixMultiplier.getValue() - money.getPrefixMultiplier().getValue());
			_value += otherValue;
		}
		
		if (_value >= 1000) {
			_value /= 1000;
			switch (_prefixMultiplier) {
			case NONE:
				_prefixMultiplier = PrefixMultiplier.KILO;
				break;
			case KILO:
				_prefixMultiplier = PrefixMultiplier.MEGA;
				break;
			case MEGA:
				_prefixMultiplier = PrefixMultiplier.GIGA;
				break;
			case GIGA:
				_prefixMultiplier = PrefixMultiplier.TERA;
				break;
			case TERA:
				_value = 1000;
				break;
			default:
				break;
			}
		}
	}

	
	public void removeMoney(Money money) {
		if (_value == 0)
			throw new IllegalArgumentException("The value of money already is minimum");
		
		if (_prefixMultiplier.getValue() < money.getPrefixMultiplier().getValue()) {
			_value = 0;
			_prefixMultiplier = PrefixMultiplier.NONE;
		}
		else {
			double otherValue = money.getValue() / Math.pow(10, _prefixMultiplier.getValue() - money.getPrefixMultiplier().getValue());
			_value -= otherValue;
		}
	}
}
