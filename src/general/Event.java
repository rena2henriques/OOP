package general;

import java.util.List;

public abstract class Event implements EventI, Comparable<Event>{

	protected double time; 
	
	public Event(double time) {
		this.time=time;
	}
	
	public double getTime() {
		return time;		
	}
	
	public void setTime(double time) {
		this.time=time;
	}
		
	public int compareTo(Event other) {
		 if (time > other.time) return 1;
		 else if (time == other.time) return 0;
		 else return -1;
		} 
	
	@Override
	abstract public List<Event> simulateEvent(); //returns null if there are no next events
	
}
