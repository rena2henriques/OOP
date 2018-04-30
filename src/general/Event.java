package general;

import java.util.List;

public abstract class Event implements EventI {

	private double time;
	
	public double getTime() {
		return time;		
	}
	
	public void setTime(double _time) {
		time=_time;
	}
	
	abstract public List<Event> simulateEvent(); //returns list of events or an empty list(or null?) if there are no next events
	
	abstract public Object getEventObject();
}
