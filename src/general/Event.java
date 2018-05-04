package general;

import java.util.List;

/**
 * This class provides an initial implementation of the EventI interface
 * to minimize the effort required to implement this interface in the case where the Event is driven by its time
 * 
 * The programmer needs only to extend this class and provide implementations for simulateEvent method and also a constructor.
 * 
 * 
 * @author Grupo 6
 *
 */
public abstract class Event implements EventI{

	private double time; 
	
	/**
	 * Constructor
	 * 
	 * @param time time of the event
	 */
	public Event(double time) {
		this.time=time;
	}
	
	/**
	 * @return time of the event passed to the constructor
	 */
	public double getTime() {
		return time;		
	}
	
	/**
	 * Set time of the event
	 * 
	 * @param time value to set 
	 */
	public void setTime(double time) {
		this.time=time;
	}
		
	//TODO TIRAR ESTES METODOS COMENTADOS
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
	/*public abstract boolean peekEvent(Object o); */
	
	
	/* (non-Javadoc)
	 * @see general.EventI#simulateEvent()
	 */
	/**
	 * The programmer that extends this class should return a list of events of this class
	 * 
	 * */
	@Override
	abstract public List<Event> simulateEvent(); 
	
}
