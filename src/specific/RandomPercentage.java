package specific;

import general.INumberGenerator;
import general.Utils;

/**
 * RandomPercentage extends INumberGenerator and generates an uniform random between 0 and 1. 
 * @see INumberGenerator
 *
 */
public class RandomPercentage implements INumberGenerator{

	/* (non-Javadoc)
	 * @see general.INumberGenerator#getNumber()
	 */
	/**
	 * Returns an uniform random between 0 and 1.
	 *
	 */
	public double getNumber(){
		return Utils.getUniformRandom(0,1);
	}
}
