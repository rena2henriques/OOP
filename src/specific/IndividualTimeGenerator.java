package specific;

import general.INumberGenerator;

/**
 * IndividualTimeGenerator is an abstract implementation of the interface INumberGenerator.
 * The method provided by the interface it's not yet implemented so classes that extend this class have to implement it.
 * <p>
 * It associates an Individual to the generator, so that the number returned in getNumber() 
 * will be generated according to the specified Individual.
 * 
 * @see INumberGenerator
 */
public abstract class IndividualTimeGenerator implements INumberGenerator{

	/**
	 * Individual associated to the number generator
	 */
	protected Individual individual;
	
	/**
	 * @param ind - individual to associate to this generator
	 */
	public void setIndividual(Individual ind) {
		individual =ind;
	}
	
}
