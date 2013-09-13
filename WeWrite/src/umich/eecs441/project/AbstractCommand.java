package umich.eecs441.project;


/**
 * Abstract interface for the general command
 * @author Bingjie Xu, Shaoxiang Su
 */
public interface AbstractCommand {
	
	/**
	 * Send request to server
	 */
	public void execute();
	/**
	 * Undo the change of the command locally
	 * Used when the client obtains a local operation, 
	 * then undo all the operations start from this operation to the unconfirmed one.
	 * All the operations are stored in the unwind list
	 */
	public void unwind();
	
	/**
	 * Redo the change of the command locally
	 * Used when the client obtains a local operation, 
	 * then redo all the operations start from the unconfirmed one to this obtained one.
	 * All the operations are stored in the rewind list
	 */
	public void rewind();
	
	/**
	 * find the client
	 * @return
	 */
	
	public int getClient();

}
