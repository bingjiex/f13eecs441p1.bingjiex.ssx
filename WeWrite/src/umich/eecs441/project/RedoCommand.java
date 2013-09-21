package umich.eecs441.project;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import umich.eecs441.project.proto.RedoCommandBuf.RedoCommandBufObj;
import android.util.Log;

public class RedoCommand implements AbstractCommand{
	
	// TODO protocol buffer needs client
	
	int client;
	
	public RedoCommand () {
		client = Client.getInstance().getClient();
	}
	
	public void execute() {
		RedoCommandBufObj.Builder builder = RedoCommandBufObj.newBuilder();
		builder.setClientID(client);
		
		RedoCommandBufObj object = builder.build();
		
		if (OnlineClient.getInstance(null, null).getClient().inSession() && 
				OnlineClient.getInstance(null, null).getClient() != null) {
			try {
				OnlineClient.getInstance(null, null).getClient().broadcast(object.toByteArray(), "RedoCommand");
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
