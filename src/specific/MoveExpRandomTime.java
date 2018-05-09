package specific;

import general.Utils;

/**
 * MoveExpRandomTime extends IndividualTimeGenerator and generates time between Move events as an observation of an exponential random variable. 
 * The mean value of the distribution is (1-log(comfort(z)))*move.
 * The comfort is a parameter of the individual and move is a parameter passed to the simulation and stored in a Population object.
 *  The class uses it's Individual in order to access this values 
 * 
 * @see IndividualTimeGenerator
 *
 */
public class MoveExpRandomTime extends IndividualTimeGenerator {

	/* (non-Javadoc)
	 * @see general.INumberGenerator#getNumber()
	 */
	
	/**
	 * Returns a double observation of an exponential random variable with mean value (1-log(comfort(z)))*move.
	 */
	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(individual.comfort))*individual.population.moveParam);
	}

}
