package specific;

import general.INumberGenerator;
import general.Utils;

public class MoveExpRandomTime implements INumberGenerator<Individual> {

	public double getNumber(Individual individual){
		return Utils.getExpRandom((1-Math.log(individual.getComfort()))*individual.getPopulation().moveParam);
	}

}
