package umich.eecs441.project;

import java.util.Vector;

import android.util.Log;

/**
 * Singleton for the CommandManager
 * @author picc
 *
 */


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
		// !! if there is no user in the map
		// add in cursor map and redo map
		CursorTrack.getInstance().addClient(cmd.getClient());
		CursorTrack.getInstance().addClient(cmd.getClient());
		if (cmd instanceof UndoCommand) {
			undo(cmd);
		} else if (cmd instanceof RedoCommand) {
			redo(cmd);
		} else {
			if (cmd.getSubmissionID() == -1) {
				// go into stack
				commandStack.add(cmd);
				cmd.rewind();
			} else {
				
				Vector<AbstractCommand> temp = new Vector<AbstractCommand> ();
				
				for (int i = commandStack.size() - 1; i >= 0; i--) {
					if (commandStack.elementAt(i).getSubmissionID() == cmd.getSubmissionID()) {
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
				// add to commandStack
				commandStack.add(cmd);
				
			}
		}
	}
	
	
	
	/**
	 * 
	 * @param client, the client that operation should be undo
	 */
	
	
	public void undo(AbstractCommand cmd) {
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack before undo", "name " + commandStack.elementAt(i).getClass().toString());
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
			
			// unwind current command
			tempCommand.unwind();
			
			// delete command
			commandStack.remove(i);
			
			// when it is the client operation and the operation is not undo
			if (tempCommand.getClient() == cmd.getClient() && !(tempCommand instanceof UndoCommand)) {
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
			Log.i("redo's unwind", String.valueOf(CursorTrack.getInstance().getCursor(Client.getInstance().getClient())));
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
	public void newCommandHandling(int client) {
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
