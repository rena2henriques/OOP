package specific;

import java.util.List;

import general.Event;

public class Death extends IndividualEvent{

	public Death(double time, Individual ind) {
		super(time, ind);
	}
	
	public List<Event> simulateEvent() {
		//removes the individual from the indiviuals list
		this.individual.getPopulation().getIndividuals().remove(individual);
		return null; //se isto der erro talvez tenha de retornar uma empty linked list??
	}
}
