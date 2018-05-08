package specific;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import general.Event;
import general.Point;
import general.SimulationCommands;

public class Death extends IndividualEvent{

	public Death(double time, Individual ind, SimulationCommands sNC) {
		super(time, ind, sNC);
	}
	
	public List<Event> simulateEvent() {
		List<Event> newEventsList = new LinkedList<Event>();
		//removes the individual from the individuals list
		Individual ind = this.getIndividual();
		//se o individuo não exisitr, acho que o prog não crasha
		ind.getPopulation().getIndividuals().remove(ind); 
		return newEventsList; //deve ser melhor do que return null;
	}
	
	
	//main
	
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
		lista.add(new Point(4,3));
		lista.add(new Point(4,4));
		lista.add(new Point(5,4));

		
		// test of getPossibleMoves function
		List<Point> possmoves = mymap.getPossibleMoves(new Point(3,2));
		
		System.out.println("The possible moves are: " + possmoves);
		
		Population pop = new Population(3,10,1,1, mymap);
		Individual dude1 = new Individual(pop,new Point(1,1));
		Individual dude2 = new Individual(pop,new Point(2,1));
		pop.individuals.add(dude1);
		//Death det = new Death(2.32, dude1);
		pop.individuals.add(dude2);
		
		System.out.println("Tamanho da lista = " + pop.individuals.size());
		System.out.println("Dude1 conforto = " + pop.individuals.get(0).getComfort());
		System.out.println("Dude2 confoto = " + pop.individuals.get(1).getComfort());
		//List<Event> newEventsList = det.simulateEvent();
		System.out.println("Tamanho da lista after death = " + pop.individuals.size());
		System.out.println("Reamining dude = " + pop.individuals.get(0).getComfort());
		

	}
	
}

//testing code bellow

/*
*/