package specific;

import general.INumberGenerator;
import general.Utils;

public class RandomPercentage implements INumberGenerator<Individual> {

	public double getNumber(Individual individual){
		return Utils.getUniformRandom(1);
	}
}
