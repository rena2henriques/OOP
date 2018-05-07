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
		double time = deathTime.getNumber(ind);
		if(time<0)
			time=0;
		return time;
	}
	
	public double getMoveTime(Individual ind){
		double time= moveTime.getNumber(ind);
		if(time<0)
			time=0;
		return time;
	}
	
	public double getReproductionTime(Individual ind){
		double time= reproductionTime.getNumber(ind);
		if(time<0)
			time=0;
		return time;
	}
	
	public double getThreshold(Individual ind){
		return  threshold.getNumber(ind);

	}
}
