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
		//INITIALIZAÇÃO DO SIMULATIONA
		//ALOCAR LOGO ESPAÇO NO PEC
		Map map = new Map(grid,initialpoint,finalpoint,obstacles,events); // VERIFICAR NOMES DAS VARIAVEIS
		List<Individual> individuals = new ArrayList<Individual>(initPop);	
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
