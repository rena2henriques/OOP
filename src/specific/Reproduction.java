package specific;

import general.Event;
import general.SimulationCommands;

import java.util.LinkedList;
import java.util.List;

public class Reproduction extends IndividualEvent{

	public Reproduction(double time, Individual individual, SimulationCommands sNC) {
		super(time, individual, sNC);
	}

	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		Individual father = this.individual;
		//current event simulated, cleans association until new one generated
		father.nextRep=null;
		List<Point> childPath;
		try {
			childPath = generateChildPath(father);
		} catch (NullPointerException e) { //case father's path is invalid
			return newEventsList;	//continues the program without killing the program
		}
		//cria filho
		Individual newDude = new Individual(father.population, childPath);
		//adiciona novo filho à lista de individuos na simulação
		father.population.getIndividuals().add(newDude);

		//criação de 3 novos eventos, death, move, reproduction
		//garantir que o tempo gerado é >0??
		double eventTime = this.getTime() + ((GridCommands) this.sNC).getCommand(GridSimulation.DEATH,newDude); 
		Death death = new Death(eventTime, newDude, sNC);
		newEventsList.add(death);
		//sets new dude's death time
		newDude.myDeath=death;
		//adds next reproduciton and move
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.MOVE,newDude); 
		if(checkDeathTime(eventTime, newDude)) {
			Move move = new Move(eventTime, newDude, sNC);
			newEventsList.add(move);
			newDude.nextMove=move;
		}
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.REP,newDude); 
		if(checkDeathTime(eventTime, newDude)) {
			Reproduction rep = new Reproduction(eventTime, newDude, sNC);
			newEventsList.add(rep);
			newDude.nextRep=rep;
		}
		//adds next reproduction for father
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.REP,father); 
		if(checkDeathTime(eventTime, father)) {
			Reproduction faRep = new Reproduction(eventTime, father, sNC);
			newEventsList.add(faRep);
			father.nextRep=faRep;
		}
		return newEventsList;
	}
	
	
	/**
	 * Receives the father of the new individual and calculates a fraction of its path, 
	 * based on the father's confort and path size.
	 * 
	 * @param ind
	 * @return prefix of father's path
	 */
	private List<Point> generateChildPath(Individual ind) throws NullPointerException {
		List<Point> myPath;
		try { 
			myPath = new LinkedList<Point>(ind.path);
		} catch (NullPointerException e) { //case father's path is invalid
			throw new NullPointerException();
		}
		double cutPathSize = myPath.size() * 0.90 + ind.comfort * (myPath.size() * 0.10);
		for(int i = (ind.path.size()-1); i >= (int)Math.ceil(cutPathSize); i--) {
			myPath.remove(i);
		}
		return myPath;
	}
	
	
}

