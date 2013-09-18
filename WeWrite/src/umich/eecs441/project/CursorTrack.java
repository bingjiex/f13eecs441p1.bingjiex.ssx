package umich.eecs441.project;

import java.util.*;

/**
 * Singleton for all the clients cursor position
 * 
 * @author Shaoxiang Su, Bingjie Xu
 *
 */
public class CursorTrack {
	
	
	/*
	 * change back to singleton so no need to pass the instance for every class
	 */
	private static CursorTrack instance = null;
	
	
	/**
	 * the hashmap that keeps track the client cursor position pair
	 */
	private HashMap <Integer, Integer> cursorMap = null;
	
	
	/**
	 * constructor
	 */
	protected CursorTrack() {
		cursorMap = new HashMap <Integer, Integer>();
	}
	/**
	 * still need a constructor with value
	 */
	
	
	
	/**
	 * getInstance without parameter
	 */
	public static CursorTrack getInstance() {
		if (instance == null)
			instance = new CursorTrack();
		return instance;
	}
	
	
	/**
	 * still need a getInstance with parameter
	 */
	
	
	/**
	 * move all the cursors that after this client cursors right by length
	 * @param client
	 * @return 
	 */
	public void moveRight(Integer client, Integer length){
		for (Map.Entry<Integer, Integer> entry : cursorMap.entrySet()) {
			 if (entry.getValue() >= cursorMap.get(client)) {
				 cursorMap.put(entry.getKey(), entry.getValue() + length);
			 }
		}
	}
	/**
	 * move all the cursors that after this client cursors left by length
	 * since delete can be a chunk of characters
	 * @param client, length
	 * @return 
	 */
	public void moveLeft(Integer client, Integer length){
		for (Map.Entry<Integer, Integer> entry : cursorMap.entrySet()) {
			 if (entry.getValue() >= cursorMap.get(client)) {
				 cursorMap.put(entry.getKey(), entry.getValue() - length);
			 }
			 else if (entry.getValue() < cursorMap.get(client) && 
					 entry.getValue() >= cursorMap.get(client) - length) {
				 cursorMap.put(entry.getKey(), cursorMap.get(client) - length);
			 }
		}
	}
	/**
	 * Move cursor of client movement steps
	 * @param client
	 * @param movement
	 */
	public void moveCursor(Integer client, Integer movement) {
		cursorMap.put(client, cursorMap.get(client) + movement);
	}
	
	/**
	 * get Cursor
	 * @param client
	 * @return
	 */
	public int getCursor (Integer client) {
		return cursorMap.get(client);
	}
	
	
	/**
	 * add a new client
	 * @param client
	 */
	public void addClient (int client) {
		cursorMap.put(client, 0);
	}

}
