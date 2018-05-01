package specific;

import java.util.LinkedList;
import java.util.List;

public class Population {
	int sensitivity; //fica package? ou damos sets?
	int deathParam;
	int moveParam;
	int reproductionParam;
	Map map;
	List<Individual> individuals;
	
	public Population(int sensitivity, int deathParam, int moveParam, int reproductionParam, Map map,
			List<Individual> individuals) {
		super();
		this.sensitivity = sensitivity;
		this.deathParam = deathParam;
		this.moveParam = moveParam;
		this.reproductionParam = reproductionParam;
		this.map = map;
		this.individuals = individuals;
	}
	
	@Override
	 public Object clone() throws CloneNotSupportedException {
		 
		 Population cloned = (Population)super.clone();
		 cloned.setIndividuals(new LinkedList<Individual>(cloned.getIndividuals()));
		 return cloned;	
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
	
	
}