package specific;

import general.Event;

public abstract class IndividualEvent extends Event{
	
	private Individual individual;
	
	IndividualEvent(double time,Individual individual){
		super(time);
		this.individual = individual;
	}
	
	public Individual getIndividual() {
		return individual;
	}
	
	public void setIndividual(Individual individual) {
		this.individual=individual;
	}
	
	/**
	 * Receives the next event generated time and checks if its after the indiviual death
	 * 
	 * @param time
	 * @return boolean
	 */
	boolean checkDeathTime(double time, Individual ind) {
		if (time > ind.getIndDeath().getTime()) {
			System.out.println("event not added");
			return false;
		}
		return true;
	}
	
}
