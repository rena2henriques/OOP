package general;

import java.util.Comparator;

/**
 * TimeEventComparator implements Comparator<Event> and compares events of type Event according to their time, in ascendant order.
 *
 * @see Event
 */
public class TimeEventComparator implements Comparator<Event>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	
	/**
	 * Compares events according to their time.
	 * 
	 * @param e1 - the first event to be compared.
	 * @param e2 - the second event to be compared
	 * 
	 * @return 1 if the time of event e1 is bigger than the time of event e2, 0 if they are equal and -1 otherwise. 
	 * 
	 */
	
	@Override
	public int compare(Event e1,Event e2) {
		return e1.time < e2.time ? -1 :(e1.time > e2.time ? 1 : 0);
	}

}
