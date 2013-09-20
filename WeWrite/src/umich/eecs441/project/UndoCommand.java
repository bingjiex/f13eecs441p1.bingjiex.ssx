package umich.eecs441.project;

import android.util.Log;

public class UndoCommand implements AbstractCommand{
	
	//TODO protocol buffer needs client 
	
	int client;
	
	public UndoCommand () {
		client = Client.getInstance().getClient();
	}
	
	public void execute() {
		Log.i("UndoCommand", "execute");
		/*
		 * send to the server 
		 */
	}
	
	/*
	 * no other methods used
	 */
	public void unwind() {}
	public void rewind() {}

	@Override
	public int getClient() {
		return client;
	}
	
}
