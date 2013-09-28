package umich.eecs441.project;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import umich.eecs441.project.proto.RedoCommandBuf.RedoCommandBufObj;
import android.util.Log;

public class RedoCommand implements AbstractCommand{
	
	// TODO protocol buffer needs client
	
	long client;
	
	public RedoCommand (int c) {
		client = c;
	}
	
	
	// for local user to use
	public RedoCommand () {
		client = (int)OnlineClient.getInstance().getClientID();
	}
	
	public void execute() {
		RedoCommandBufObj.Builder builder = RedoCommandBufObj.newBuilder();
		builder.setClientID((int)client);
		
		RedoCommandBufObj object = builder.build();
		
		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "RedoCommand");
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
		
		
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
	public long getClient() {
		return client;
	}
	
	// No need for submissionID
	@Override
	public int getSubmissionID() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	// never use
	public void setSubmissionID (int subId) {
		return;
	}
	
}
