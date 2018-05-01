package specific;

import general.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Move extends Event {
	Individual individual; //aqui ou numa super?? 
	
	public Individual getEventObject() {
		return individual;
	}
	
	//constructor
	public Move(double time, Individual ind) {
		this.setTime(time); //super
		this.individual = ind; //suepr tambem??
	}
	
	//inherited implemetions
	public LinkedList<Event> simulateEvent() {
		
		//TODO
		List<Event> newEventsList = new ArrayList<Event>(1);
		List<Point> pointsList = individual.getPossibleDirections();
		//get random number between 0 and 1
		Point choosenPoint = chooseDirection(pointsList, number between 0 and 1) 
		this.individual.addToPath(newPoint);//adicionar novo point à pessoa	
		//newEventsList.add(new Move(this.get time + gera novo tempo))
	
		return newEventsList;
		
	}
	
	//public methods
	//getters e setters
	
	//private methods
	private void chooseDirection() {
			return List(floor(list_size*number between 0 and 1))
	}

}

//floor(p*n) da me a poisção que preciso de tirar do array.
