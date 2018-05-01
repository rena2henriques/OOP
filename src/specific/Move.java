package specific;

import general.Event;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Move extends IndividualEvent {
	
	//constructor
	public Move(double time, Individual ind) {
		super(time, ind);

	}
	
	//inherited implemetions
	public List<Event> simulateEvent() {

		List<Event> newEventsList = new LinkedList<Event>();
		//gets individual current position, which is the last one of the path
		Point currPos = individual.getPath().get(individual.getPath().size());
		//gets possible individual moves
		List<Point> pointsList = individual.getPopulation().getMap().getPossibleMoves(currPos);
		//gets a number between 0 and 1
		double direction;//get random number between 0 and 1
		//chooses next point to move, based on available moves and the generated number
		Point choosenPoint = chooseDirection(pointsList, direction) 
		//adds choosen point to the path
		this.individual.addToPath(choosenPoint);//adicionar novo point à pessoa	
		//creates next move
		double eventTime = this.getTime() + gera novo tempo;
		if(checkDeathTime(eventTime, this.getIndividual()))
			newEventsList.add(new Move(eventTime))
		return newEventsList;
	}
	
	
	//private methods
	
	
	/**
	 * From a list of up to 4 positions, chooses the point at the 
	 * index based on a number between 0 and 1.
	 * 
	 * @param pointsList
	 * @param direction
	 * @return Next point to move
	 */
	private Point chooseDirection(List<Point> pointsList, double direction) {
			return pointsList.get((int)(Math.floor(pointsList.size()*direction)));
	}

}
