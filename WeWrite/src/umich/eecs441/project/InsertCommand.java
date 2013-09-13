package umich.eecs441.project;

import android.util.Log;
import android.widget.EditText;

/**
 * Insert command
 * @author Bingjie Xu, Shaoxiang Su
 *
 */
public class InsertCommand implements AbstractCommand{
	/*
	 * No singleton anymore, since there might be possibilities that the record comes from server
	 * in the server create an instance for CursorTrack and CommandManager
	 * set them as arguments of every Command  
	 */
	
	
	/*
	 * every command should have a private attribute that contains the client information
	 * and a boolean that indicate if it is confirmed
	 */
	// current use an int since there is no interaction, default is 0
	private int client;
	
	/**
	 * the char that is inserted
	 */
	private String newChar;
	
	/**
	 * the current text on the edit text
	 */
	private EditText text;
	
	/**
	 * the cursor instance
	 */
	private CursorTrack currentCursor; 
	
	private CommandManager currentManager;
	
	/**
	 * get the client of the operation
	 * @return client, int
	 */
	public int getClient(){
		return client;
	}
	
	
	/**
	 * constructor
	 * @param myChar
	 * @param currentText
	 */
	public InsertCommand(String myChar, EditText currentText, int myClient,
								CursorTrack myCursorTrack, CommandManager cmdManager){
		
		// current is int type, expected to use client type
		client = myClient;
		
		currentCursor = myCursorTrack;
		newChar = myChar;
		text = currentText;
		
		currentManager = cmdManager;
		
		Log.i("InsertCommand", "Constructor");
	}
	
	public void execute(){
		/*
		 * send execute request
		 * increment the all the cursor after the current cursor position by 1 for rewind
		 * since after this operation there might be delete from other place 
		 */
		currentCursor.moveRight(client, newChar.length());
		
		/*
		 * Store to the command manager log
		 */
		currentManager.storeCommand(this);
		
		Log.i("InsertCommand", "Character inserted " + newChar);
	}
	
	// undo is just a signal from the server and do undo operation
	// request send from UndoCommand
	
	public void unwind(){
		Log.i("InsertCommand", "Unwind");
		/*
		 * When unwind to this operation, the cursor must behind the char
		 */
		// it should be able to change the text on the edit text
		String temp = text.getText().toString();
		int cursorPosition = currentCursor.getCursor(client);
		temp = temp.substring(0,cursorPosition - newChar.length()) + temp.substring(cursorPosition);
		text.setText(temp);
		currentCursor.moveLeft(client, newChar.length());
	}
	
	public void rewind(){
		Log.i("InsertCommand", "Rewind");
		String temp = text.getText().toString();
		int cursorPosition = currentCursor.getCursor(client);
		temp = temp.substring(0, cursorPosition) + newChar + temp.substring(cursorPosition);
		text.setText(temp);
		currentCursor.moveRight(client, newChar.length());
	}
}
