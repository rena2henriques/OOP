package general;

import java.util.Comparator;

/**
 * Implements Comparator<Event> and compares events according to time, in ascendant order.
 * 
 * @author Group 6
 *
 */
public class TimeEventComparator implements Comparator<Event>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Event e1,Event e2) {
		return e1.getTime() < e2.getTime() ? -1 :(e1.getTime() > e2.getTime() ? 1 : 0);
	}

}
