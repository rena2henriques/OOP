package specific;

import general.Utils;

public class DeathExpRandomTime extends IndividualTimeGenerator {
		
	public double getNumber(){
		return Utils.getExpRandom((1-Math.log(1-individual.comfort))*individual.population.deathParam);
	}
	
}
