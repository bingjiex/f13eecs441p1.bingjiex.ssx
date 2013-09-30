package umich.eecs441.project;

import umich.eecs441.project.proto.CursorCommandBuf.CursorCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

public class CursorCommand implements AbstractCommand {
	
	private long client;
	
	private int movement;
	
	private int submissionID;
	
	
	/**
	 * the current text on the edit text
	 */
	private CursorWatcher text;
	
	// for receice command
	public CursorCommand (int m, int c) {
		
		client = c;
		movement = m;
		text = TextEditorActivity.getCursorWatcher();
		Log.i("CusorCommand constructor with clientID", "clientID" + String.valueOf(client));
	}
	
	// for local operation
	public CursorCommand (int m) {
		
		client = (int)OnlineClient.getInstance().getClientID();
		movement = m;
		text = TextEditorActivity.getCursorWatcher();
		Log.i("CusorCommand constructor local", "clientID" + String.valueOf(client));
	}
	
	// TODO protocol buffer needs client movement;
	
	// TODO add another constructor to accept event and push into stack
	
	
	public long getClient(){
		return client;
	}
	public int getMovement() { 
		return movement;
	}
	
	public int getSubmissionID() {
		return submissionID;
	}
	
	public void setSubmissionID (int subId) {
		submissionID = subId;
	}
	
	public void execute() {
		
		int currentPos = CursorTrack.getInstance().getCursor(client);
		Log.i("CursorCommand execute()", "currentPos " + String.valueOf(currentPos));
		if (movement<0 && currentPos+movement < 0) {
			
			Log.i("CursorCommand execute()", "exceeds left bound");
			CursorTrack.getInstance().moveCursor(client, -currentPos);
			Log.i("CursorCommand execute()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		} else if (movement>0 && currentPos+movement > text.getText().toString().length()) {
			Log.i("CursorCommand execute()", "exceeds right bound");
			CursorTrack.getInstance().moveCursor(client, text.getText().toString().length()-currentPos);
			Log.i("CursorCommand execute()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
			
		} else {
			Log.i("CursorCommand execute()", "proper");
			CursorTrack.getInstance().moveCursor(client, movement);
			Log.i("CursorCommand execute()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
			
		}
		Log.i("CursorCommand", "move" + String.valueOf(movement));
		
		// TODO initialize submissionID
		/*
		 * send request
		 */
		Log.i("CursorCommand", "execute()");
		CursorCommandBufObj.Builder builder = CursorCommandBufObj.newBuilder();
		builder.setClientID((int)client);
		builder.setMovement(movement);
		CursorCommandBufObj object = builder.build();

		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				
				submissionID = OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "CursorCommand");
				Log.i("CursorCommand getSubmissionID", "submissionID : " + String.valueOf(submissionID));
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	public void unwind() {
		Log.i("CursorCommand", "unwind");
		
		int currentPos = CursorTrack.getInstance().getCursor(client);
		
		Log.i("Cursor moving", "from " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
		if (movement<0 && currentPos-movement > text.getText().toString().length()) {
			Log.i("CursorCommand unwind()", "exceeds right bound");
			CursorTrack.getInstance().moveCursor(client, text.getText().toString().length()-currentPos);
			Log.i("CursorCommand unwind()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		} else if (movement>0 && currentPos+movement < 0) {
			Log.i("CursorCommand unwind()", "exceeds left bound");
			CursorTrack.getInstance().moveCursor(client,-currentPos);
			Log.i("CursorCommand unwind()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		} else {
			Log.i("CursorCommand execute()", "proper");
			CursorTrack.getInstance().moveCursor(client, -movement);
			Log.i("CursorCommand execute()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
			
		}
		
		Log.i("Cursor moving", "to " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		
	}
	
	public void rewind() {
		Log.i("CursorCommand", "rewind");
		
		int currentPos = CursorTrack.getInstance().getCursor(client);
		
		if (movement<0 && currentPos+movement < 0) {
			
			Log.i("CursorCommand rewind()", "exceeds left bound");
			CursorTrack.getInstance().moveCursor(client, -currentPos);
			Log.i("CursorCommand rewind()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		} else if (movement>0 && currentPos+movement > text.getText().toString().length()) {
			Log.i("CursorCommand rewind()", "exceeds right bound");
			CursorTrack.getInstance().moveCursor(client, text.getText().toString().length()-currentPos);
			Log.i("CursorCommand rewind()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		} else {
			Log.i("CursorCommand rewind()", "proper");
			CursorTrack.getInstance().moveCursor(client, movement);
			Log.i("CursorCommand rewind()", "current position after " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
			
		}
	}
	
	public int getTrackMapSize() {
		return 0;
	}
	
}