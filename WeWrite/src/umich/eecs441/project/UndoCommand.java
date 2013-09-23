package umich.eecs441.project;

import umich.eecs441.project.proto.UndoCommandBuf.UndoCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

public class UndoCommand implements AbstractCommand{
	
	//TODO protocol buffer needs client 
	
	int client;
	
	public UndoCommand () {
		client = Client.getInstance().getClient();
	}
	
	public void execute() {
		UndoCommandBufObj.Builder builder = UndoCommandBufObj.newBuilder();
		builder.setClientID(client);
		
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
	public int getClient() {
		return client;
	}

	// No need for submissionID
	@Override
	public int getSubmissionID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
