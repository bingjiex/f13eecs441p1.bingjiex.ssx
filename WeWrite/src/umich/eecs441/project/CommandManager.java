package umich.eecs441.project;

import java.util.Vector;

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
	}
	
	/**
	 * 
	 * @param client, the client that operation should be undo
	 */
	
	
	public void undo(AbstractCommand cmd) {
		
		Vector <AbstractCommand> temp = new Vector<AbstractCommand>();
		
		for (int i = commandStack.size() - 1; i >= 0; i--){
			// unwind the current command
			commandStack.elementAt(i).unwind();
			// when it is the client operation and the opertion is not undo
			if (commandStack.elementAt(i).getClient() == cmd.getClient() && !(cmd instanceof UndoCommand)) {
				// the one that should be undone
				// put into the redoList
				redoList.add(commandStack.elementAt(i));
				break;
			}
			// else put into temp array
			temp.add(cmd);
		}
		for (int i = temp.size() - 1; i >= 0; i--) {
			temp.elementAt(i).rewind();
			
		}
	}

	public void redo() {
		
	}
	

}
