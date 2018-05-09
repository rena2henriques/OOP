package specific;

import java.util.LinkedList;
import java.util.List;

import general.Event;


/**
 * This class extends Event and provides an observation with information about the state of the simulation.
 * 
 * @see Event
 * 
 */
public class ObservationEvent extends Event{
	
	/**
	 * Running simulation
	 */
	protected GridSimulation grid;
	
	/**
	 * Constructor
	 * @see Event
	 * 
	 * @param time - time of the observation
	 * @param grid - current simulation
	 */
	public ObservationEvent(double time, GridSimulation grid) {
		super(time);
		this.grid = grid;
	}
	
	/* (non-Javadoc)
	 * @see general.Event#simulateEvent()
	 */
	/**
	 * Prints the current status of the population and creates the next observation to be
	 * added to the PEC.
	 * 
	 * @return newEventsList - list of events to be added to the PEC containing only the next observation 
	 */
	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		//prints status of the population
		printObservation();
		//creates next observation with time = current time + finalTime/20
		newEventsList.add(new ObservationEvent(this.getTime()+grid.getFinalTime()/20, this.grid));
		//we don't consider observations as a realized event
		grid.setNumEvents(grid.getNumEvents()-1);
		return newEventsList;
		
	}
	
	/**
	 * Prints observation state.
	 */
	private void printObservation() {
		String finalHit="no";
		
		double bestScore=grid.population.getBestInd().comfort;
		
		if(grid.population.finalPointHit) {
			finalHit="yes";
			bestScore=(double )grid.population.getBestInd().cost;
		}
		System.out.println("Observation " + (int)(this.getTime()/(grid.getFinalTime()/20)) + ":"); //Observation number:
		System.out.println("\t\tPresent instant:\t\t\t" + this.getTime());
		System.out.println("\t\tNumber of realized events:\t\t" + grid.getNumEvents());
		System.out.println("\t\tPopulation size:\t\t\t" + grid.population.getIndividuals().size());
		System.out.println("\t\tFinal point has been hit:\t\t" + finalHit);
		System.out.println("\t\tPath of the best fit individual:\t" + grid.population.getBestInd().pathString());
		System.out.println("\t\tCost/Confort:\t\t\t\t" + bestScore );

	}
	
	
}
