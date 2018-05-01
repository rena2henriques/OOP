package specific;

import general.Event;

public abstract class IndividualEvent extends Event{
	
	Individual individual;
	
	IndividualEvent(double time,Individual individual){
		super(time);
		this.individual = individual;
	}
	
}
