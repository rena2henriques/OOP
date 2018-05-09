package specific;

import general.Utils;

/**
 * DeathExpRandomTime extends IndividualTimeGenerator and generates time between Death events as an observation of an exponential random variable. 
 * The mean value of the distribution is (1-log(1-comfort(z)))*death.
 * The comfort is a parameter of the individual and death is a parameter passed to the simulation and stored in a Population object.
 *  The class uses it's Individual in order to access this values 
 * 
 * @see IndividualTimeGenerator
 *
 */
public class DeathExpRandomTime extends IndividualTimeGenerator {
		
	/* (non-Javadoc)
	 * @see general.INumberGenerator#getNumber()
	 */
	
	/**
	 * Returns a double observation of an exponential random variable with mean value (1-log(1-comfort(z)))*death.
	 */
	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(1-individual.comfort))*individual.population.deathParam);
	}
	
}
