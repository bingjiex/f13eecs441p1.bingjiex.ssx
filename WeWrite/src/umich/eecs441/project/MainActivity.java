package umich.eecs441.project;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import edu.umich.imlc.collabrify.client.CollabrifySession;


public class MainActivity extends Activity
						  implements SessionListAccessible {

	private Button createSession;
	private Button joinSession;
	
	static private String baseFileStr;
	static private String sessionName;
	static private int userUpperLimit;
	static private long sessionId;
	
	static public boolean createNewSession = false;
	
	private boolean inputValid;
	
	// obtain the session list
	private List <CollabrifySession> sessionList;
	
	private ArrayList<String> temp = new ArrayList<String>();
	
	// waitingDialog
	private ProgressDialog waitingDialog;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		createSession = (Button)this.findViewById(R.id.button1);
		joinSession = (Button)this.findViewById(R.id.button2);
		
		
		// init singleton
		CursorTrack.clear();
		RedoTrack.clear();
		
		
		OnlineClient.getInstance(this.getApplicationContext());
		
		OnlineClient.getInstance().setMainActivity(MainActivity.this);
		
			joinSession.setOnClickListener(new Button.OnClickListener () {
				@Override
				public void onClick(View arg0) {
					Log.i("joinsession ON click", "show progressDialog");
					waitingDialog = ProgressDialog.show(MainActivity.this, "Waiting...", "Obtaining Sessions", true);
					Log.i("joinSession setOnClickListener", String.valueOf(waitingDialog == null));
	
					
					// TODO Auto-generated method stub
					try {
						Log.i("@@", "@@");
						temp.add("pass");
						OnlineClient.getInstance().getClient().requestSessionList(temp);
					} catch (Exception e) {
						// TODO Auto-generated catch block
					}
			}
			
		});
		
		createSession.setOnClickListener (new Button.OnClickListener() {
			 
			public void onClick(View v) {
				inputValid = true;
				
				LinearLayout layout = new LinearLayout(MainActivity.this);
				layout.setOrientation(LinearLayout.VERTICAL);
				
				final AlertDialog.Builder alertDialogbuilder = new AlertDialog.Builder(MainActivity.this);

				alertDialogbuilder.setTitle("Create Session");
				alertDialogbuilder.setMessage("Enter your session info");

				final EditText sessionNameEdit = new EditText(MainActivity.this);
				sessionNameEdit.setHint("Session Name");
				layout.addView(sessionNameEdit);
				
				final EditText baseFileStrEdit = new EditText(MainActivity.this);
				baseFileStrEdit.setHint("Base file");
				baseFileStrEdit.setHeight(500);
				baseFileStrEdit.setGravity(Gravity.TOP);
			
				layout.addView(baseFileStrEdit);
				
				
				alertDialogbuilder.setPositiveButton(android.R.string.ok, null);
				alertDialogbuilder.setNegativeButton(android.R.string.cancel, null);
				alertDialogbuilder.setView(layout);
				
				final AlertDialog alertDialog = alertDialogbuilder.create();
				alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {

				    @Override
				    public void onShow(DialogInterface dialog) {

				        Button ok = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
				        ok.setOnClickListener(new View.OnClickListener() {

				            @Override
				            public void onClick(View view) {
				            	if (sessionNameEdit.getText().toString().isEmpty()) {
									  Toast toast = Toast.makeText(MainActivity.this, "Session name connot be empty.", Toast.LENGTH_LONG);
									  toast.show();
									  inputValid = false;
								  } else {
									  sessionName = sessionNameEdit.getText().toString();
									  baseFileStr = baseFileStrEdit.getEditableText().toString();
									  }
				            	if (inputValid) {
				            		// TODO: pass createNewSession to the textEditorActivity
				            		createNewSession = true;
				            		alertDialog.dismiss();
				            		Intent intent = new Intent();
				    				intent.setClass(MainActivity.this, TextEditorActivity.class);
				    				startActivity(intent);
				    				MainActivity.this.finish();
				            	}
				            	
				            }   
				        });
				        
				        Button cancel = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
				        cancel.setOnClickListener(new View.OnClickListener() {
				        	 @Override
					            public void onClick(View view) {
				        		 createNewSession = false;
				        		 alertDialog.dismiss();
				        }	
				        });

				    }
				});
				
				alertDialog.show();
				
				
			}
		});

	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void setSessionList(List<CollabrifySession> list) {
		// TODO Auto-generated method stub
		sessionList = new ArrayList<CollabrifySession>(list);
		Log.i("find list thread", String.valueOf(list.size()));
		Log.i("setSessionList waitingDialog", String.valueOf(waitingDialog == null));
		
		waitingDialog.dismiss();
		
		final AlertDialog.Builder builder = new AlertDialog.Builder(
				MainActivity.this);

		ArrayList <String> sessionNames = new ArrayList<String>();
		for (CollabrifySession e : sessionList) {
			sessionNames.add(e.name());
		}
		for (String s : sessionNames) {
			Log.i("sessionName", s);
		}
		builder.setTitle("Choose Session").setItems(
				sessionNames.toArray(new String[sessionList.size()]), 
		new DialogInterface.OnClickListener() {
			@Override
			public void onClick(
				DialogInterface dialog, int which) {
				createNewSession = false;
				sessionId = sessionList.get(which).id();
				// switch to the activity
				Intent intent = new Intent();
				intent.setClass(MainActivity.this, TextEditorActivity.class);
				startActivity(intent);
				MainActivity.this.finish();
			}
		});
		MainActivity.this.runOnUiThread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				
				builder.show();
			}
		});
	}
	public void noListFound() {
		Log.i("find list thread", "no list");
		waitingDialog.dismiss();
		final AlertDialog.Builder WrongUserInformationDialog = new AlertDialog.Builder(MainActivity.this);
		WrongUserInformationDialog.setTitle("No Availabel Session");
		WrongUserInformationDialog.setMessage("Please create one");
		WrongUserInformationDialog.setPositiveButton("OK", null);
		
		// why use a unOnUiThread
		MainActivity.this.runOnUiThread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				WrongUserInformationDialog.show();
			}
			
		});
	}
		
	public static long getSessionId() {
		return sessionId;
	}
	
	public static String getSessionName() {
		return sessionName;
	}


	public static int getUserUpperLimit() {
		return userUpperLimit;
	}

	public static String getBaseFileStr() {
		return baseFileStr;
	}
	
	

}
