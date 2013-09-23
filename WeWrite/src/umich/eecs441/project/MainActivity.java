package umich.eecs441.project;

import java.util.Random;
import java.util.Vector;

import edu.umich.imlc.collabrify.client.exceptions.CollabrifyException;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity{

	private Button createSession;
	private Button joinSession;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_main);
		
		createSession = (Button)this.findViewById(R.id.button1);
		joinSession = (Button)this.findViewById(R.id.button2);
		
		OnlineClient.getInstance(this);
		
		createSession.setOnClickListener(new Button.OnClickListener () {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					Vector<String> list = new Vector<String>();
					list.add("dummy");
					Random rand = new Random();
					String sessionName = "Test" + rand.nextInt(Integer.MAX_VALUE);
					OnlineClient.getInstance().getClient().createSession(sessionName, list, "12345", 12);
					
				} catch (CollabrifyException e) {
					Log.e("create session failed", " ");
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
