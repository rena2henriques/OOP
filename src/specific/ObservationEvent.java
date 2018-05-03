package specific;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import general.Event;
import general.INumberGenerator;
import general.Point;

public class ObservationEvent extends Event{
	GridSimulation grid;
	
	ObservationEvent(double time, GridSimulation grid) {
		super(time);
		this.grid = grid;
	}
	
	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		printObservation(); //acho que n e preciso mandar argumento nenhum
		newEventsList.add(new ObservationEvent(this.getTime()+grid.getFinalTime()/20, this.grid));
		return newEventsList;
		
	}
	
	/**
	 * Prints observation state
	 */
	private void printObservation() {
		System.out.println("Observation" + (this.getTime()/grid.getFinalTime()/20) + ":"); //Observation number:
		System.out.println("Present instant: " + this.getTime());
		System.out.println("Number of realized events: " + grid.getNumEvents());
		System.out.println("Population size: " + grid.getPopulation().getIndividuals().size());
		//vai returnar true\false, e não yes\no, confirmar...
		System.out.println("Final point has been hit: " + grid.getPopulation().finalPointHit);
		System.out.println("Path of the best fit individual: " + grid.getPopulation().getBestInd());
		System.out.println("Cost/Confort: " + grid.getPopulation().getBestInd().getCost() + "/" + grid.getPopulation().getBestInd().getComfort());
		//POR AS COISAS NO FORMATO QUE ELA QUER - PERGUNTAR SE TEM QUE SER IGUALLZINHO
		//POR TABS /t?
	}
	
	public boolean peekEvent(Object o) {
		//nunca queremos n�o simular o nosso evento
		return false;
	}
	
}
