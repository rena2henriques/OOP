package general;

import java.util.Comparator;

public class TimeEventComparator implements Comparator<Event>{
	
	@Override
	public int compare(Event e1,Event e2) {
		return e1.getTime() < e2.getTime() ? -1 :(e1.getTime() > e2.getTime() ? 1 : 0);
	}

}
