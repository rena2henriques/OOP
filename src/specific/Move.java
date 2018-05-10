package specific;

import general.Event;

import java.util.LinkedList;
import java.util.List;
import general.SimulationCommands;

/**
 * This class extends individualEvent and its simulation chooses the next point of the map
 * to which the individual will go, and moves him by adding that point to his path.
 * <p>
 * Afterwards creates the individual's next move, to be added to the PEC and associates it
 * to the individual.
 * 
 * @see IndividualEvent
 *
 */
public class Move extends IndividualEvent {
	
	
	/**
	 * Constructor
	 * Sets super class attributes
	 * @see IndividualEvent
	 * 
	 * @param time - time of the event
	 * @param ind - associated individual
	 * @param sNC - set of commands to rule movements and event creations
	 */
	public Move(double time, Individual ind, SimulationCommands sNC) {
		super(time, ind, sNC);
	}
	
	
	/* (non-Javadoc)
	 * @see general.Event#simulateEvent()
	 */
	/**
	 * Simulating a move, chooses from the individual's 
	 * possible moves (based on is last position) his next position 
	 * according to the value retrieved from
	 * the sNC (set of commands). <p>
	 * Afterwards creates the individual's next move with a time retrieved 
	 * from the set of commands and adds, to be added to the PEC if that move is to happen
	 * before the individual's death.
	 * 
	 * @return newEventsList - list of events to be added to the PEC
	 * containing only the associated individual's next move
	 */
	public List<Event> simulateEvent() {
		
		Individual ind = this.individual;
		//current event simulated, cleans association until new one generated
		ind.nextMove=null;
		List<Event> newEventsList = new LinkedList<Event>();
		//gets individual current position, which is the last one of the path
		Point currPos = ind.path.get((ind.path.size()) - 1);
		//gets possible individual moves
		List<Point> pointsList = ind.population.getMap().getPossibleMoves(currPos);
		//chooses next point to move, based on available moves and the generated number
		if(!pointsList.isEmpty()) {
			//gets a number between 0 and 1
			double direction= this.sNC.getCommand(GridSimulation.THRESH);
			//chooses a point according to the number between 0 and 1
			Point choosenPoint = chooseDirection(pointsList, direction);
			//adds choosen point to the path
			ind.addToPath(choosenPoint);
			//checks if with the new move, this individual is the best fit
			checkBestFitIndividual(ind, ind.population);
			//gets from the set of commands the next event time
			double eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.MOVE,ind); //temp
			//if the gotten time is smaller that the individual's death time, creates a new move
			if(checkDeathTime(eventTime, ind)) {
				//creates new move
				Move move = new Move(eventTime, ind, this.sNC);
				//adds it to the events list to be added to the pec
				newEventsList.add(move);
				//associates it to the individual as well
				ind.nextMove=move;
			}
		}
		//returns empty case the new move had time after the individual's death
		return newEventsList;
	}
		
	
	/**
	 * From a list of up to 4 positions, chooses the point at the 
	 * index proportional to the generated number between 0 and 1.
	 * 
	 * @param pointsList - List of available points for the individual to go
	 * @param direction - Number between 0 and 1 
	 * @return Next point to move
	 */
	protected Point chooseDirection(List<Point> pointsList, double direction) {
			return pointsList.get((int)(Math.floor(pointsList.size()*direction)));
	}
	
	
	
	/**
	 * Evaluates if the received individual is the current best fit, and updates.
	 * 
	 * @param currentInd - Individual on whom the move was simulated
	 * @param pop - Simulation's population 
	 */
	protected void checkBestFitIndividual(Individual currentInd, Population pop) {
			
		//if none of the individuals has reached the final point before and the current individual
		//reaches it, we change the flag and set the current individual as the best individual
		if(!pop.finalPointHit && pop.map.isFinal(currentInd.path.get(currentInd.path.size()-1))) {
			pop.finalPointHit=true; 
			updateBestFit(currentInd, pop);			
		}	
		//we only check if it is the best individual if the final point hasn't been reached 
		//or if it has, we only check if our individual is also in the final point
		else if(!pop.finalPointHit || (pop.finalPointHit &&  pop.map.isFinal(currentInd.path.get(currentInd.path.size()-1)))) {
			if(checkIfIsBestFit(currentInd, pop))
				updateBestFit(currentInd, pop);			
		}
	}
	
	
	/**
	 * Evaluates if the received individual is current best fit of the population.
	 * Takes into account if the final point was hit or not.
	 * 
	 * @param currentInd - Individual that is being evaluated
	 * @param pop - Simulation's population to get the current best individual
	 * @return boolean - True if the received individual is the new best fit, false otherwise
	 */
	private boolean checkIfIsBestFit(Individual currentInd, Population pop) {
			
		if(pop.bestInd==null)
			return true;
			
		//if none of the individuals has reached the final point we check the comfort
		else if(!pop.finalPointHit && currentInd.comfort>pop.bestInd.comfort) {
			return true;
		} 
		//if an individual has already reached the final point, we check the cost
		else if(pop.finalPointHit && currentInd.cost < pop.bestInd.cost) {
			return true;
		}
			
		return false;
	}
		
	/**
	 * Set individual on whom the move was simulated and sets him to the population's best fit
	 * 
	 * @param currentInd - Evaluated individual
	 * @param pop - Simulation's population
	 */
	private void updateBestFit(Individual currentInd, Population pop) {	
			pop.bestInd=currentInd.getPathIndividual(); 	
	}
	
}

