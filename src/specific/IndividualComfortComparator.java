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
		return i1.comfort > i2.comfort ? -1 :(i1.comfort < i2.comfort ? 1 : 0);
	}
}
