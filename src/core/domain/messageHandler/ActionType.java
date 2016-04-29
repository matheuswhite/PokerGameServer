package core.domain.messageHandler;

public enum ActionType {
	RAISE("RAISE"),
	CHECK("CHECK"),
	CALL("CALL"),
	FOLD("FOLD");
	
	private final String type;       

    private ActionType(String s) {
        type = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : type.equals(otherName);
    }

    public String toString() {
       return this.type;
    }
}
