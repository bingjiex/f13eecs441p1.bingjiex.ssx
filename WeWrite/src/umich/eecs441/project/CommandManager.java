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
			Log.i("command in Stack", "name " + commandStack.elementAt(i).getClass().toString());
		}
		
		Vector <AbstractCommand> temp = new Vector<AbstractCommand>();
		
		for (int i = commandStack.size() - 1; i >= 0; i--){
			// current command
			AbstractCommand tempCommand = commandStack.elementAt(i);
			
			// unwind current command
			tempCommand.unwind();
			
			// delete command
			commandStack.remove(i);
			
			// when it is the client operation and the opertion is not undo
			if (tempCommand.getClient() == cmd.getClient() && !(tempCommand instanceof UndoCommand)) {
				// the one that should be undone
				// put into the redoList
				redoList.add(tempCommand);
				// add the obtained undo command to the list
				commandStack.add(cmd);
				break;
			}
			// else put into temp array
			temp.add(tempCommand);
		}
		for (int i = temp.size() - 1; i >= 0; i--) {
			temp.elementAt(i).rewind();
			commandStack.add(temp.elementAt(i));
		}
	}

	public void redo() {
		
	}
	

}
