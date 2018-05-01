package specific;

import general.Event;

public abstract class IndividualEvent extends Event{
	
	private Individual individual;
	
	IndividualEvent(double time,Individual individual){
		super(time);
	}
	
	public Individual getIndividual() {
		return individual;
	}

}
