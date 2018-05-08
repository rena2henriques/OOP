package specific;

import general.Utils;

public class MoveExpRandomTime extends IndividualTimeGenerator {

	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(individual.getComfort()))*individual.getPopulation().moveParam);
	}

}
