package umich.eecs441.project;

import java.util.HashMap;
import java.util.Map;

import umich.eecs441.project.proto.RemoveCommandBuf.RemoveCommandBufObj;
import android.util.Log;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;

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
	private long client;
	
	private HashMap<Long, Integer> trackMap;
	
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
	 * get the client of the operation
	 * @return client, int
	 */
	public long getClient(){
		return client;
	}
	
	public int getSubmissionID() {
		return submissionID;
	}
	
	public void setSubmissionID (int subId) {
		submissionID = subId;
	}
	/**
	 * constructor
	 * @param myChar
	 * @param currentText
	 */
	
	public RemoveCommand (String myChar, int c) {
		client = c;
		removedChar = myChar;
		text = TextEditorActivity.getCursorWatcher();
		trackMap = new HashMap<Long, Integer>();
	}
	
	
	
	// it is for an instant operation
	public RemoveCommand(String myChar, HashMap<Long, Integer> recoverMap){
		
		// current is int type, expected to use client type
		client = (int)OnlineClient.getInstance().getClientID();
		trackMap = recoverMap;
		removedChar = myChar;
		text = TextEditorActivity.getCursorWatcher();
		
				
		Log.i("RemoveCommand", "Constructor");
	}
	
	// TODO: if there is 
	
	
	public void execute(){
		RemoveCommandBufObj.Builder builder = RemoveCommandBufObj.newBuilder();
		builder.setClientID((int)client);
		builder.setRemovedChar(removedChar);
		RemoveCommandBufObj object = builder.build();
		
		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				submissionID = OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "RemoveCommand");
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
		
		/*for (Map.Entry<Integer, Integer> entry : CursorTrack.getInstance().getCursorMap().entrySet()) {
			if (isBetween(entry)) {
				Integer senderCursorPosAfter = CursorTrack.getInstance().getCursor(client) - removedChar.length();
				trackMap.put(entry.getKey(), entry.getValue()-senderCursorPosAfter);
			}
		}
		*/
		Log.i("RemoveCommand, current cursor before execute", String.valueOf(CursorTrack.getInstance().getCursor(client)));
	
//		CursorTrack.getInstance().moveLeft(client,removedChar.length());
		
		/*
		 * Store to the command manager log, when Command is constructed, use getInstance store!!!
		 */		
		Log.i("RemoveCommand", "Character removed " + removedChar);
		Log.i("RemoveCommand, current cursor after execute", String.valueOf(CursorTrack.getInstance().getCursor(client)));
	}
	// !!!!!!!!!!!!!!!!!!!!!!!!!TODO: How to return to the previous state.
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
		int cursorPosition = CursorTrack.getInstance().getCursor(client);
		temp = temp.substring(0, cursorPosition) + removedChar + temp.substring(cursorPosition);
		text.setText(temp);
		
		for (Map.Entry<Long, Integer> entry : CursorTrack.getInstance().getCursorMap().entrySet()) {
			if (trackMap.containsKey(entry.getKey())) {
				CursorTrack.getInstance().getCursorMap().put(entry.getKey(), trackMap.get(entry.getKey()));
			}
		}
		CursorTrack.getInstance().moveRight(client,	removedChar.length());
		
		trackMap.clear();
		
		//CursorTrack.getInstance().moveRight(client, removedChar.length());
		text.addTextChangedListener(text.getTextWatcher());
	}
	
	public void rewind(){
		
		text.removeTextChangedListener(text.getTextWatcher());
		Log.i("RemoveCommand", "Rewind");
		String temp = text.getText().toString();
		
		int cursorPosition = CursorTrack.getInstance().getCursor(client);
		
		String subStrBeforeCursor = temp.substring(0, cursorPosition);
		
		int actualRemoveLength = actualRemoveLength(removedChar, subStrBeforeCursor);
		
		temp = temp.substring(0, cursorPosition - actualRemoveLength) + temp.substring(cursorPosition);
		text.setText(temp);
		
		for (Map.Entry<Long, Integer> entry : CursorTrack.getInstance().getCursorMap().entrySet()) {
			if (isBetween(entry, actualRemoveLength)) {
				Integer senderCursorPosAfter = CursorTrack.getInstance().getCursor(client) - removedChar.length();
				trackMap.put(entry.getKey(), entry.getValue()-senderCursorPosAfter);
			}
		}
		
		CursorTrack.getInstance().moveLeft(client, actualRemoveLength);
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
	
	private boolean isBetween (Map.Entry<Long, Integer> entry, int actualLength) {
		Integer senderCursorPosBefore = CursorTrack.getInstance().getCursor(client);
		Integer senderCursorPosAfter = CursorTrack.getInstance().getCursor(client) - actualLength;
		if (entry.getValue() < senderCursorPosBefore && entry.getValue() > senderCursorPosAfter) return true;
		else return false;
	}

}
