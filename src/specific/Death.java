package specific;


import java.util.LinkedList;
import java.util.List;

import general.Event;
import general.SimulationCommands;

public class Death extends IndividualEvent{

	public Death(double time, Individual ind, SimulationCommands sNC) {
		super(time, ind, sNC);
	}
	
	public List<Event> simulateEvent() {
		List<Event> newEventsList = new LinkedList<Event>();
		//removes the individual from the individuals list
		Individual ind = this.getIndividual();
		//se o individuo não existir, acho que o prog não crasha
		ind.population.getIndividuals().remove(ind); 
		return newEventsList; //deve ser melhor do que return null;
	}
	
}