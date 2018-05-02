package specific;

import general.Event;

import general.INumberGenerator;

import general.Point;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Move extends IndividualEvent {
	
	//constructor
	public Move(double time, Individual ind, SimulationNumberCommands sNC) {
		super(time, ind, sNC);
	}
	
	//inherited implemetions
	public List<Event> simulateEvent() {
		
		Individual ind = this.getIndividual();
		List<Event> newEventsList = new LinkedList<Event>();
		//gets individual current position, which is the last one of the path
		Point currPos = ind.getPath().get((ind.getPath().size()) - 1);
		//gets possible individual moves
		List<Point> pointsList = ind.getPopulation().getMap().getPossibleMoves(currPos);
		//chooses next point to move, based on available moves and the generated number
		if(!pointsList.isEmpty()) {
			//gets a number between 0 and 1
			double direction = this.getsNC().getThreshold(ind);
			//exceçao par prevnir quem implete o getThreshold não retorne entre 0 e 1
			try {
				if(direction > 1 || direction < 0) throw new wrongThreshold();
			} catch (wrongThreshold e) {
				direction = 0; //sets to default
			}
			Point choosenPoint = chooseDirection(pointsList, direction);
			//adds choosen point to the path
			ind.addToPath(choosenPoint);//adicionar novo point à pessoa
			checkBestFitIndividual(ind, ind.getPopulation());
			//creates next move
			double eventTime = this.getTime() + this.getsNC().getMoveTime(ind); //temp
			if(checkDeathTime(eventTime, ind)) //para testar move, comentar esta condição
				newEventsList.add(new Move(eventTime, ind, this.getsNC()));
		} //else there are no available moves
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
	
	private void checkBestFitIndividual(Individual currentInd, Population pop) {
			
		//if none of the individuals has reached the final point before and the current individual
		//reaches it, we change the flag and set the current individual as the best individual
		if(!pop.finalPointHit && pop.map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1))) {
			pop.finalPointHit=true; 
			updateBestFit(currentInd, pop);			
		}	
		//we only check if it is the best individual if the final point hasn't been reached 
		//or if it has, we only check if our individual is also in the final point
		else if(!pop.finalPointHit || (pop.finalPointHit &&  pop.map.isFinal(currentInd.getPath().get(currentInd.getPath().size()-1)))) {
			if(checkIfIsBestFit(currentInd, pop))
				updateBestFit(currentInd, pop);			
		}
	}
	
	private boolean checkIfIsBestFit(Individual currentInd, Population pop) {
			
		if(pop.bestInd==null)
			return true;
			
		//if none of the individuals has reached the final point we check the comfort
		else if(!pop.finalPointHit && currentInd.getComfort()>pop.bestInd.getComfort()) {
			return true;
		} 
		//if an individual has already reached the final point, we check the cost
		else if(pop.finalPointHit && currentInd.getCost() < pop.bestInd.getCost()) {
			return true;
		}
			
		return false;
	}
		
	private void updateBestFit(Individual currentInd, Population pop) {
		try {
			pop.bestInd=(Individual) currentInd.clone(); 	
		} catch(CloneNotSupportedException e) {
			e.printStackTrace();
		}
	}
	

	//main teste
	
public static void main(String[] args) {
		
		Map mymap = new Map(5,4);
		
		mymap.setN_obst(4);
		
		mymap.addFinalPoint(5, 4);
		mymap.addInitialPoint(1, 1);
		mymap.addObstacle(2, 1);
		mymap.addObstacle(2, 3);
		mymap.addObstacle(2, 4);
		mymap.addObstacle(4, 2);
		
		mymap.addSpecialZone(2, 2, 3, 3, 4);
		mymap.addSpecialZone(2, 3, 3, 4, 5);
		
		List<Point> lista = new ArrayList<Point>();
		
		// best path
		lista.add(new Point(1,1));
		lista.add(new Point(1,2));
		lista.add(new Point(2,2));
		lista.add(new Point(3,2));
		lista.add(new Point(3,3));
		lista.add(new Point(4,4));
		lista.add(new Point(5,4));
		lista.add(new Point(4,3));

		
		// test of getPossibleMoves function
		List<Point> possmoves = mymap.getPossibleMoves(new Point(5,4));
		
		System.out.println("The possible moves are: " + possmoves);
		
		Population pop = new Population(3,10,1,1, mymap);
		Individual dude1 = new Individual(pop,lista);
		Individual dude2 = new Individual(pop,new Point(2,1));
		
		INumberGenerator<Individual> deaths = new DeathExpRandomTime();
		INumberGenerator<Individual> moves = new MoveExpRandomTime();
		INumberGenerator<Individual> reps = new ReproductionExpRandomTime();
		INumberGenerator<Individual> thrs = new RandomPercentage();
		SimulationNumberCommands sNC = new SimulationNumberCommands(deaths, moves, reps, thrs);
		
		pop.individuals.add(dude1);
		Move move = new Move(sNC.getMoveTime(dude1), dude1, sNC);
		pop.individuals.add(dude2);
		
		System.out.println("moves are: " + dude1.getPath());
		System.out.println("old best dude: " + pop.getBestInd());
		List<Event> newEventsList = move.simulateEvent();
		System.out.println("moves are: " + dude1.getPath());
		System.out.println("ev size: " + newEventsList.size());
		System.out.println("new ev time: " + newEventsList.get(0).getTime());
		
		System.out.println("new best dude: " + pop.getBestInd());

		
		Move move2 = (Move) newEventsList.get(0);
		newEventsList.clear();
		newEventsList = move2.simulateEvent();
		
		System.out.println("new best dude: " + dude1.getPath());
		

	}
}

//test code below :) 


/**/
