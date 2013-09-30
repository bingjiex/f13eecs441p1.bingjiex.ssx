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
	private HashMap<Long, Vector<AbstractCommand> > redoMap = null;
	
	protected RedoTrack () {
		redoMap = new HashMap<Long, Vector<AbstractCommand> >();
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
	public void  addClient (Long client) {
		redoMap.put(client, new Vector<AbstractCommand> () );
	}
	
	/**
	 * get the specific redolist
	 * @param client
	 * @return
	 */
	public Vector<AbstractCommand> getRedoList (Long client) {
		return redoMap.get(client);
	}
	
	
	/**
	 * Check if the redolist has event
	 * @param client
	 * @return
	 */
	public boolean isEmpty (Long client) {
		return redoMap.get(client).isEmpty();
	}
	
	/**
	 * clear the redolist of client. When there is a new action come
	 * @param client
	 */
	public void clearRedoList (Long client) {
		redoMap.get(client).clear();
	}
	
	
}
