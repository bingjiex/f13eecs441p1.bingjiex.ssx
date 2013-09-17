package umich.eecs441.project;

/**
 * my own client, use int temperarily
 * @author picc
 *
 */

public class Client {
	
	private static Client instance = null;
	
	// TODO expected to change to client type
	private int client;
	
	// initial with 0, tells if the two lists contains actions
	private int commandStackContains;
	
	// !!! We still need to track the redo list since the instant operation will not
	// put the command into redo list
	private int redoListContains;
	
	public int getRedoListContains() {
		return redoListContains;
	}

	public void setRedoListContains(int redoListContains) {
		this.redoListContains = redoListContains;
	}

	// TODO expected to add constructor with client type argument
	protected Client() {
		client = 0;
		
		commandStackContains = 0;
	}
	
	public static Client getInstance() {
		if (instance == null)
			instance = new Client();
		return instance;
	}
	
	public int getClient () {
		return client;
	}

	public int getCommandStackContains() {
		return commandStackContains;
	}

	public void setCommandStackContains(int commandStackContains) {
		this.commandStackContains = commandStackContains;
	}

}