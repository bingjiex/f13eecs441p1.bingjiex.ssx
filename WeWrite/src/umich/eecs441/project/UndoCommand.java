package umich.eecs441.project;

import umich.eecs441.project.proto.UndoCommandBuf.UndoCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

public class UndoCommand implements AbstractCommand{
	
	//TODO protocol buffer needs client 
	
	long client;
	
	public UndoCommand (int c) {
		client = c;
	}
	
	// for local
	public UndoCommand () {
		client = (int)OnlineClient.getInstance().getClientID();
	}
	
	public void execute() {
		UndoCommandBufObj.Builder builder = UndoCommandBufObj.newBuilder();
		builder.setClientID((int)client);
		
		UndoCommandBufObj object = builder.build();
		
		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "UndoCommand");
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
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
	public long getClient() {
		return client;
	}

	// No need for submissionID
	@Override
	public int getSubmissionID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	public void setSubmissionID (int subId) {
		return;
	}
	
	public int getTrackMapSize() {
		return 0;
	}
}
