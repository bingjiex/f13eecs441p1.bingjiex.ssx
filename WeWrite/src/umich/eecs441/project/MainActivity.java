package umich.eecs441.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;


public class MainActivity extends Activity{

	private Button createSession;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
	
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
<<<<<<< HEAD
=======
	/**
	 * if time up, then according to the lastcommand, flush the buffer and send the request
	 */
	private synchronized void timeUp() {
		if (buffer != "") {
			if (lastCommand.equals("Insert")) {
				AbstractCommand cmd = new InsertCommand(buffer, editText);
				CommandManager.getInstance().storeCommand(cmd);
				cmd.execute();				
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());

			} else {
				// reverse the buffer string
				String temp = "";
				for (int i = buffer.length() - 1; i >= 0; i--) {
					temp += buffer.charAt(i);
				}
				AbstractCommand cmd = new RemoveCommand(temp, editText);
				CommandManager.getInstance().storeCommand(cmd);
				cmd.execute();				
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
		Log.i("Last Command in change Command", lastCommand);
		Log.i("Current Command in change Command", command);
		
		if (buffer != "" && !lastCommand.equals(command)) {
			if (lastCommand.equals("Insert")) {
				AbstractCommand cmd = new InsertCommand(buffer, editText);
				CommandManager.getInstance().storeCommand(cmd);
				cmd.execute();				
				Client.getInstance().setCommandStackContains(
						Client.getInstance().getCommandStackContains() + 1);
				Client.getInstance().setRedoListContains(0);
				CommandManager.getInstance().newCommandHandling(Client.getInstance().getClient());
			} else {
				// reverse the buffer string
				String temp = "";
				for (int i = buffer.length() - 1; i >= 0; i--) {
					temp += buffer.charAt(i);
				}
				AbstractCommand cmd = new RemoveCommand(temp, editText);
				CommandManager.getInstance().storeCommand(cmd);
				cmd.execute();				
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
>>>>>>> bb9578bd30210dc071f0691c11b2d3cb889a1797
	

}
