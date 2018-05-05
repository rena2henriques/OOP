package specific;

import java.util.Comparator;

/**
 * Implements Comparator<Individual> and compares individuals according to their comfort, in descendant order.
 * 
 * @author Group 6
 *
 */
public class IndividualComfortComparator implements Comparator<Individual>{
	
	@Override
	public int compare(Individual i1,Individual i2) {
		return i1.getComfort() > i2.getComfort() ? -1 :(i1.getComfort() < i2.getComfort() ? 1 : 0);
	}
}
