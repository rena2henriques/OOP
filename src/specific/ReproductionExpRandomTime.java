package specific;

import general.Utils;

/**
 * ReproductionExpRandomTime extends IndividualTimeGenerator and generates time between Reproduction events as an observation of an exponential random variable. 
 * The mean value of the distribution is (1-log(comfort(z)))*reproduction.
 * The comfort is a parameter of the individual and reproduction is a parameter passed to the simulation and stored in a Population object.
 *  The class uses it's Individual in order to access this values 
 * 
 * @see IndividualTimeGenerator
 *
 */
public class ReproductionExpRandomTime extends IndividualTimeGenerator{

	/* (non-Javadoc)
	 * @see general.INumberGenerator#getNumber()
	 */
	
	/**
	 * Returns a double observation of an exponential random variable with mean value (1-log(comfort(z)))*reproduction.
	 */
	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(individual.comfort))*individual.population.reproductionParam);
	}

}
