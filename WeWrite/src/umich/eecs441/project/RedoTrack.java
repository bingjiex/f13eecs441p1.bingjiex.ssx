package umich.eecs441.project;

import java.util.*;



/**
 * This class is track all the redo list of every client so that the clean can be very fast
 * It is a singleton
 * @author picc
 *
 */
public class RedoTrack {
	
	private static RedoTrack instance = null;
	
	// mapping from the client instance to the vector contains the redolist
	private HashMap<Integer, Vector<AbstractCommand> > redoMap = null;
	
	protected RedoTrack () {
		redoMap = new HashMap<Integer, Vector<AbstractCommand> >();
	}
	
	public static RedoTrack getInstance () {
		if (instance == null)
			instance = new RedoTrack();
		return instance;
	}

	/**
	 * add a new client into the vector
	 * @param client
	 */
	public void  addClient (int client) {
		redoMap.put(client, new Vector<AbstractCommand> () );
	}
	
	/**
	 * get the specific redolist
	 * @param client
	 * @return
	 */
	public Vector<AbstractCommand> getRedoList (int client) {
		return redoMap.get(client);
	}
	
	
	/**
	 * Check if the redolist has event
	 * @param client
	 * @return
	 */
	public boolean isEmpty (int client) {
		return redoMap.get(client).isEmpty();
	}
	
	/**
	 * clear the redolist of client. When there is a new action come
	 * @param client
	 */
	public void clearRedoList (int client) {
		redoMap.get(client).clear();
	}
	
	
	
	
	
	
}
