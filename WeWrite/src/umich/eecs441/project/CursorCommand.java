package umich.eecs441.project;

import android.util.Log;

public class CursorCommand implements AbstractCommand {
	
	private int client;
	
	
	private int movement;
	private CursorTrack currentTrack;
	private CommandManager currentManager;
	
	public CursorCommand (int c, int m, CursorTrack cT, CommandManager cM) {
		movement = m;
		currentTrack = cT;
		currentManager = cM;
	}
	
	public int getClient(){
		return client;
	}
	
	
	public void execute() {
		/*
		 * send request
		 */
		currentTrack.moveCursor(client, movement);
		Log.i("CursorCommand", "move");
	}
	
	public void unwind() {
		Log.i("CursorCommand", "unwind");
		currentTrack.moveCursor(client, -movement);
	}
	
	public void rewind() {
		Log.i("CursorCommand", "rewind");
		currentTrack.moveCursor(client, movement);
	}
	
	
}