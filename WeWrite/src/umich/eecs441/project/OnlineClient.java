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
	
	// session ID
	private long sessionId;
	// session name
	private String sessionName;
	
	
	
	
	
	
	// initial with 0, tells if the two lists contains actions
	private int commandStackContains;
	// !!! We still need to track the redo list since the instant operation will not
	// put the command into redo list
	private int redoListContains;
	

	// TODO expected to add constructor with client type argument
	protected OnlineClient(Context contex) {
		
		collabrifyListener = new CollabrifyAdapter () {
			@Override
		    public void onDisconnect() {
		        Log.i("client connection", "disconnected");
		        activity.runOnUiThread(new Runnable(){

		        	@Override
		        	public void run() {
		        	}
		        });
			}

/*			@Override
			public void onReceiveEvent(final long orderId, int subId,
					String eventType, final byte[] data) {
				
				Log.d("client connection", "RECEIVED SUB ID:" + subId);
				
				activity.runOnUiThread(new Runnable() {
					@Override
		          	public void run() {
					}
				});
			}
*/
/*			@Override
			public void onReceiveSessionList(final List<CollabrifySession> sessionList) {
	        
				if( sessionList.isEmpty() ) {
					Log.i("client connection", "No session available");
					return;
				}
				List<String> sessionNames = new ArrayList<String>();
				for( CollabrifySession s : sessionList ) {
					sessionNames.add(s.name());
				}
				final AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
				builder.setTitle("Choose Session").setItems(
						sessionNames.toArray(new String[sessionList.size()]),
						new DialogInterface.OnClickListener() {
							@Override
							public void onClick(DialogInterface dialog, int which) {
								try {	
										sessionId = sessionList.get(which).id();
										sessionName = sessionList.get(which).name();
										myClient.joinSession(sessionId, null);
								} catch( CollabrifyException e ) {
										Log.e(TAG, "error", e);
								}
							}
						});

				activity.runOnUiThread(new Runnable() {
					@Override
					public void run() {
					}
				});
			}
*/
	      
	      	@Override
	      	public void onSessionCreated(long id) {
	      		Log.i("client connection", "Session created, id: " + id);
	      		sessionId = id;
	      		activity.runOnUiThread(new Runnable() {

	      			@Override
	      			public void run() {

	      			
	      			}
	      		});
	      	}

	      	@Override
	      	public void onError(CollabrifyException e) {
	      		Log.e("client connection", "error", e);
	      	}
/*
	      	@Override
	      	public void onSessionJoined(long maxOrderId, long baseFileSize) {
	      		Log.i("client connection", "Session Joined");
	      		if( baseFileSize > 0 ) {
	      			//initialize buffer to receive base file
	      			baseFileReceiveBuffer = new ByteArrayOutputStream((int) baseFileSize);
	      		}
	      		activity.runOnUiThread(new Runnable() {
	      			
	      			@Override
	      			public void run() {
	      			}
	      		});
	      	}
	      	 
*/	      	
/*	      	@Override
	      	public byte[] onBaseFileChunkRequested(long currentBaseFileSize) {
	      		// read up to max chunk size at a time
	      		byte[] temp = new byte[CollabrifyClient.MAX_BASE_FILE_CHUNK_SIZE];
	      		int read = 0;
	      		try {
	      			read = baseFileBuffer.read(temp);
	      		} catch( IOException e ) {
	      			// TODO Auto-generated catch block
	      			e.printStackTrace();
	      		}
	      		if( read == -1 ) {
	      			return null;
	      		}
	      		if( read < CollabrifyClient.MAX_BASE_FILE_CHUNK_SIZE ) {
	      			// Trim garbage data
	      			ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      			bos.write(temp, 0, read);
	      			temp = bos.toByteArray();
	      		}
	      		return temp;
	      	}
		       
	      	@Override
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
		     }
*/		};
		
		try {
			client = new CollabrifyClient(context, "ssx@umich.edu", "Shao", 
					"441fall2013@umich.edu", "XY3721425NoScOpE", false, collabrifyListener);
		} catch (CollabrifyException e) {
			e.printStackTrace();
		}		
		commandStackContains = 0;
	}
	
	
	public static OnlineClient getInstance(Context context) {
		if (instance == null)
			instance = new OnlineClient(context);
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