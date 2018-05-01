package specific;

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
}