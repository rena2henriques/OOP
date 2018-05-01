package specific;

import general.INumberGenerator;
import general.Utils;

public class DeathExpRandomTime implements INumberGenerator<Individual> {
		
	public double getNumber(Individual individual){
		return Utils.getExpRandom((1-Math.log(1-individual.getComfort()))*individual.getPopulation().deathParam);
	}
	
}
