package specific;

import general.INumberGenerator;

public class SimulationNumberCommands {
	
	private INumberGenerator<Individual> deathTime;
	private INumberGenerator<Individual> moveTime;
	private INumberGenerator<Individual> reproductionTime;
	private INumberGenerator<Individual> threshold;

	public SimulationNumberCommands(INumberGenerator<Individual> deathTime, INumberGenerator<Individual> moveTime, 
			INumberGenerator<Individual> reproductionTime, INumberGenerator<Individual> threshold){
		this.deathTime=deathTime;
		this.moveTime=moveTime;
		this.reproductionTime=reproductionTime;
		this.threshold=threshold;
		}
	
	public double getDeathTime(Individual ind){
		return deathTime.getNumber(ind);
	}
	
	public double getMoveTime(Individual ind){
		return moveTime.getNumber(ind);
	}
	
	public double getReproductionTime(Individual ind){
		return reproductionTime.getNumber(ind);
	}
	
	public double getThreshold(Individual ind){
		return threshold.getNumber(ind);
	}
}
