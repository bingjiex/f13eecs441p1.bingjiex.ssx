package umich.eecs441.project;

import java.util.*;

import android.util.Log;

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
	private HashMap <Long, Integer> cursorMap = null;
	
	
	/**
	 * constructor
	 */
	protected CursorTrack() {
		cursorMap = new HashMap <Long, Integer>();
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
	 * and move the current client by length
	 * @param client
	 * @return 
	 */
	public void moveRight(Long client, Integer length){
		for (Map.Entry<Long, Integer> entry : cursorMap.entrySet()) {
			 if (entry.getValue() > cursorMap.get(client)) {
				 cursorMap.put(entry.getKey(), entry.getValue() + length);
			 }
		}
		cursorMap.put(client, cursorMap.get(client) + length);
	}
	/**
	 * move all the cursors that after this client cursors left by length
	 * since delete can be a chunk of characters
	 * @param client, length
	 * @return 
	 */
	// canbe used in insert and remove
	public void moveLeft(Long client, Integer length){
		for (Map.Entry<Long, Integer> entry : cursorMap.entrySet()) {
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
	public void moveCursor(Long client, Integer movement) {
		cursorMap.put(client, cursorMap.get(client) + movement);
	}
	
	/**
	 * get Cursor
	 * @param client
	 * @return
	 */
	public int getCursor (Long client) {
		Log.i("CursorTrack getCursor", String.valueOf(client));
		
		return cursorMap.get(client);
	}
	
	
	/**
	 * add a new client
	 * @param client
	 */
	public void addClient (Long client) {
		cursorMap.put(client, 0);
	}
	
	/*
	 * get CursorMap
	 * 
	 */
	public HashMap<Long, Integer> getCursorMap() {
		return cursorMap;
	}
	
}
