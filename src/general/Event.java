package general;

import java.util.List;

public abstract class Event implements EventI{

	private double time; 
	
	public Event(double time) {
		this.time=time;
	}
	
	public double getTime() {
		return time;		
	}
	
	public void setTime(double time) {
		this.time=time;
	}
		
	/*public int compareTo(Event other) {
		
		if(other==null) throw new NullPointerException(); 
		
		 if (time > other.time) return 1;
		 else if (time == other.time) return 0;
		 else return -1;
		} */
	
	/**
	 * Receives an object and returns true if the event has the exact same 
	 * object associated to him
	 * 
	 * @param o
	 * @return bool
	 */
	public abstract boolean peekEvent(Object o); 
	
	@Override
	abstract public List<Event> simulateEvent(); //returns null if there are no next events
	
}
