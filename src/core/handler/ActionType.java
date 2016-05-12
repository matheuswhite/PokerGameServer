package core.handler;

public enum ActionType {
	BET("BET"),
	CALL("CALL"),
	FOLD("FOLD"), 
	BUY_IN("BUY_IN");
	
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
