package umich.eecs441.project;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.widget.CheckBox;
import edu.umich.imlc.android.common.Utils;
import edu.umich.imlc.collabrify.client.CollabrifyAdapter;
import edu.umich.imlc.collabrify.client.CollabrifyClient;
import edu.umich.imlc.collabrify.client.CollabrifyListener;
import edu.umich.imlc.collabrify.client.CollabrifySession;
import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import edu.umich.imlc.collabrify.collabrify_dummy_app.MainActivity;

/**
 * my own client, use int temperarily
 * @author picc
 *
 */

public class OnlineClient {
	
	private static OnlineClient instance = null;
	
	private static final Level LOGGING_LEVEL = Level.ALL;
	
	
	// TODO expected to change to client type
	private CollabrifyClient client;
	// set the context from your main activity
	private Context context;	
	// pass activity reference
	private Activity activity;
	// for the client
	private CollabrifyListener collabrifyListener;
	
	
	// initial with 0, tells if the two lists contains actions
	private int commandStackContains;
	
	// !!! We still need to track the redo list since the instant operation will not
	// put the command into redo list
	private int redoListContains;
	

	// TODO expected to add constructor with client type argument
	protected OnlineClient(Context contex, CollabrifyListener collabrifyListener) {
		try {
			client = new CollabrifyClient(context, "ssx@umich.edu", "Shao", 
					"441fall2013@umich.edu", "XY3721425NoScOpE", false, collabrifyListener);
		} catch (CollabrifyException e) {
			e.printStackTrace();
		}		
		commandStackContains = 0;
	}
	
	
	public static OnlineClient getInstance(Context context, CollabrifyListener collabrifyListener) {
		if (instance == null)
			instance = new OnlineClient(context, collabrifyListener);
		return instance;
	}
	
	public CollabrifyClient getClient () {
		return client;
	}
	
	public long getClientID () {
		
		return client.currentSessionParticipantId();
	}

	public int getCommandStackContains() {
		return commandStackContains;
	}

	public void setCommandStackContains(int commandStackContains) {
		this.commandStackContains = commandStackContains;
	}
	
	public int getRedoListContains() {
		return redoListContains;
	}

	public void setRedoListContains(int redoListContains) {
		this.redoListContains = redoListContains;
	}

}