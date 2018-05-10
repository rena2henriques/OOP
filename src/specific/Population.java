package specific;

import java.util.LinkedList;
import java.util.List;

/**
 * This class has the parameters that rule the individuals' movements and the creation 
 * of new events. Also contains the sensitivity of the individuals' comfort.
 *   
 * <p>Contains the list of alive individuals participating on the simulation and an 
 * association to the best fit individual. Also a flag that is activated upon 
 * reaching the final point.
 */
public class Population{
	/**
	 * Sensitivity to calculate the individuals' comfort
	 */
	protected int sensitivity;
	/**
	 * Parameter used in the time calculation of death events
	 */
	protected int deathParam;
	/**
	 * Parameter used in the time calculation of move events 
	 */
	protected int moveParam;
	/**
	 * Parameter used in the time calculation of reproduction events 
	 */
	protected int reproductionParam;
	/**
	 * Flag activated when an individual reaches the map's final point
	 */
	protected boolean finalPointHit;
	/**
	 * Map of the simulation
	 */
	protected Map map;
	/**
	 * List of the alive individuals present in the simulation
	 */
	protected List<Individual> individuals;
	/**
	 * Current best fit of the simulation
	 */
	protected Individual bestInd;
	
	

	/**
	 * Constructor to be used when there is an individuals list already created.
	 * 
	 * @param sensitivity - To comfort calculation
	 * @param deathParam - To calculate event death 
	 * @param moveParam - To calculate event move time
	 * @param reproductionParam - To calculate event reproduction
	 * @param map - Simulation's map
	 * @param individuals - Already created individuals list
	 */
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
	
	/**
	 * Constructor to be used when there isn't any individual in the simulation yet 
	 * 
	 * @param sensitivity - To comfort calculation
	 * @param deathParam - To calculate event death 
	 * @param moveParam - To calculate event move time
	 * @param reproductionParam - To calculate event reproduction
	 * @param map - Simulation's map
	 */
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

	/**
	 * Gets population's associated map
	 * 
	 * @return map - Simulation's Map
	 */
	public Map getMap() {
		return map;
	}

	/**
	 * Sets population associated map
	 * 
	 * @param map - Simulation's Map
	 */
	public void setMap(Map map) {
		this.map = map;
	}
	/**
	 * Gets the population's associated list of simulation individuals
	 * 
	 * @return individuals - List of alive individuals
	 */
	public List<Individual> getIndividuals() {
		return individuals;
	}

	/**
	 * Sets population's associated simulation individuals
	 * 
	 * @param individuals - List of alive individuals
	 */
	public void setIndividuals(List<Individual> individuals) {
		this.individuals = individuals;
	}
	
	/**
	 * Clears all population's associated individuals 
	 */
	public void clearIndividuals() {
		individuals.clear();
	}
	
	/**
	 * Gets current simulation's best fit individual
	 * 
	 * @return bestInd - Current simulation's best fit individual
	 */
	public Individual getBestInd() {
		return bestInd;
	}

	/**
	 * Set's the population's best fit individual
	 * 
	 * @param bestInd - New best fit individual
	 */
	public void setBestInd(Individual bestInd) {
		this.bestInd = bestInd;
	}
	
}