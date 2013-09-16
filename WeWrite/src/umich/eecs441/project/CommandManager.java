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
	private Vector<AbstractCommand> redoList = null;
	
	
	protected CommandManager() {
		commandStack = new Vector<AbstractCommand> ();
		redoList = new Vector<AbstractCommand> ();
	}
	protected CommandManager (Vector<AbstractCommand> v1, Vector<AbstractCommand> v2) {
		commandStack = v1;
		redoList = v2;
	}
	
	// get instance without parameter
	public static CommandManager getInstance() {
		if (instance == null)
			instance = new CommandManager();
		return instance;
	}
	
	
	public static CommandManager getInstance(Vector<AbstractCommand> v1, Vector<AbstractCommand> v2) {
		if (instance == null)
			instance = new CommandManager(v1, v2);
		return instance;
	}
	
	
	public void storeCommand (AbstractCommand cmd) {
		commandStack.add(cmd);
		Log.i("commandStack size is ", String.valueOf(commandStack.size()));
	}
	
	/**
	 * 
	 * @param client, the client that operation should be undo
	 */
	
	
	public void undo(AbstractCommand cmd) {
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack before undo", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		for (int i = redoList.size() - 1; i >= 0; i--) {
			Log.i("command in redolist before undo", redoList.elementAt(i).getClass().toString());
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
				redoList.add(tempCommand);
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
		
		for (int i = redoList.size() - 1; i >= 0; i--) {
			Log.i("command in redolist after undo", redoList.elementAt(i).getClass().toString());
		}
	}

	public void redo(AbstractCommand cmd) {
		
		for (int i = commandStack.size() - 1; i >= 0; i--) {
			Log.i("command in Stack before redo", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		for (int i = redoList.size() - 1; i >= 0; i--) {
			Log.i("command in redolist before redo", redoList.elementAt(i).getClass().toString());
		}
		
		// undo temp vector for save the top of the command stack
		Vector<AbstractCommand> temp = new Vector<AbstractCommand>();
		
		// TODO keep a value to see if the redo list contains the client's operation
		// vector is ordered remove
		
		// TODO if there is a need for memory allocation. and how to copy
		AbstractCommand redoCommand = null;
		for (int i = redoList.size() - 1; i >= 0; i--) {
			if (redoList.elementAt(i).getClient() == cmd.getClient()) {
				redoCommand = redoList.elementAt(i);
				redoList.removeElementAt(i);
				break;
			}
		}
		Log.i("redoList size ", String.valueOf(redoList.size()));
		
		int undoNum = Client.getInstance().getRedoListContains();
		
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

				if (undoNum == 1) {
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
		
		for (int i = redoList.size() - 1; i >= 0; i--) {
			Log.i("command in redolist after redo", redoList.elementAt(i).getClass().toString());
		}
		
	}
	
}
