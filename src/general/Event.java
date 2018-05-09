package general;

import java.util.List;

/**
 * This class provides an initial implementation of the EventI interface
 * to minimize the effort required to implement it in the case where the event is driven by its time.
 *  <p>
 * The programmer needs only to extend this class and provide a constructor and implementations for the simulateEvent method.
 * @see EventI
 * 
 */
public abstract class Event implements EventI{

	/**
	 * Time of the event.
	 */
	protected double time; 
	
	/**
	 * Constructor.
	 * <p>
	 * Creates an Event with the specified time.
	 * 
	 * @param time - time for this event
	 * 
	 */
	public Event(double time) {
		this.time=time;
	}
	
	/**
	 * Returns the time of this event.
	 * 
	 * @return time of the event passed to the constructor
	 */
	public double getTime() {
		return time;		
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
