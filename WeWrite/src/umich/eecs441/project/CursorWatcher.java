package umich.eecs441.project;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

public class CursorWatcher extends EditText {
	public CursorWatcher(Context context, AttributeSet attrs,
            int defStyle) {
        super(context, attrs, defStyle);

    }

    public CursorWatcher(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    public CursorWatcher(Context context) {
        super(context);

    }

	
	 @Override   
     protected void onSelectionChanged(int selStart, int selEnd) { 
		if (selStart == selEnd) {
			int clientID = Client.getInstance().getClient();
			int posCursorPos = CursorTrack.getInstance().getCursor(clientID);
			int mov = selEnd - posCursorPos;
			if (mov != 0) {
				AbstractCommand cmd = new CursorCommand(mov);
				Log.i("Triger onSelectionChanged", "Command Class Name " + cmd.getClass().toString());
				CommandManager.getInstance().storeCommand(cmd);
				cmd.execute();
			}
		}
        Toast.makeText(getContext(), "selStart is " + selStart + "selEnd is " + selEnd, Toast.LENGTH_LONG).show();
     } 
}
