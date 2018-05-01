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
		Point currPos = individual.getPath().get(individual.getPath().size());
		
		List<Point> pointsList = individual.getPopulation().getMap().getPossibleMoves(currPos);
		double direction;//get random number between 0 and 1
		Point choosenPoint = chooseDirection(pointsList, direction) 
		this.individual.addToPath(choosenPoint);//adicionar novo point à pessoa	
		double eventTime = this.getTime() + gera novo tempo;
		//POR A UM IF\EXCEÇAO PARA NAO ADICIONAR EVENTOS AFTER DEATH
		newEventsList.add(new Move(eventTime))
		return newEventsList;
		
	}
	
	
	//private methods
	private Point chooseDirection(List<Point> pointsList, double direction) {
			return pointsList.get((int)(Math.floor(pointsList.size()*direction)));
	}

}
