package umich.eecs441.project;

import java.util.List;

import edu.umich.imlc.collabrify.client.CollabrifySession;

public interface SessionListAccessible {
	public void setSessionList (List<CollabrifySession> list);
	public void setGuard (int guard);
}
