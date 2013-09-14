package umich.eecs441.project;

import android.util.Log;

public class CursorCommand implements AbstractCommand {
	
	private int client;
	
	
	private int movement;
	private CursorTrack currentTrack;
	
	public CursorCommand (int m) {
		client = Client.getInstance().getClient();
		movement = m;
		currentTrack = CursorTrack.getInstance();
	}
	public int getClient(){
		return client;
	}
	public int getMovement() { 
		return movement;
	}
	
	public void execute() {
		/*
		 * send request
		 */
		currentTrack.moveCursor(client, movement);
		Log.i("CursorCommand", "move" + String.valueOf(movement));
	}
	
	public void unwind() {
		Log.i("CursorCommand", "unwind");
		
		Log.i("Cursor moving", "from " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
		currentTrack.moveCursor(client, -movement);
		
		Log.i("Cursor moving", "to " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
	}
	
	public void rewind() {
		Log.i("CursorCommand", "rewind");
		currentTrack.moveCursor(client, movement);
	}
	
	
}