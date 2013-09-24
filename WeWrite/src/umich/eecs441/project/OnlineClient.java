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

/**
 * my own client, use int temperarily
 * 
 * @author picc
 * 
 */

public class OnlineClient {

	private static OnlineClient instance = null;

	private static final Level LOGGING_LEVEL = Level.ALL;

	// TODO expected to change to client type
	private CollabrifyClient client;
	// for the client
	private CollabrifyListener collabrifyListener;

	// session ID
	private long sessionId;
	// session name
	private String sessionName;

	// initial with 0, tells if the two lists contains actions
	private int commandStackContains;
	// !!! We still need to track the redo list since the instant operation will
	// not
	// put the command into redo list
	private int redoListContains;

	// for activities
	private SessionListAccessible mainActivity;
	
	private EventAccessible editorActivity;

	public void setEditorActivity(EventAccessible editorActivity) {
		this.editorActivity = editorActivity;
	}


	// TODO expected to add constructor with client type argument
	protected OnlineClient(Context context, SessionListAccessible main) {

		commandStackContains = 0;
		
		mainActivity = main;

		collabrifyListener = new CollabrifyAdapter() {
			@Override
			public void onDisconnect() {
				Log.i("client connection", "disconnected");
			}

		/*	@Override
			public void onReceiveEvent(final long orderId, int subId,
					String eventType, final byte[] data) {

				Log.d("client connection", "RECEIVED SUB ID:" + subId);

				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
					}
				});
			}*/

			@Override
			public void onReceiveSessionList(final List<CollabrifySession> sessionList) {
	        
				if( sessionList.isEmpty() ) {
					Log.i("client connection", "No session available");
					// no element
					mainActivity.setGuard(1);
					return;
				}
				mainActivity.setSessionList(sessionList);
				// obtain elements
				mainActivity.setGuard(2);
				return;
			}

			@Override
			public void onSessionCreated(long id) {
				Log.i("client connection", "Session created, id: " + id);
				sessionId = id;
				
			}

			@Override
			public void onError(CollabrifyException e) {
				Log.e("client connection", "error", e);
			}
			
/*			@Override
			public void onSessionJoined(long maxOrderId, long baseFileSize) {
				Log.i("client connection", "Session Joined");
				if (baseFileSize > 0) { // initialize buffer to receive base
										// file
					baseFileReceiveBuffer = new ByteArrayOutputStream(
							(int) baseFileSize);
				}
				activity.runOnUiThread(new Runnable() {

					@Override
					public void run() {
					}
				});
			}
			 
	      	public void onBaseFileChunkReceived(byte[] baseFileChunk) {
	      		try {
	      			if( baseFileChunk != null ) {
	      				
	      				baseFileReceiveBuffer.write(baseFileChunk);
	      			} else {
	      				activity.runOnUiThread(new Runnable() {
	      					@Override
	      					public void run() {
	      						broadcastedText.setText(baseFileReceiveBuffer.toString());
	      					}
	      				});
	      				baseFileReceiveBuffer.close();
	      			}
	      		} catch( IOException e ) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
	      	}

	      	 
	      	@Override
	      	public void onBaseFileUploadComplete(long baseFileSize)	{
	      		
		        activity.runOnUiThread(new Runnable() {

		        	@Override
		        	public void run() {
		        	}
		        });
		        try {
		        	baseFileBuffer.close();
		        } catch( IOException e ) {
		          // TODO Auto-generated catch block
		        	e.printStackTrace();
		        }
		     }*/
		};
	

		try {
			client = new CollabrifyClient(context,
					"ssx@umich.edu", "Shao", "441fall2013@umich.edu",
					"XY3721425NoScOpE", false, collabrifyListener);
		} catch (CollabrifyException e) {
			e.printStackTrace();
		}


	}

	
	public static OnlineClient getInstance(Context context, SessionListAccessible main) {
		if (instance == null)
			instance = new OnlineClient(context, main);
		return instance;
	}

	public CollabrifyClient getClient() {
		return client;
	}

	public static OnlineClient getInstance() {
		if (instance == null) {
			Log.i("onlineclient", "no client");
			return null;
		}
		return instance;
	}

	public long getClientID() {

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