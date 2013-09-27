package umich.eecs441.project;

import umich.eecs441.project.proto.CursorCommandBuf.CursorCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

public class CursorCommand implements AbstractCommand {
	
	private int client;
	
	private int movement;
	
	private int submissionID;
	
	
	/**
	 * the current text on the edit text
	 */
	private CursorWatcher text;
	
	// for receice command
	public CursorCommand (int m, int c, CursorWatcher currentText) {
		client = c;
		movement = m;
		text = currentText;
	}
	
	// for local operation
	public CursorCommand (int m, CursorWatcher currentText) {
		client = (int)OnlineClient.getInstance().getClientID();
		movement = m;
		text = currentText;
	}
	
	// TODO protocol buffer needs client movement;
	
	// TODO add another constructor to accept event and push into stack
	/*public CursorCommand (ProtocolBufferClass object) {
		client = object.getClient();
		movement = object.getMovement();
		submissionID = object.getSubmissionID();
		CursorTrack.getInstance() = CursorTrack.getInstance();
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
	
	public void setSubmissionID () {
		submissionID = -1;
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
		
		int currentPos = CursorTrack.getInstance().getCursor(client);
		if (movement<0 && currentPos+movement <= 0) 
			CursorTrack.getInstance().moveCursor(client, -currentPos);
		else if (movement>0 && currentPos+movement >= text.getText().toString().length())  
			CursorTrack.getInstance().moveCursor(client, text.getText().toString().length()-currentPos);
		else CursorTrack.getInstance().moveCursor(client, movement);
		Log.i("CursorCommand", "move" + String.valueOf(movement));
	}
	
	public void unwind() {
		Log.i("CursorCommand", "unwind");
		
		Log.i("Cursor moving", "from " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
		CursorTrack.getInstance().moveCursor(client, -movement);
		
		Log.i("Cursor moving", "to " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
	}
	
	public void rewind() {
		Log.i("CursorCommand", "rewind");
		CursorTrack.getInstance().moveCursor(client, movement);
	}
	
	
}