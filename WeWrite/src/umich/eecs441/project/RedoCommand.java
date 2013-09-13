package umich.eecs441.project;
import android.util.Log;

public class RedoCommand implements AbstractCommand{
	
	int client;
	
	public RedoCommand () {
		client = Client.getInstance().getClient();
	}
	
	public void execute() {
		Log.i("RedoCommand", "execute");
		/*
		 * send to the server 
		 */
	}
	
	/*
	 * no other methods used
	 */
	public void undo() {}
	public void unwind() {}
	public void rewind() {}

	@Override
	public int getClient() {
		return client;
	}
	
}
