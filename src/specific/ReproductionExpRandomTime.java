package specific;

import general.Utils;

public class ReproductionExpRandomTime extends IndividualTimeGenerator{

	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(individual.comfort))*individual.population.reproductionParam);
	}

}
