package specific;

import java.util.LinkedList;
import java.util.List;

import general.Event;

public class Death extends IndividualEvent{

	public Death(double time, Individual ind) {
		super(time, ind);
	}
	
	public List<Event> simulateEvent() {
		List<Event> newEventsList = new LinkedList<Event>();
		//removes the individual from the indiviuals list
		this.individual.getPopulation().getIndividuals().remove(individual);
		return newEventsList; //deve ser melhor do que return null;
	}
}
