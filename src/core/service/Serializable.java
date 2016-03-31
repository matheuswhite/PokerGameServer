package core.service;

public interface Serializable {

	public String toJSON();
	public Serializable toObject(String json);
	public String getType();
}
