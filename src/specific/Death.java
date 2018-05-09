package specific;


import java.util.LinkedList;
import java.util.List;

import general.Event;
import general.SimulationCommands;

/**
 * This class extends IndividualEvent, when Death is simulated, the associated individual is
 * elimanted from the population (and the simulation)
 * 
 * @see IndividualEvent
 */
public class Death extends IndividualEvent{

	/**
	 * Constructor
	 * Sets super class attributes
	 * @see IndividualEvent
	 * 
	 * @param time - time of the event
	 * @param ind - associated individual
	 * @param sNC - set of commands to rule movements and event creations
	 */
	public Death(double time, Individual ind, SimulationCommands sNC) {
		super(time, ind, sNC);
	}
	
	/* (non-Javadoc)
	 * @see general.Event#simulateEvent()
	 */
	/**
	 * Removes the associated individual from the population and returns an empty events list
	 * because this individual won't have another event associated, 
	 * therefore no new event is generated
	 * 
	 * @return newEventsList - empty events list to be added to the pec
	 */
	public List<Event> simulateEvent() {
		List<Event> newEventsList = new LinkedList<Event>();
		Individual ind = this.individual;
		//removes the individual from the individuals list
		ind.population.getIndividuals().remove(ind); 
		return newEventsList;
	}
	
}