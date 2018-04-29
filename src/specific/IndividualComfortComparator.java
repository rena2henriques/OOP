package specific;

import java.util.Comparator;

public class IndividualComfortComparator implements Comparator<Individual>{
	
	@Override
	public int compare(Individual i1,Individual i2) {
		return i1.getComfort() > i2.getComfort() ? -1 :(i1.getComfort() < i2.getComfort() ? 1 : 0);
	}
}
