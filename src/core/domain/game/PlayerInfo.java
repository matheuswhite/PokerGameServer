package core.domain.game;

import core.service.PrefixMultiplier;

public class PlayerInfo {
	
	private Card[] _hand;
	private Money _moneyPlayer;
	
	public PlayerInfo() {
		_hand = new Card[2];
		_moneyPlayer = new Money(0, PrefixMultiplier.NONE);
	}
	
	public Card[] getHand() {
		return _hand;
	}
	
	public Money getMoneyPlayer() {
		return _moneyPlayer;
	}
	public void removeMoney(Money money) {
		_moneyPlayer.removeMoney(money);
	}
	public void addMoney(Money money) {
		_moneyPlayer.addMoney(money);
	}
}
