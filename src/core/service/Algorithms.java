package core.service;

public class Algorithms {

	private static Algorithms _instance;
	
	private Algorithms() {
		
	}
	
	public static Algorithms Instance() {
		if (_instance == null) {
			inicializeInstance();
		}
		return _instance;
	}

	private static synchronized void inicializeInstance() {
		if (_instance == null) {
			_instance = new Algorithms();
		}
	}
}
