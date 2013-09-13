package umich.eecs441.project;

import java.util.*;

/**
 * Singleton for all the clients cursor position
 * 
 * @author Shaoxiang Su, Bingjie Xu
 *
 */
public class CursorTrack {
	/**
	 * the hashmap that keeps track the client cursor position pair
	 */
	private HashMap <Integer, Integer> cursorMap = null;
	/**
	 * constructor
	 */
	public CursorTrack() {
		cursorMap = new HashMap <Integer, Integer>();
	}
	/**
	 * move all the cursors that after this client cursors right by length
	 * @param client
	 * @return 
	 */
	public void moveRight(Integer client, Integer length){
		for (Map.Entry<Integer, Integer> entry : cursorMap.entrySet()) {
			 if (entry.getValue() >= client) {
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
			 if (entry.getValue() >= client) {
				 cursorMap.put(entry.getKey(), entry.getValue() - length);
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
	
	public int getCursor (Integer client) {
		return cursorMap.get(client);
	}
	

}
