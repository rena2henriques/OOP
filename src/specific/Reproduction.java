package specific;

import general.Event;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Reproduction extends IndividualEvent{

	public Reproduction(double time, Individual individual) {
		super(time, individual);
	}

	public List<Event> simulateEvent(){
		Individual father = this.getIndividual();
		LinkedList<Point> childPath = generateChildPath(father);
		//cria filho
		Individual newDude = new Individual(father.getPopulation(), childPath);
		//adiciona novo filho à lista de individuos na simulação
		father.getPopulation().getIndividuals().add(newDude);

		//criação de 3 novos eventos, death, move, reproduction
		List<Event> newEventsList = new LinkedList<Event>();
		double eventTime = this.getTime() + 0.27; //temp
		Death death = new Death(eventTime, newDude);
		newEventsList.add(death);
		//sets new dude's death time
		newDude.setIndDeath(death);
		//adds next reproduciton and move
		eventTime = this.getTime() + 0.25; //temp
		if(checkDeathTime(eventTime, newDude))
			newEventsList.add(new Move(eventTime, newDude));
		eventTime = this.getTime() + 0.22; //temp
		if(checkDeathTime(eventTime, newDude))
			newEventsList.add(new Reproduction(eventTime, newDude));
		
		return newEventsList;
	}
	
	
	/**
	 * Receives the father of the new individual and calculates a fraction of its path, 
	 * based on the father's confort and path size.
	 * 
	 * @param ind
	 * @return prefix of father's path
	 */
	private LinkedList<Point> generateChildPath(Individual ind) {
		LinkedList<Point> myPath = new LinkedList<Point>(ind.getPath());
		double cutPathSize = myPath.size() * 0.90 + ind.getComfort() * (myPath.size() * 0.10);
		for(int i = (ind.getPath().size()-1); i >= (int)Math.ceil(cutPathSize); i--) {
			myPath.remove(i);
		}
		return myPath;
	}
}


//test params below



/*public static void main(String[] args) {

Map mymap = new Map(5,4);

mymap.setN_obst(4);

mymap.addFinalPoint(5, 4);
mymap.addInitialPoint(1, 1);
mymap.addObstacle(2, 1);
mymap.addObstacle(2, 3);
mymap.addObstacle(2, 4);
mymap.addObstacle(4, 2);

//mymap.addSpecialZone(2, 2, 3, 3, 4);
//mymap.addSpecialZone(2, 3, 3, 4, 5);

List<Point> lista = new ArrayList<Point>();

// best path
lista.add(new Point(1,1));
lista.add(new Point(1,2));
lista.add(new Point(2,2));
lista.add(new Point(3,3));
lista.add(new Point(4,4));
lista.add(new Point(4,3));
lista.add(new Point(3,2));
lista.add(new Point(5,4));


Population pop = new Population(3,10,1,1, mymap);
Individual dude1 = new Individual(pop,lista);
pop.individuals.add(dude1);
Reproduction rep = new Reproduction(2.32, dude1);	


System.out.println("Dude dist= " );


System.out.println("pop size= " + pop.getIndividuals().size());
System.out.println("father path = " + dude1.getPath());
System.out.println("father conf = " + dude1.getComfort());
List<Event> newEventsList = rep.simulateEvent();
System.out.println("pop size after rep= " + pop.getIndividuals().size());
System.out.println("child path = " + pop.getIndividuals().get(1).getPath());
System.out.println("child conf = " + pop.getIndividuals().get(1).getComfort());
System.out.println("List events size = " + newEventsList.size());
System.out.println("List deat time = " + newEventsList.get(0).getTime());
System.out.println("List move time = " + newEventsList.get(1).getTime());
System.out.println("List rep time = " + newEventsList.get(2).getTime());
Reproduction rep2 = (Reproduction) newEventsList.get(2);
newEventsList.clear();
newEventsList = rep2.simulateEvent();
System.out.println("pop size after rep= " + pop.getIndividuals().size());
System.out.println("child path = " + pop.getIndividuals().get(2).getPath());

}*/
