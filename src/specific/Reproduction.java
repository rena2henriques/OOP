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
		Individual newInd = new Individual(this.individual.getPopulation(), childPath);
		individual.getPopulation().getIndividuals().add(newInd);
		
		return null;
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
