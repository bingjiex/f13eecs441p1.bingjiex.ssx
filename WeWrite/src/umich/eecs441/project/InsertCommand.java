package umich.eecs441.project;

import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import umich.eecs441.project.proto.InsertCommandBuf.InsertCommandBufObj;
import android.util.Log;

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
	
	// TODO protocol buffer needs client newChar
	/*
	 * every command should have a private attribute that contains the client information
	 * and a boolean that indicate if it is confirmed
	 */
	// current use an int since there is no interaction, default is 0
	private int submissionID;
	
	private long client;
	
	/**
	 * the char that is inserted
	 */
	private String newChar;
	
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
	
	public void setSubmissionID(int subId) {
		submissionID = subId;
	}
	/**
	 * constructor
	 * @param myChar
	 * @param currentText
	 */
	public InsertCommand (String myChar, int c) {
		newChar = myChar;
		client = c;
		text = TextEditorActivity.getCursorWatcher();
	}
	
	
	
	// for local user construct command
	public InsertCommand(String myChar){
		
		// current is int type, expected to use client type
		client = (int)OnlineClient.getInstance().getClientID();
		
		newChar = myChar;
		text = TextEditorActivity.getCursorWatcher();
				
		Log.i("InsertCommand", "Constructor");
	}
	
	public void execute(){
		InsertCommandBufObj.Builder builder = InsertCommandBufObj.newBuilder();
		builder.setClientID((int)client);
		builder.setNewChar(newChar);
		
		InsertCommandBufObj object = builder.build();

		if (OnlineClient.getInstance().getClient().inSession() && 
				OnlineClient.getInstance().getClient() != null) {
			try {
				Log.i("@@@@ InsertCommand execute", "insert char: " + newChar);
				submissionID = OnlineClient.getInstance().getClient().broadcast(object.toByteArray(), "InsertCommand");
			} catch (CollabrifyException e) {
				e.printStackTrace();
			}
		}
		
		Log.i("InsertCommand, current cursor before execute", String.valueOf(CursorTrack.getInstance().getCursor(client)));
		/*
		 * send execute request
		 * increment all the cursor after the current cursor position by 1 for rewind
		 * since after this operation there might be delete from other place 
		 */
//		CursorTrack.getInstance().moveRight(client, newChar.length());
		
		/*
		 * Store to the command manager log, when Command is constructed, use getInstance store!!!
		 */		
		Log.i("InsertCommand", "Character inserted " + newChar);
		Log.i("InsertCommand, current cursor after execute", String.valueOf(CursorTrack.getInstance().getCursor(client)));
	}
	
	// undo is just a signal from the server and do undo operation
	// request send from UndoCommand
	
	public void unwind(){
		
		text.removeTextChangedListener(text.getTextWatcher());
		
		Log.i("InsertCommand", "Unwind");
		/*
		 * When unwind to this operation, the cursor must behind the char
		 */
		// it should be able to change the text on the edit text
		String temp = text.getText().toString();
		int cursorPosition = CursorTrack.getInstance().getCursor(client);
		Log.i("Insert unwind @ temp", "Client: " + String.valueOf(client) + "\n" + "Newchar: " + newChar + "\n Cursor: " + String.valueOf(cursorPosition));
		temp = temp.substring(0,cursorPosition - newChar.length()) + temp.substring(cursorPosition);
		text.setText(temp);
		
		Log.i("@ InsertCommand unwind is calling CursorTrack's moveLeft", "Client: "+ client + "NewCharLenghth: " + String.valueOf(newChar.length()));
		
		CursorTrack.getInstance().moveLeft(client, newChar.length());
		text.addTextChangedListener(text.getTextWatcher());
	}
	
	public void rewind(){
		
		text.removeTextChangedListener(text.getTextWatcher());
		Log.i("InsertCommand Rewind", "CursorPosition: " + String.valueOf(CursorTrack.getInstance().getCursor(client)));
		String temp = text.getText().toString();
		int cursorPosition = CursorTrack.getInstance().getCursor(client);
		temp = temp.substring(0, cursorPosition) + newChar + temp.substring(cursorPosition);
		text.setText(temp);
		
		Log.i("@ InsertCommand rewind is calling CursorTrack's moveRight", "Client: "+ client + "NewCharLenghth: " + String.valueOf(newChar.length()));
		
		CursorTrack.getInstance().moveRight(client, newChar.length());
		text.addTextChangedListener(text.getTextWatcher());
	}
	
	
}
