package specific;

import java.util.Comparator;

/**
 * <p>IndividualComfortComparator implements Comparator of objects of type Individual and compares Individuals according 
 * to their comfort, in descendant order.
 * 
 *	@see Individual
 */
public class IndividualComfortComparator implements Comparator<Individual>{
	
	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	
	/**
	 * Compares individuals according to their comfort.
	 * 
	 * @param i1 - the first individual to be compared.
	 * @param i2 - the second individual to be compared
	 * 
	 * @return 1 if the comfort of individual i1 is smaller than the comfort of individual i2, 0 if they are equal and -1 otherwise. 
	 * 
	 */
	
	@Override
	public int compare(Individual i1,Individual i2) {
		return i1.comfort > i2.comfort ? -1 :(i1.comfort < i2.comfort ? 1 : 0);
	}
}
