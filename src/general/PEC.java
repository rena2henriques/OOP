package general;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * This class provides an implementation of the PECI interface.
 * It provides a priority queue of events that have a time (class Event) and orders them accordingly to that attribute.
 * 
 * @author Grupo 6
 *
 */
public class PEC implements PECI<Event>{  
	
	/**
	 * Queue of events to be simulated
	 */
	private Queue<Event> events;
	
	/**
	 * No argument constructor.
	 * Creates a PriorityQueue with the default initial capacity that orders the events according to the specified time comparator.
	 */
	public PEC() {
		events= new PriorityQueue<Event>(new TimeEventComparator());
	}
	
	/**
	 * Arg constructor.
	 * Creates a PriorityQueue with the specified initial capacity that orders the events according to the specified time comparator
	 * 
	 * @param initialCapacity 
	 */
	public PEC(int initialCapacity) {
		if(initialCapacity==0) {
			events= new PriorityQueue<Event>(new TimeEventComparator());
		} else {
			events= new PriorityQueue<Event>(initialCapacity,new TimeEventComparator());
		}
	}
	
	/**
	 * @return the priority queue with the events
	 */
	public Queue<Event> getEvents() {
		return events;
	}

	/* (non-Javadoc)
	 * @see general.PECI#addEvent(general.EventI)
	 */
	public void addEvent(Event e) {
		events.add(e);
	}
	
	/** {@inheritDoc}
	 * Retrieves and removes the first event (with smaller time) in this queue
	 *  or returns null if there are no events.
	 */
	public Event nextEvent() {
		Event event= events.poll(); 
		return event;
	}
	
	/** {@inheritDoc}
	 * Retrieves, but does not remove, the first event of this queue, or returns null if there are no more events.
	 * 
	 * @return  the head of the priority queue, or null if this queue is empty
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
