package specific;

import java.util.LinkedList;
import java.util.List;

public class Population{
	int sensitivity;
	int deathParam;
	int moveParam;
	int reproductionParam;
	boolean finalPointHit;
	Map map;
	List<Individual> individuals;
	Individual bestInd;
	
	

	public Population(int sensitivity, int deathParam, int moveParam, int reproductionParam, Map map,
			List<Individual> individuals) {
		super();
		this.sensitivity = sensitivity;
		this.deathParam = deathParam;
		this.moveParam = moveParam;
		this.reproductionParam = reproductionParam;
		this.map = map;
		this.individuals = individuals;
		this.finalPointHit = false;
		this.bestInd = null;
	}
	
	public Population(int sensitivity, int deathParam, int moveParam, int reproductionParam, Map map) {
		super();
		this.sensitivity = sensitivity;
		this.deathParam = deathParam;
		this.moveParam = moveParam;
		this.reproductionParam = reproductionParam;
		this.map = map;
		individuals= new LinkedList<Individual>();
		this.finalPointHit = false;
		this.bestInd = null;
	}

	public Map getMap() {
		return map;
	}

	public void setMap(Map map) {
		this.map = map;
	}

	public List<Individual> getIndividuals() {
		return individuals;
	}

	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	
	public void clearIndividuals() {
		individuals.clear();
	}
	
	public Individual getBestInd() {
		return bestInd;
	}

	public void setBestInd(Individual bestInd) {
		this.bestInd = bestInd;
	}
	
}