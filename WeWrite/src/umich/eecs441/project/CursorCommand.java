package umich.eecs441.project;

import umich.eecs441.project.proto.CursorCommandBuf.CursorCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

public class CursorCommand implements AbstractCommand {
	
	private int client;
	
	private int movement;
	
	private int submissionID;
	
	private CursorTrack currentTrack;
	
	public CursorCommand (int m) {
		client = Client.getInstance().getClient();
		movement = m;
		currentTrack = CursorTrack.getInstance();
	}
	
	// TODO protocol buffer needs client movement;
	
	// TODO add another constructor to accept event and push into stack
	/*public CursorCommand (ProtocolBufferClass object) {
		client = object.getClient();
		movement = object.getMovement();
		submissionID = object.getSubmissionID();
		currentTrack = CursorTrack.getInstance();
	}
	*/
	
	
	public int getClient(){
		return client;
	}
	public int getMovement() { 
		return movement;
	}
	
	public int getSubmissionID() {
		return submissionID;
	}
	
	public void execute() {
		// TODO initialize submissionID
		/*
		 * send request
		 */
		CursorCommandBufObj.Builder builder = CursorCommandBufObj.newBuilder();
		builder.setClientID(client);
		builder.setMovement(movement);
		CursorCommandBufObj object = builder.build();

		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				submissionID = OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "CursorCommand");
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
		
		
		
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