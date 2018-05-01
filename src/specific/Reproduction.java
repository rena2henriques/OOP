package specific;

import general.Event;

import java.util.LinkedList;
import java.util.List;

public class Reproduction extends IndividualEvent{

	public Reproduction(double time, Individual individual) {
		super(time, individual);
	}

	public List<Event> simulateEvent(){
		LinkedList<Point> childPath = generateChildPath(this.individual);
		Individual newDude = new Individual(this.individual.getPopulation(), childPath);
		individual.getPopulation().getIndividuals().add(newInd);

		//POR A UM IF\EXCEÇAO PARA NAO ADICIONAR EVENTOS AFTER DEATH
		List<Event> newEventsList = new LinkedList<Event>();
		double eventTime = this.getTime() + gera novo tempo;
		newEventsList.add(new Death(eventTime, newDude));
		eventTime = this.getTime() + gera novo tempo;
		newEventsList.add(new Move(eventTime, newDude));
		eventTime = this.getTime() + gera novo tempo;
		newEventsList.add(new Reproduction(eventTime, newDude));
		
		return newEventsList;
	}
	
	private LinkedList<Point> generateChildPath(Individual ind) {
		LinkedList<Point> myPath = new LinkedList<Point>(ind.getPath());
		double cutPathSize = myPath.size() * 0.90 + ind.getComfort() * (myPath.size() * 0.10);
		for(int i = (int)Math.ceil(cutPathSize); i < ind.getPath().size(); i++) {
			myPath.remove(i);
		}
		return myPath;
	}
}
