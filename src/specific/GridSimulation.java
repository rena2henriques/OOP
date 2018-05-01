package specific;

import java.util.LinkedList;
import java.util.List;
import general.SimulationA;
import general.Event;
import general.PEC;

public class GridSimulation extends SimulationA{
	
	private Population population;
	private Point initialPoint;
	private Individual bestInd;
	private int maxInd, initPop;
	private boolean finalPointHit;

	public GridSimulation(XMLFile file) {
		//read file
		//call SAX Parser
		//INITIALIZAÇÃO DO SIMULATIONA
		//ALOCAR LOGO ESPAÇO NO PEC
		Map map = new Map(grid,initialpoint,finalpoint,obstacles,events); // VERIFICAR NOMES DAS VARIAVEIS
		List<Individual> individuals = new LinkedList<Individual>();	//LINKEDLIST OU ARRAYLIST COM CAPAC
		population = new Population(sensitivity,death, move,reproduction,map);	
		this.finalTime=finaltime;
		pec = new PEC(6*initPop); //6*initPop is the initialcapacity of the priorityqueue;
	}
	
	public void simulate() {
		
		//reseting the dynamic variables and initializing the population of individuals
		reset();
		initialize();	
		
		//local variables
		List<Event> eventList = new LinkedList<Event>(); //list of events returned in the simulateEvent
		Individual currentInd=null; // individual of the current event
		Event currentEvent= null; // current event 
		
	}
	
	void reset() {	
		super.init();		
		finalPointHit=false;
		bestInd=null;
		population.clearIndividuals();
	}
	
	void initialize() {
		
		Individual newInd=null;
		
		//initializing the population 
		for(int i=0; i<initPop;i++) {
			
			newInd=new Individual(population, initialPoint);
			
			//first 3 events for each individual - death, move, reproduction
			Death death= new Death(RANDTIME,newInd);
			newInd.setIndDeath(death);
			pec.addEvent(death);			
			//SÓ MANDAR EVENTOS PARA A PEC SE O SEU TEMPO FOR INFERIOR AO DAMORTE
			pec.addEvent(new Move(RANDTIME,newInd));
			pec.addEvent(new Reproduction(RANDTIME,newInd));
	
			population.individuals.add(newInd);			
			
		}
		
		//add first observation
		pec.addEvent(new Observation(finalTime/20,this));
			
	}
	
	
	public Point getInitialPoint() {
		return initialPoint;
	}
	
	public void setInitialPoint(Point p) {
		initialPoint=p;
	}
	
	public Population getPopulation() {
		return population;
	}
	
	public void setPopulation(Population population) {
		this.population=population;
	}
	
	public int getMaxInd() {
		return maxInd;
	}

	public void setMaxInd(int max_ind) {
		this.maxInd = max_ind;	
	}

	public int getInitPop() {
		return initPop;
	}

	public void setInitPop(int init_pop) {
		this.initPop = init_pop;
	}

	public boolean isFinalPointHit() {
		return finalPointHit;
	}


}
