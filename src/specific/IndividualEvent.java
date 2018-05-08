package specific;

import general.Event;
import general.SimulationCommands;

public abstract class IndividualEvent extends Event{
	
	protected Individual individual;
	protected SimulationCommands sNC;
	
	IndividualEvent(double time,Individual individual, SimulationCommands simNumCom){
		super(time);
		this.individual = individual;
		this.sNC = simNumCom;
	}
	
	public Individual getIndividual() {
		return individual;
	}
	
	public void setIndividual(Individual individual) {
		this.individual=individual;
	}
	
	public SimulationCommands getsNC() {
		return sNC;
	}
	
	
	
	
	/*public boolean peekEvent(Object o) {
		
		//PODE DAR MERDA POR CAUSA DO CAST, VERIFICAR!
		//we want this event to be peeked if o equals its individual
		try {
			if(individual== (Individual) o)
				return true;
		} catch(ClassCastException exception) {
			return false;
		}
		
		return false;
		//EXCEP�AO caso nao recebamos um individuo???
		
	}*/

	

	/**
	 * Receives the next event generated time and checks if its after the indiviual death
	 * 
	 * @param time
	 * @return boolean
	 */
	static boolean checkDeathTime(double time, Individual ind) {
		double deathTime = 0;
		try{
			deathTime = ind.getIndDeath().getTime();
		} catch (NullPointerException e) { //case has no death event associated
			return true; //means that death event will not happen before simulation ends
		}				 
		if (time > deathTime) {
			return false;
		}
		return true;
	}
	
}
