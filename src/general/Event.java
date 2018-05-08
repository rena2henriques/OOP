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

	/**
	 * time of the event
	 */
	protected double time; 
	
	/**
	 * Constructor
	 * Creates an Event with the specified time
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

	
	/* (non-Javadoc)
	 * @see general.EventI#simulateEvent()
	 */
	/**
	 * The programmer that extends this class should return a list of events of the class Event
	 * 
	 * */
	@Override
	abstract public List<Event> simulateEvent(); 
	
}
