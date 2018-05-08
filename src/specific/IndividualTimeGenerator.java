package specific;

import general.INumberGenerator;

public abstract class IndividualTimeGenerator implements INumberGenerator{

	protected Individual individual;
	
	public void setIndividual(Individual ind) {
		individual =ind;
	}
	
	abstract public double getNumber();
}
