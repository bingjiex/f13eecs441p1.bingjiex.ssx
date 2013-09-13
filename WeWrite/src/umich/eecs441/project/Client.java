package umich.eecs441.project;

/**
 * my own client, use int temperarily
 * @author picc
 *
 */

public class Client {
	
	private static Client instance = null;
	
	private int client;
	
	protected Client() {
		client = 0;
	}
	
	public static Client getInstance() {
		if (instance == null)
			instance = new Client();
		return instance;
	}
	
	public int getClient () {
		return client;
	}
	
}
