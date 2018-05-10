package specific;

import general.Event;
import general.SimulationCommands;

import java.util.LinkedList;
import java.util.List;

/**
 * This class extends IndividualEvent. Simulating a reproduction involves creating a
 * son (new individual) based on the individual on whom the reproduction is being simulated 
 * and adds the son to the simulation's population, creating and setting 
 * his first three events (death, move, reproduction). It also creates and sets the next
 * father's reproduction. 
 * 
 * @see IndividualEvent
 *
 */
public class Reproduction extends IndividualEvent{

	/**
	 * Constructor
	 * Sets super class attributes.
	 * @see IndividualEvent
	 * 
	 * @param time - time of the event
	 * @param individual - associated individual
	 * @param sNC - set of commands to rule movements and event creations
	 */
	public Reproduction(double time, Individual individual, SimulationCommands sNC) {
		super(time, individual, sNC);
	}

	
	
	/* (non-Javadoc)
	 * @see general.Event#simulateEvent()
	 */
	/**
	 * Simulating a reproduction involves creating a son of the individual on whom the 
	 * reproduction is being simulated (i.e. creating a new individual), which will have 
	 * a fraction of the father's path.
	 * <p>
	 * A new sets of events (death, move, reproduction) will be created and associated to the
	 * son, and a new reproduction will be created and associated to the father.
	 * 
	 * @return newEventsList - List of events to be added to the PEC containing at most
	 * 3 new events for the new individual and 1 new event to the father 
	 */
	public List<Event> simulateEvent(){
		List<Event> newEventsList = new LinkedList<Event>();
		Individual father = this.individual;
		//current event simulated, cleans association until new one generated
		father.nextRep=null;
		List<Point> childPath;
		try {
			//gets a prefix of the the father's path
			childPath = generateChildPath(father);
		} catch (NullPointerException e) { //case father's path is invalid
			return newEventsList;	//skips this events and continues the program
		}
		//creates a new individual (son) with a already set path (father's prefix)
		Individual newDude = new Individual(father.population, childPath);
		//adds the new individual to the population
		father.population.getIndividuals().add(newDude);

		//get the new individual's death time from the set of commands
		double eventTime = this.getTime() + ((GridCommands) this.sNC).getCommand(GridSimulation.DEATH,newDude); 
		//creats a death event to be associated to the son
		Death death = new Death(eventTime, newDude, sNC);
		//adds the event to the list of events to be added to the pec
		newEventsList.add(death);
		//sets the son's death
		newDude.myDeath=death;
		//get the new individual's move time from the set of commands
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.MOVE,newDude); 
		//creats a move event to be associated to the son if it's time is smaller than the son's death time
		if(checkDeathTime(eventTime, newDude)) {
			Move move = new Move(eventTime, newDude, sNC);
			//adds the event to the list of events to be added to the pec
			newEventsList.add(move);
			//sets the son's next move
			newDude.nextMove=move;
		}
		//get the new individual's reproduction time from the set of commands
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.REP,newDude);
		//creats a reproduction event to be associated to the son if it's time is smaller than his death time
		if(checkDeathTime(eventTime, newDude)) {
			Reproduction rep = new Reproduction(eventTime, newDude, sNC);
			//adds the event to the list of events to be added to the pec
			newEventsList.add(rep);
			//sets the son's next reproduction
			newDude.nextRep=rep;
		}
		//get the new father's reproduction time from the set of commands
		eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.REP,father); 
		if(checkDeathTime(eventTime, father)) {
			//creats a reproduction event to be associated to the father if it's time is smaller than his death time
			Reproduction faRep = new Reproduction(eventTime, father, sNC);
			//adds the event to the list of events to be added to the pec
			newEventsList.add(faRep);
			//sets the father's next reproduction
			father.nextRep=faRep;
		}
		return newEventsList;
	}
	
	
	/**
	 * Receives the father of the new individual and calculates a fraction of its path, 
	 * based on the father's comfort and path size.
	 * 
	 * @param ind - Individual which will be the father of the to be created individual
	 * @return myPath - prefix of father's path
	 */
	private List<Point> generateChildPath(Individual ind) throws NullPointerException {
		List<Point> myPath;
		try { 
			//duplicates the father's path
			myPath = new LinkedList<Point>(ind.path);
		} catch (NullPointerException e) { //case father's path is invalid
			throw new NullPointerException();
		}
		//calculates the size of the son's path based on the father's confort and path
		double cutPathSize = myPath.size() * 0.90 + ind.comfort * (myPath.size() * 0.10);
		//updates the son's path to the calculated path 
		for(int i = (ind.path.size()-1); i >= (int)Math.ceil(cutPathSize); i--) {
			myPath.remove(i);
		}
		return myPath;
	}
	
	
}

