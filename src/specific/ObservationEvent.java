package specific;

import java.util.LinkedList;
import java.util.List;

import general.Event;
public class ObservationEvent extends Event{
	
	protected GridSimulation grid;
	
	public ObservationEvent(double time, GridSimulation grid) {
		super(time);
		this.grid = grid;
	}
	
	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		printObservation(); //acho que n e preciso mandar argumento nenhum
		newEventsList.add(new ObservationEvent(this.getTime()+grid.getFinalTime()/20, this.grid));
		grid.setNumEvents(grid.getNumEvents()-1);
		return newEventsList;
		
	}
	
	/**
	 * Prints observation state
	 */
	private void printObservation() {
		String finalHit="no";
		
		double bestScore=grid.population.getBestInd().comfort;
		
		if(grid.population.finalPointHit) {
			finalHit="yes";
			bestScore=(double )grid.population.getBestInd().cost;
		}
		System.out.println("Observation " + (int)(this.getTime()/(grid.getFinalTime()/20)) + ":"); //Observation number:
		System.out.println("\t\tPresent instant: " + this.getTime());
		System.out.println("\t\tNumber of realized events: " + grid.getNumEvents());
		System.out.println("\t\tPopulation size: " + grid.population.getIndividuals().size());
		System.out.println("\t\tFinal point has been hit: " + finalHit);
		System.out.println("\t\tPath of the best fit individual: " + grid.population.getBestInd().pathString());
		System.out.println("\t\tCost/Confort: " + bestScore );

	}
	
	
}
