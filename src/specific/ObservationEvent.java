package specific;

import java.util.LinkedList;
import java.util.List;

import general.Event;

public class ObservationEvent extends Event{
	GridSimulation grid;
	
	ObservationEvent(double time, GridSimulation grid) {
		super(time);
		this.grid = grid;
	}
	
	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		printObservation(); //acho que n e preciso mandar argumento nenhum
		double nextObsTime = this.getTime(); //+getFinalSimulationTime
		newEventsList.add(new ObservationEvent(nextObsTime, this.grid));
		return newEventsList;
		
	}
	
	private void printObservation() {
		//TODO
		
		//POR AS COISAS NO FORMATO QUE ELA QUER - PERGUNTAR SE TEM QUE SER IGUALLZINHO
	}
	
	public boolean peekEvent(Object o) {
		//nunca queremos não simular o nosso evento
		return false;
	}
}
