package umich.eecs441.project;

import java.util.Vector;

import android.util.Log;

/**
 * Singleton for the CommandManager
 * @author picc
 *
 */

// TODO: think about if enough time should be reserved for a joined-in client finish all the event?
// TODO: if there is a new event comes, we should consider to send the message.
public class CommandManager {
	
	private static CommandManager instance = null;
	
	private Vector<AbstractCommand> commandStack = null; 
	
	
	protected CommandManager() {
		commandStack = new Vector<AbstractCommand> ();
	}
	protected CommandManager (Vector<AbstractCommand> v1) {
		commandStack = v1;
	}
	
	// get instance without parameter
	public static CommandManager getInstance() {
		if (instance == null)
			instance = new CommandManager();
		return instance;
	}
	
	
	public static CommandManager getInstance(Vector<AbstractCommand> v1) {
		if (instance == null)
			instance = new CommandManager(v1);
		return instance;
	}
	
	// store an unconfirmed command
	public void storeCommand (AbstractCommand cmd) {
		commandStack.add(cmd);
		Log.i("commandStack size is ", String.valueOf(commandStack.size()));
	}
	
	// receive a command
	public void receiveCommand (AbstractCommand cmd) {
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("CommandManager receiveCommand stack", "Command name: " + commandStack.elementAt(i).toString() + "\n" + 
														 "Command owner: " + commandStack.elementAt(i).getClient() + "\n" + 
					 									 "Command submissionID: " + commandStack.elementAt(i).getSubmissionID());			
		}
		
		Log.i("CommandManager receiveCommand", "cmd: " + cmd.toString());
		// !! if there is no user in the map
		// add in cursor map and redo map
		if (!CursorTrack.getInstance().getCursorMap().containsKey(cmd.getClient())) {
			Log.i("CommandManager receiveCommand", "client doesnot exist");
			CursorTrack.getInstance().addClient(cmd.getClient());
			RedoTrack.getInstance().addClient(cmd.getClient());
		}
		if (cmd instanceof UndoCommand) {
			undo(cmd);
		} else if (cmd instanceof RedoCommand) {
			redo(cmd);
		} else {
			Log.i("CommandManager receiveCommand", "Command ID " + String.valueOf(cmd.getSubmissionID()));
			if (cmd.getSubmissionID() == -1) {
				Log.i("CommandManager receiveCommand", "other's command");
				// go into stack
				commandStack.add(cmd);
				cmd.rewind(); 
			} else {
				Log.i("CommandManager receiveCommand", "owned command");
				Vector<AbstractCommand> temp = new Vector<AbstractCommand> ();
				
				for (int i = commandStack.size() - 1; i >= 0; i--) {
					if (commandStack.elementAt(i).getSubmissionID() == cmd.getSubmissionID()) {
						Log.i("CommandManager receiveCommand", "find the command with the same ID");
						commandStack.elementAt(i).unwind();
						commandStack.remove(i); 
						break;
					} else {
						AbstractCommand tempCommand = commandStack.elementAt(i);
						commandStack.elementAt(i).unwind();
						commandStack.remove(i);
						temp.add(tempCommand);
					}
				}
				for (int i = temp.size() - 1; i >= 0; i--) {
					temp.elementAt(i).rewind();
					commandStack.add(temp.elementAt(i));
				}
				
				
				cmd.rewind();
				// set submissionID -1
				// add to commandStack
				cmd.setSubmissionID(-1);
				Log.i("CommandManager receiveCommand", "setSubmissionID " + String.valueOf(cmd.getSubmissionID()));
				commandStack.add(cmd);
				
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param client, the client that operation should be undo
	 */
	
	
	public void undo(AbstractCommand cmd) {
		
		
		Log.i("CommandManager undo", "command client: " + String.valueOf(cmd.getClient()));
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack before undo", "name " + commandStack.elementAt(i).getClass().toString() + "\n" + 
												  "clientID: " + String.valueOf(commandStack.elementAt(i).getClient()) + "\n" + 
					 							  "submissionID: " + String.valueOf(commandStack.elementAt(i).getSubmissionID()));
		
		}
		
		for (int i = RedoTrack.getInstance().getRedoList(cmd.getClient()).size() - 1; i >= 0; i--) {
			Log.i("command in redolist before undo", 
					RedoTrack.getInstance().getRedoList(cmd.getClient()).elementAt(i).getClass().toString());
		}
		
		Vector <AbstractCommand> temp = new Vector<AbstractCommand>();
		
		// TODO record how many commands the current client has. If the command number is 0,
		// means the current client has no command to redo and return directly
		
		// TODO notice that if the undo is the same client and the client action is unconfirmed
		for (int i = commandStack.size() - 1; i >= 0; i--){
			// current command
			AbstractCommand tempCommand = commandStack.elementAt(i);
			
			Log.i("traverse command for undo", "name " + commandStack.elementAt(i).getClass().toString() + "\n" + 
					  "clientID: " + String.valueOf(commandStack.elementAt(i).getClient()) + "\n" + 
					  "submissionID: " + String.valueOf(commandStack.elementAt(i).getSubmissionID()));

			
			
			// unwind current command
			tempCommand.unwind();
			
			// delete command
			commandStack.remove(i);
			

			
			// when it is the client operation and the operation is not undo
			// !!! and the command is confirmed
			if (tempCommand.getClient() == cmd.getClient() && !(tempCommand instanceof UndoCommand) && tempCommand.getSubmissionID() == -1) {
				Log.i("CommandManager undo find command", "go into the condition scope");
				// the one that should be undone
				// put into the redoList
				RedoTrack.getInstance().getRedoList(cmd.getClient()).add(tempCommand);
				//! Notice that the redoList is increasing, later one is at behind!!!!!
				// add the obtained undo command to the list
				commandStack.add(cmd);
				break;
			}
			// else put into temp array
			temp.add(tempCommand);
		}
		// rewind all the command back.
		for (int i = temp.size() - 1; i >= 0; i--) {
			temp.elementAt(i).rewind();
			commandStack.add(temp.elementAt(i));
		}
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack after", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		for (int i = RedoTrack.getInstance().getRedoList(cmd.getClient()).size() - 1; i >= 0; i--) {
			Log.i("command in redolist after undo", 
					RedoTrack.getInstance().getRedoList(cmd.getClient()).elementAt(i).getClass().toString());
		}
	}

	
	// CHANGE: for every client make a redo list for them, if there is a new fresh
	// command which is not undo or redo, flush the specific redolist and remove all
	// the undo in the commandStack of thet specific client
	public void redo(AbstractCommand cmd) {
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack before redo", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		for (int i = RedoTrack.getInstance().getRedoList(cmd.getClient()).size() - 1; i >= 0; i--) {
			Log.i("command in redolist before redo", 
					RedoTrack.getInstance().getRedoList(cmd.getClient()).elementAt(i).getClass().toString());
		}
		
		// undo temp vector for save the top of the command stack
		Vector<AbstractCommand> temp = new Vector<AbstractCommand>();
		
		// TODO delete the redoContains in the Client class of the current client track
		// no need for it
		AbstractCommand redoCommand = null;
		/*for (int i = redoList.size() - 1; i >= 0; i--) {
			if (redoList.elementAt(i).getClient() == cmd.getClient()) {
				redoCommand = redoList.elementAt(i);
				redoList.removeElementAt(i);
				break;
			}
		}*/
		// check if the redo List has element
		// actually there is no need to check, because before sending the request
		// the button listener will track this
		if (!RedoTrack.getInstance().isEmpty(cmd.getClient())){
			// get the last element
			redoCommand = RedoTrack.getInstance().getRedoList(cmd.getClient()).lastElement();
			// remove the last element
			RedoTrack.getInstance().getRedoList(cmd.getClient()).removeElementAt(
					RedoTrack.getInstance().getRedoList(cmd.getClient()).size() - 1);
		}
		Log.i("redoList size ", String.valueOf(
				RedoTrack.getInstance().getRedoList(cmd.getClient()).size()));
		
		
		// the undo tag in the stack is just the number
		// NOTICE: if the redolist is empty, redo command will not be sent to server
		int undoNum = RedoTrack.getInstance().getRedoList(cmd.getClient()).size();
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			// current command
			AbstractCommand tempCommand = commandStack.elementAt(i);
			
			// unwind current command
			tempCommand.unwind();
			Log.i("redo's unwind", String.valueOf(cmd.getClient()));
			// delete command
			commandStack.remove(i);
			
			
			
			// when it is the client operation and the operation is undo
			if (tempCommand.getClient() == cmd.getClient() && (tempCommand instanceof UndoCommand)) {

				if (undoNum == 0) {
					// put the command back to the stack
					redoCommand.rewind();
					commandStack.add(redoCommand);
					break;
				}
				else {
					undoNum --;
				}
			}
			// else put into temp array
			temp.add(tempCommand);
		}
		// rewind all the command back.
		for (int i = temp.size() - 1; i >= 0; i--) {
			temp.elementAt(i).rewind();
			commandStack.add(temp.elementAt(i));
		}
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack after redo", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		for (int i = RedoTrack.getInstance().getRedoList(cmd.getClient()).size() - 1; i >= 0; i--) {
			Log.i("command in redolist after redo", 
					RedoTrack.getInstance().getRedoList(cmd.getClient()).elementAt(i).getClass().toString());
		}
		
	}
	
	/**
	 * used when a client has some action instead of undo and redo
	 * clear the redoList
	 * remove all the undo tag in the stack
	 * @param client
	 */
	public void newCommandHandling(long client) {
		if(!RedoTrack.getInstance().isEmpty(client)) {
			Log.i("redoList size before newCommandHandling", String.valueOf(RedoTrack.getInstance().getRedoList(client).size()));
			for (int i = commandStack.size() - 1; i >= 0; i--) {
				Log.i("command in Stack after before newCommandHandling", "name " + commandStack.elementAt(i).getClass().toString());
			}
			for (int i = 0; i < commandStack.size(); i++){
				if (commandStack.elementAt(i).getClient() == client &&
						(commandStack.elementAt(i) instanceof UndoCommand)) {
					commandStack.removeElementAt(i);
					i--;
				}
			}
			RedoTrack.getInstance().clearRedoList(client);
			for (int i = commandStack.size() - 1; i >= 0; i--) {
				Log.i("command in Stack after after newCommandHandling", "name " + commandStack.elementAt(i).getClass().toString());
			}
		}
	}
	
}
