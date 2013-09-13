package umich.eecs441.project;

import java.util.Vector;

public class CommandManager {
	
	private Vector<AbstractCommand> commandStack = null; 
	private Vector<AbstractCommand> redoList = null;
	
	public CommandManager() {
		commandStack = new Vector<AbstractCommand> ();
		redoList = new Vector<AbstractCommand> ();
	}
	public CommandManager (Vector<AbstractCommand> v1, Vector<AbstractCommand> v2) {
		commandStack = v1;
		redoList = v2;
	}
	
	public void storeCommand (AbstractCommand cmd) {
		commandStack.add(cmd);
	}
	
	/**
	 * 
	 * @param client, the client that operation should be undo
	 */
	
	/*
	public void undo(int client) {
		
		Vector <AbstractCommand> temp;
		
		for (int i = commandStack.size() - 1; i >= 0; i--){
			if ()
	}
*/	
	public void redo() {
		
	}
	

}
