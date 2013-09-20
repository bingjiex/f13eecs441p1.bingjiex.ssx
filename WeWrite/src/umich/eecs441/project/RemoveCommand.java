package umich.eecs441.project;

import android.util.Log;
import android.widget.EditText;

/**
 * Remove command
 * @author Bingjie Xu, Shaoxiang Su
 *
 */
public class RemoveCommand implements AbstractCommand{
	/*
	 * No singleton anymore, since there might be possibilities that the record comes from server
	 * in the server create an instance for CursorTrack and CommandManager
	 * set them as arguments of every Command  
	 */
	// TODO protocol buffer needs clientID and removedChar
	
	/*
	 * every command should have a private attribute that contains the client information
	 * and a boolean that indicate if it is confirmed
	 */
	// current use an int since there is no interaction, default is 0
	private int client;
	
	/**
	 * the char that is delete
	 */
	private String removedChar;
	
	// submissionID just for distinguish between confirmed event and unconfirmed event
	private int submissionID;
	
	
	/**
	 * the current text on the edit text
	 */
	private CursorWatcher text;
	
	/**
	 * the cursor instance
	 */
	private CursorTrack currentCursor; 
	
	
	/**
	 * get the client of the operation
	 * @return client, int
	 */
	public int getClient(){
		return client;
	}
	
	public int getSubmissionID() {
		return submissionID;
	}
	/**
	 * constructor
	 * @param myChar
	 * @param currentText
	 */
	// it is for an instant operation
	public RemoveCommand(String myChar, CursorWatcher currentText){
		
		// current is int type, expected to use client type
		client = Client.getInstance().getClient();
		
		currentCursor = CursorTrack.getInstance();
		removedChar = myChar;
		text = currentText;
				
		Log.i("RemoveCommand", "Constructor");
	}
	
	// TODO: if there is 
	
	
	public void execute(){
		Log.i("RemoveCommand, current cursor before execute", String.valueOf(currentCursor.getCursor(client)));
	
		currentCursor.moveLeft(client,removedChar.length());
		
		/*
		 * Store to the command manager log, when Command is constructed, use getInstance store!!!
		 */		
		Log.i("RemoveCommand", "Character removed " + removedChar);
		Log.i("RemoveCommand, current cursor after execute", String.valueOf(currentCursor.getCursor(client)));
	}
	
	// undo is just a signal from the server and do undo operation
	// request send from UndoCommand
	
	public void unwind(){
		
		text.removeTextChangedListener(text.getTextWatcher());
		
		Log.i("RemoveCommand", "Unwind");
		/*
		 * When unwind to this operation, the cursor must behind the char
		 */
		// it should be able to change the text on the edit text
		String temp = text.getText().toString();
		int cursorPosition = currentCursor.getCursor(client);
		temp = temp.substring(0, cursorPosition) + removedChar + temp.substring(cursorPosition);
		text.setText(temp);
		currentCursor.moveRight(client, removedChar.length());
		text.addTextChangedListener(text.getTextWatcher());
	}
	
	public void rewind(){
		
		text.removeTextChangedListener(text.getTextWatcher());
		Log.i("RemoveCommand", "Rewind");
		String temp = text.getText().toString();
		
		int cursorPosition = currentCursor.getCursor(client);
		
		String subStrBeforeCursor = temp.substring(0, cursorPosition);
		
		int actualRemoveLength = actualRemoveLength(removedChar, subStrBeforeCursor);
		
		temp = temp.substring(0, cursorPosition - actualRemoveLength) + temp.substring(cursorPosition);
		text.setText(temp);
		
		currentCursor.moveLeft(client, actualRemoveLength);
		text.addTextChangedListener(text.getTextWatcher());
	}
	
	
	/* 
	 * str1: the removed string
	 * str2: the text bafore the cursor in edittext
	 */
	private int actualRemoveLength(String str1, String str2) {
		if (str1.isEmpty() || str2.isEmpty()) {
			return 0;
		}
		
		int leng1 = str1.length();
		
		int result = 0;
		
		for (int i=leng1-1; i>=0; i-- ) {
			if (str1.charAt(i) == str2.charAt(i)) {
				result++;
			}
		}
		return result;
	}
}