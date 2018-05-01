package general;

import java.util.List;

public abstract class Event implements EventI {

	protected double time; 
	
	public double getTime() {
		return time;		
	}
	
	public void setTime(double time) {
		this.time=time;
	}
	
	abstract public List<Event> simulateEvent(); //returns null if there are no next events
	
}
