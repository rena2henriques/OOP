package specific;

import general.Event;

import java.util.LinkedList;
import java.util.List;
import general.SimulationCommands;

public class Move extends IndividualEvent {
	
	//constructor
	public Move(double time, Individual ind, SimulationCommands sNC) {
		super(time, ind, sNC);
	}
	
	//inherited implemetions
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
	
			Point choosenPoint = chooseDirection(pointsList, direction);
			//adds choosen point to the path
			ind.addToPath(choosenPoint);//adicionar novo point à pessoa
			checkBestFitIndividual(ind, ind.population);
			//creates next move
			double eventTime = this.getTime() + ((GridCommands)this.sNC).getCommand(GridSimulation.MOVE,ind); //temp
			if(checkDeathTime(eventTime, ind)) {
				Move move = new Move(eventTime, ind, this.sNC);
				newEventsList.add(move);
				ind.nextMove=move;
			}
		} //else there are no available moves
		return newEventsList;
	}
		
	
	/**
	 * From a list of up to 4 positions, chooses the point at the 
	 * index based on a number between 0 and 1.
	 * 
	 * @param pointsList
	 * @param direction
	 * @return Next point to move
	 */
	protected Point chooseDirection(List<Point> pointsList, double direction) {
			return pointsList.get((int)(Math.floor(pointsList.size()*direction)));
	}
	
	
	
	/**
	 * Evaluates if the received individual is current best fit, and updates
	 * 
	 * @param currentInd
	 * @param pop
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
	 * Evaluates if the received individual is current best fit of the population
	 * @param currentInd
	 * @param pop
	 * @return
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
	 * Current individual is now the population's best fit
	 * 
	 * @param currentInd
	 * @param pop
	 */
	private void updateBestFit(Individual currentInd, Population pop) {	
			pop.bestInd=currentInd.getPathIndividual(); 	
	}
	
}

