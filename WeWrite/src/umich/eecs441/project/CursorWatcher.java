package umich.eecs441.project;

import android.content.Context;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

// change to singleton since for every client there is just one instance
public class CursorWatcher extends EditText {
	
	private TextWatcher textWatcher;
		
	public TextWatcher getTextWatcher() {
		return textWatcher;
	}

	public void setTextWatcher(TextWatcher textWatcher) {
		this.textWatcher = textWatcher;
	}

	protected CursorWatcher(Context context, AttributeSet attrs,
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
		if (selStart != selEnd) {
			int clientID = Client.getInstance().getClient();
			int cursorPos = CursorTrack.getInstance().getCursor(clientID);
			
			this.setSelection(cursorPos);
		}
     } 
}
