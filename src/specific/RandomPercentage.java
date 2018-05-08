package specific;

import general.INumberGenerator;
import general.Utils;

public class RandomPercentage implements INumberGenerator{

	public double getNumber(){
		return Utils.getUniformRandom(0,1);
	}
}
