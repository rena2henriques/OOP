package general;

import java.util.List;

public abstract class Event implements EventI, Comparable<Event>{

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
		
	public int compareTo(Event other) {
		
		//TODO ver se isto é mesmo assim e se deviamos redefinir o equals
		if(other==null) throw new NullPointerException(); 
		
		 if (time > other.time) return 1;
		 else if (time == other.time) return 0;
		 else return -1;
		} 
	
	public abstract boolean peekEvent(Object o); 
	
	@Override
	abstract public List<Event> simulateEvent(); //returns null if there are no next events
	
}
