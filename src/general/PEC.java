package general;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class provides an implementation of the PECI interface.
 * <p>
 * It provides a priority queue of events that have a time (events from class Event). 
 * The events of the PEC are ordered according to the TimeEventComparator provided in the construction of the priority queue.
 * A priority queue does not permit null elements, so it's impossible to add null events to the PEC.
 * <p>
 * The operations made to the PEC, such as adding, removing and clearing events are implemented with methods from java.util.PriorityQueue.
 * The methods nextEvent and getEvent are implemented with the poll and peek methods, respectively, so they return a null reference when there are no more events.
 * 
 * @see PECI, TimeEventComparator, Event
 */
public class PEC implements PECI<Event>{  
	
	/**
	 * Queue of events to be simulated.
	 */
	private Queue<Event> events;
	
	/**
	 * No argument constructor.
	 * Creates a PriorityQueue with the default initial capacity (11) that orders the events according to the specified time comparator.
	 * 
	 * @see TimeEventComparator
	 */
	public PEC() {
		events= new PriorityQueue<Event>(new TimeEventComparator());
	}
	
	/**
	 * Argument constructor.
	 * <p>
	 * Creates a PriorityQueue with the specified initial capacity that orders the events according to the specified time comparator.
	 * 
	 * @param initialCapacity - the initial capacity of the priority queue of this PEC.
	 * 
	 * @see TimeEventComparator
	 */
	public PEC(int initialCapacity) {
		if(initialCapacity==0) {
			events= new PriorityQueue<Event>(new TimeEventComparator());
		} else {
			events= new PriorityQueue<Event>(initialCapacity,new TimeEventComparator());
		}
	}
	
	/**
	 * @return the priority queue of this PEC.
	 */
	public Queue<Event> getEvents() {
		return events;
	}

	/* (non-Javadoc)
	 * @see general.PECI#addEvent(general.EventI)
	 */
	/** 
	 * Appends the specified event to the PEC.
	 * <p>
	 * If the event to be added to the PEC is null, an error message is issued and the program is exited.
	 */
	public void addEvent(Event event) {
		try {
			events.add(event);
		} catch(NullPointerException exc) {
			System.err.println("Trying to add a null event to the PEC");
			System.exit(-1);
		}
	}
	
	/* (non-Javadoc)
	 * @see general.PECI#nextEvent()
	 */
	/** 
	 * Retrieves and removes the first event (with smaller time) in this PEC
	 *  or returns null if there are no more events.
	 */
	public Event nextEvent() {
		Event event= events.poll(); 
		return event;
	}
	
	
	/* (non-Javadoc)
	 * @see general.PECI#getEvent()
	 */
	/**
	 * Retrieves, but does not remove, the first event in this PEC, or returns null if there are no more events.
	 * 
	 * @return  the event with the smallest time in the PEC, or null if this PEC is empty
	 */

	public Event getEvent() {
		return events.peek(); 
	}
	
	/* (non-Javadoc)
	 * @see general.PECI#clear()
	 */
	public void clear() {
		events.clear();
	}
	
	/* (non-Javadoc)
	 * @see general.PECI#removeEvent(general.EventI)
	 */
	/**
	 * If the event is present in the container, it removes occurrences of the specified event. 
	 * If the event is not present, an error message is issued.
	 */
	public void removeEvent(Event event) {

		if(!events.remove(event)) {
			System.err.println("Error: try to remove an event that doesn't exist");	
		}
	}
	
	/* (non-Javadoc)
	 * @see general.PECI#isEmpty()
	 */
	public boolean isEmpty() {
		return events.isEmpty();
	}
	
	
	
}
