package specific;

import java.util.ArrayList;
import java.util.List;

import general.SimulationA;

public class GridSimulation extends SimulationA{
	
	private Population population;
	private Point initialPoint;
	private Individual bestInd;
	private int maxInd, initPop;
	private boolean finalPointHit;

	public GridSimulation(XMLFile file) {
		//read file
		//call SAX Parser
		//INITIALIZA��O DO SIMULATIONA
		//ALOCAR LOGO ESPA�O NO PEC
		Map map = new Map(grid,initialpoint,finalpoint,obstacles,events); // VERIFICAR NOMES DAS VARIAVEIS
		List<Individual> individuals = new ArrayList<Individual>(initPop);	//MUDARLIST
		population = new Population(sensitivity,death, move,reproduction,map,individuals);	
	}
	
	public void simulate() {
		
		//reseting the dynamic variables and initializing the population of individuals
		reset();
		initialize();	
		
	}
	
	void reset() {	
		super.init();		
		finalPointHit=false;
		bestInd=null;
		population.clearIndividuals();
	}
	
	void initialize() {
		
		//initializing the population 
		for(int i=0; i<initPop;i++) {
			population.individuals.add(new Individual(map, initialPoint));
			//first 3 events for each individual - death, move, reproduction
			pec.addEvent(new Death(RANDTIME,individuals.get(individuals.size()-1),individuals));
			pec.addEvent(new Move(RANDTIME,individuals.get(individuals.size()-1)));
			pec.addEvent(new Reproduction(RANDTIME,individuals.get(individuals.size()-1),individuals));
		}
		
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
