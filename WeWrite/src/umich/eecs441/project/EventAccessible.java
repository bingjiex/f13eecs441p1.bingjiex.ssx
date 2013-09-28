package umich.eecs441.project;

public interface EventAccessible {
	
	// if the uploadCompleted, dismiss the waitingDialog
	public void dismissWaitingDialog ();
	
	// load file into app
	public void setEditorText (final String baseFile);
	
	public void eventReceived (AbstractCommand cmd);
}
