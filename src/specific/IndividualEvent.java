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
	
}
