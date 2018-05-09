package specific;

import general.Event;
import general.SimulationCommands;

public abstract class IndividualEvent extends Event{
	
	protected Individual individual;
	protected SimulationCommands sNC;
	
	public IndividualEvent(double time,Individual individual, SimulationCommands simNumCom){
		super(time);
		this.individual = individual;
		this.sNC = simNumCom;
	}
	

	/**
	 * Receives the next event generated time and checks if its after the indiviual death
	 * 
	 * @param time
	 * @return boolean
	 */
	public static boolean checkDeathTime(double time, Individual ind) {
		double deathTime = 0;
		try{
			deathTime = ind.myDeath.getTime();
		} catch (NullPointerException e) { //case has no death event associated
			return true; //means that death event will not happen before simulation ends
		}				 
		if (time > deathTime) {
			return false;
		}
		return true;
	}
	
}
