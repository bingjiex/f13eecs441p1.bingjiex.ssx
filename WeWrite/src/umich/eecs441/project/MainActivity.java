package umich.eecs441.project;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button undoButton;
	private CursorWatcher cw;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// TODO: addClient(*id*)
		CursorTrack.getInstance().addClient(0);
		setContentView(R.layout.text_editor_screen);
		
		cw = (CursorWatcher) this.findViewById(R.id.txtMessage);
		
		undoButton = (Button) this.findViewById(R.id.button2);
		
		undoButton.setOnClickListener(new Button.OnClickListener () {
			public void onClick (View arg0) {
				AbstractCommand cmd = new UndoCommand ();
				cmd.execute();
				
				CommandManager.getInstance().undo(cmd);
				if (cmd.getClient() == Client.getInstance().getClient()) {
					
					Log.i("undo button clicked", "aaa");
					
				
					cw.setSelection(CursorTrack.getInstance().getCursor(Client.getInstance().getClient()));
				}
			}

		});
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	
}
