package umich.eecs441.project;

import java.util.ArrayList;
import java.util.List;

import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import edu.umich.imlc.collabrify.client.exceptions.ConnectException;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
public class TextEditorActivity extends Activity
							    implements EventAccessible {

	private Button undoButton;
	private Button redoButton;
	private Button leaveButton;
	private Button terminateButton;
	private CursorWatcher editText;
	
	// for the timer
	// string buffer, recording the char
	private static String buffer;
	// keep track of the last command
	private static String lastCommand;
	// the time
	private static long startTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		Log.i("TextEditorActivity onCreate", "get sessionName  " + MainActivity.getSessionName());
		Log.i("TextEditorActivity onCreate", "get Maximum people  " + String.valueOf(MainActivity.getUserUpperLimit()));
		Log.i("TextEditorActivity onCreate", "get base file" + MainActivity.getBaseFileStr());
		
		if (MainActivity.getBaseFileStr().equals("") || MainActivity.getBaseFileStr() == null) {
			// TODO: getUserUpperLimit
			List<String> temp = new ArrayList<String>();
			temp.add("sample");
			try {
				Log.i("createSession", "no base file");
				OnlineClient.getInstance().getClient().createSession(MainActivity.getSessionName(), temp, null, MainActivity.getUserUpperLimit());
			} catch (ConnectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (CollabrifyException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			
		}
		
		
		// initialize
		buffer = "";
		lastCommand = "";
		
		
		
		
		setContentView(R.layout.text_editor_screen);
		editText = (CursorWatcher) this.findViewById(R.id.txtMessage);
		undoButton = (Button) this.findViewById(R.id.button2);
		redoButton = (Button) this.findViewById(R.id.button1);
		leaveButton = (Button)this.findViewById(R.id.button3);
		terminateButton = (Button)this.findViewById(R.id.button4);
		
		// when new client come addClient
		CursorTrack.getInstance().addClient(0);
		RedoTrack.getInstance().addClient(0);
				
		// timer thread
		new Thread () {
			public void run() {
				startTime = System.currentTimeMillis();
				while(true) {
					if (System.currentTimeMillis() - startTime >= 800) {
						startTime = System.currentTimeMillis();
						timeUp();
					}
				}
				
			}
		}.start();
		
		leaveButton.setOnClickListener(new Button.OnClickListener () {
			public void onClick (View v) {
				LinearLayout layout = new LinearLayout(TextEditorActivity.this);
				layout.setOrientation(LinearLayout.VERTICAL);
				
				final AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(TextEditorActivity.this);
				alertDialogbuilder.setTitle("Leave Session");
				alertDialogbuilder.setMessage("Are you sure to leave the session?");
				
				alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // TODO: leave the session, back to the main menu. !!how about the session's creator?
			        }
			     });
				
			    alertDialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // TODO: back to the texteditor screen
			        }
			     });
			    alertDialogbuilder.setView(layout);
			    final AlertDialog alertDialog = alertDialogbuilder.create();
			    alertDialog.show();
			}
		
		});
		
		terminateButton.setOnClickListener(new Button.OnClickListener () {
			public void onClick (View v) {
				LinearLayout layout = new LinearLayout(TextEditorActivity.this);
				layout.setOrientation(LinearLayout.VERTICAL);
				
				final AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(TextEditorActivity.this);
				alertDialogbuilder.setTitle("Leave Session");
				alertDialogbuilder.setMessage("Are you sure to leave the session?");
				
				alertDialogbuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			            // TODO: should first check whether the current user is the creator. if not should pop up
			        	// 		warning window and back to the session. otherwise, should terminate the session and
			        	//		back to the main menu.
			        }
			     });
				
			    alertDialogbuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
			        public void onClick(DialogInterface dialog, int which) { 
			        	// TODO:should first check whether the current user is the creator. if not should pop up
			        	// 		warning window and back to the session. otherwise, should back to the texteditor
			        	// 		screen.
			        }
			     });
			    alertDialogbuilder.setView(layout);
			    final AlertDialog alertDialog = alertDialogbuilder.create();
			    alertDialog.show();
			}
		
		});
		
		undoButton.setOnClickListener(new Button.OnClickListener () {
			public void onClick (View arg0) {
				// if there is action (not undo) in the stack
				if (Client.getInstance().getCommandStackContains() != 0){
					
					Client.getInstance().setCommandStackContains(
							Client.getInstance().getCommandStackContains() - 1);
					Client.getInstance().setRedoListContains(
							Client.getInstance().getRedoListContains() + 1);
					// TODO: Should check if the command is confirmed or not, change undo function in CommandManager
					changeCommand("Undo", "");
					AbstractCommand cmd = new UndoCommand ();
					cmd.execute();
					
					
					// It should run when the client receive the undo request
					// move into reveive command
					CommandManager.getInstance().undo(cmd);
					if (cmd.getClient() == Client.getInstance().getClient()) {
						Log.i("undo button clicked", "  ");
						editText.setSelection(CursorTrack.getInstance().getCursor(Client.getInstance().getClient()));
					}
				}
			}

		});
		
		redoButton.setOnClickListener(new Button.OnClickListener () {

			@Override
			public void onClick(View arg0) {
				// if there is action in the list
				if (Client.getInstance().getRedoListContains() != 0) {
					Client.getInstance().setCommandStackContains(
							Client.getInstance().getCommandStackContains() + 1);
					Client.getInstance().setRedoListContains(
							Client.getInstance().getRedoListContains() - 1);
					
					changeCommand("redo", "");
					AbstractCommand cmd = new RedoCommand ();
					cmd.execute();
					
					// It should run when the client receive the redo request
					// move it into receive client
					CommandManager.getInstance().redo(cmd);
					if (cmd.getClient() == Client.getInstance().getClient()) {
						editText.setSelection(CursorTrack.getInstance().getCursor(Client.getInstance().getClient()));
					}
				}
			}
			
		});
		
		
		
		
		
		
		editText.setOnClickListener(new EditText.OnClickListener () {

			@Override
			public void onClick(View arg0) {
				Log.i("EditText", "Trigger");
				
				// might need to send request for the last command
				changeCommand("Move", "");
				startTime = System.currentTimeMillis();
				
				int movement = editText.getSelectionEnd() 
						- CursorTrack.getInstance().getCursor(Client.getInstance().getClient());
				// unconfirmed operation
				if (movement != 0) {
					// TODO constructor with client argument might be expected
					// add commandStackContains by 1 to indicate the client has one action
					Client.getInstance().setCommandStackContains(Client.getInstance().getCommandStackContains() + 1);
					AbstractCommand cmd = new CursorCommand(movement);
					
					
					//!!!! execute should be ahead of the store since it will find storecommand
					cmd.execute();
					CommandManager.getInstance().storeCommand(cmd);
					// when there is a new command executed, we have to tell that the client
					// can undo no matter if it is confirmed, so 
					Client.getInstance().setCommandStackContains(
							Client.getInstance().getCommandStackContains() + 1);
					Client.getInstance().setRedoListContains(0);
					//!!!! two number in Client is just for track the very instant operation of the client
					// doesnt influence the real stack, where the change occurs when the command come back
					
					// Since it is not collabrify, just do as if we get the response
					// clear
					
					// just pass the client ID
					CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());
					// TODO when receive a command, put the command into the stack, also check if it is confirmed or not
					
				}
			}
			
		});
		
		TextWatcher textWatcher = new TextWatcher() {
			
			
			public void afterTextChanged(Editable s) {
				
			}
			
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				if (count > after) {
					String change = s.toString().substring(start, start + count - after);
					changeCommand("Delete", change);
					startTime = System.currentTimeMillis();	
				}
				
				
				
			}
			
			public void onTextChanged(CharSequence s, int start, int before, int count) {
					
				if (count > before) {
					
					/*Client.getInstance().setCommandStackContains(
							Client.getInstance().getCommandStackContains() + 1);
					Log.i("onTextChanged triggered, CharSequence", s.toString());
					Log.i("onTextChanged triggered, Current Edit Text", editText.getText().toString());
					Log.i("onTextChanged triggered, start", String.valueOf(start));
					Log.i("onTextChanged triggered, count", String.valueOf(count));
					Log.i("onTextChanged triggered, before", String.valueOf(before));
					String change = s.toString().substring(start, start + count - before);
					Log.i("onTextChanged triggered, string change", change);
					AbstractCommand cmd = new InsertCommand(change, editText);
					CommandManager.getInstance().storeCommand(cmd);
					cmd.execute();
					
					// when there is a new command executed, we have to tell that the client
					// can undo no matter if it is confirmed, so 
					Client.getInstance().setCommandStackContains(
							Client.getInstance().getCommandStackContains() + 1);
					Client.getInstance().setRedoListContains(0);
					//!!!! two number in Client is just for track the very instant operation of the client
					// doesnt influence the real stack, where the change occurs when the command come back
					
					// Since it is not collabrify, just do as if we get the response
					// clear
					CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());
			*/		
					String change = s.toString().substring(start, start + count - before);
					changeCommand("Insert", change);
					startTime = System.currentTimeMillis();
				}
			}
		};
		
		editText.setTextWatcher(textWatcher);
		editText.addTextChangedListener(editText.getTextWatcher());
		
	}
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	/**
	 * if time up, then according to the lastcommand, flush the buffer and send the request
	 */
	private synchronized void timeUp() {
		if (buffer != "") {
			if (lastCommand.equals("Insert")) {
				AbstractCommand cmd = new InsertCommand(buffer, editText);
				// notice that the execute should be ahead of the storecommand to set the command id.
				cmd.execute();
				CommandManager.getInstance().storeCommand(cmd);
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());

			} else {
				// reverse the buffer string
				String temp = "";
				for (int i = temp.length() - 1; i >= 0; i++) {
					temp += buffer.charAt(i);
				}
				AbstractCommand cmd = new RemoveCommand(temp, editText);
				cmd.execute();
				CommandManager.getInstance().storeCommand(cmd);
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());

			}
		}
		buffer = "";
	}
	// TODO: command change from insert to move cursor
	// I am considering to pass a "" as newChar
	private synchronized void changeCommand(String command, String newChar) {
		if (buffer != "" && !lastCommand.equals(command)) {
			Log.i("changeCommand command changed", command);
			if (lastCommand.equals("Insert")) {
				AbstractCommand cmd = new InsertCommand(buffer, editText);
				cmd.execute();	
				CommandManager.getInstance().storeCommand(cmd);			
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());
			} else {
				// reverse the buffer string
				String temp = "";
				for (int i = temp.length() - 1; i >= 0; i++) {
					temp += buffer.charAt(i);
				}
				AbstractCommand cmd = new RemoveCommand(temp, editText);
				cmd.execute();
				CommandManager.getInstance().storeCommand(cmd);				
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());

			}
			buffer = "";
		}
		lastCommand = command;
		buffer = buffer + newChar;
		Log.i("changeCommand append", buffer);
	}
	
}






