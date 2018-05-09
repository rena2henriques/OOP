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
		Individual father = this.getIndividual();
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
		double eventTime = this.getTime() + ((GridCommands) this.getsNC()).getCommand(GridSimulation.DEATH,newDude); 
		Death death = new Death(eventTime, newDude, getsNC());
		newEventsList.add(death);
		//sets new dude's death time
		newDude.myDeath=death;
		//adds next reproduciton and move
		eventTime = this.getTime() + ((GridCommands)this.getsNC()).getCommand(GridSimulation.MOVE,newDude); 
		if(checkDeathTime(eventTime, newDude)) {
			Move move = new Move(eventTime, newDude, getsNC());
			newEventsList.add(move);
			newDude.nextMove=move;
		}
		eventTime = this.getTime() + ((GridCommands)this.getsNC()).getCommand(GridSimulation.REP,newDude); 
		if(checkDeathTime(eventTime, newDude)) {
			Reproduction rep = new Reproduction(eventTime, newDude, getsNC());
			newEventsList.add(rep);
			newDude.nextRep=rep;
		}
		//adds next reproduction for father
		eventTime = this.getTime() + ((GridCommands)this.getsNC()).getCommand(GridSimulation.REP,father); 
		if(checkDeathTime(eventTime, father)) {
			Reproduction faRep = new Reproduction(eventTime, father, getsNC());
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
	
	/*
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

		//mymap.addSpecialZone(2, 2, 3, 3, 4);
		//mymap.addSpecialZone(2, 3, 3, 4, 5);

		List<Point> lista = new ArrayList<Point>();
		lista.add(new Point(1,2));
		lista.add(new Point(2,2));
		lista.add(new Point(3,2));
		lista.add(new Point(3,3));

	
		INumberGenerator<Individual> deaths = new DeathExpRandomTime();
		INumberGenerator<Individual> moves = new MoveExpRandomTime();
		INumberGenerator<Individual> reps = new ReproductionExpRandomTime();
		INumberGenerator<Individual> thrs = new RandomPercentage();
		SimulationNumberCommands sNC = new SimulationNumberCommands(deaths, moves, reps, thrs);
		

		Population pop = new Population(3,10,1,1, mymap);
		Individual dude1 = new Individual(pop,lista);
		
		
		
		pop.individuals.add(dude1);
		Reproduction rep = new Reproduction(sNC.getReproductionTime(dude1), dude1, sNC);	


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
}


//test params below



/**/
