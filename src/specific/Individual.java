
package specific;

import java.util.List;
import java.util.LinkedList;

/**
 * The Individual is associated with a path of points in a map, 
 * and therefore with a correspondent cost and comfort. 
 * It's also associated to a Population of individuals and to it's lifetime events (Death,Move and Reproduction).
 * <p>This class provides methods to add points to the path of the Individual, recalculating it's comfort and cost.
 * Note that the methods are implemented in such a way that when there is a point added to the path that is already on it,
 * the corresponding cycle is eliminated.
 * 
 * @see Map
 * @see Population
 * @see Death
 * @see Move
 * @see Reproduction
 */
public class Individual {
	
	/**
	 * The cost of the path of the individual
	 */
	protected int cost;
	
	/**
	 * The comfort of the path the individual
	 */
	protected double comfort;
	
	/**
	 * The list of points that make the path of the Individual
	 */
	protected List<Point> path; //array of points
	
	/**
	 * The population (with the individual list and other simulation information) in which the individual lives
	 */
	protected Population population; //population
	
	/**
	 * The death event related to this individual
	 */
	protected Death myDeath; //death event related to this individual
	
	/**
	 * The move event related to this individual
	 */
	protected Move nextMove;
	
	/**
	 * The reproduction event related to this individual
	 */
	protected Reproduction nextRep;
	
	/**
	 * Constructor.
	 * <p>
	 * Creates an Individual with the specified Population and initial point, and calculates it's comfort and cost.
	 * 
	 * @param population - population with the parameters of the simulation
	 * @param initial - initial point, point where the path starts
	 */
	public Individual(Population population, Point initial){
		this.population=population;
		path = new LinkedList<Point>();
		path.add(initial);
		calculateComfort();		
	}


	/**
	 * Constructor. To use when the individual already has a path.
	 * <p>
	 * Creates an Individual with the specified Population and list of points, and calculates it's comfort and cost.
	 * 
	 * @param population - population with the parameters of the simulation 
	 * @param points - path of the this individual
	 */
	public Individual(Population population, List<Point> points) {
		this.population=population;
		path=points;
		cost=population.map.calculateCost(points);
		calculateComfort();
	}
	
	
	/**
	 * Constructor. To use when the Individual is only associated to a path.
	 * <p>
	 * Creates an Individual with the specified path.
	 * 
	 * @param points - path of this individual
	 */
	public Individual(List<Point> points) {
		path= new LinkedList<Point>(points);
	}
		
	/**
	 * Creates and returns a copy of this Individual, based only on it's path, comfort and cost.
	 * <p>
	 * The path is a deep copy of the path, 
	 * in order to assure that isn�t altered by any changes that might occur in the path of this Individual.
	 * The rest of the attributes of the individual are set to null.
	 * 
	 * @return an Individual with a copy of the comfort, cost and a deep copy of the path of the current Individual.
	 */
	protected Individual getPathIndividual() {
		Individual newInd= new Individual(path);
		newInd.cost=this.cost;
		newInd.comfort=this.comfort;

		return newInd;
	}
	
	/**
	 * Add a new point to the path and updates the cost and comfort accordingly.	
	 * 
	 * This method first checks if the new position introduces a cycle in the path (with the checkCycle method). 
	 * If there is a cycle, the corresponding points are deleted from the path (method breakCycle). 
	 * If a cycle isn't detected, it adds the new point (method addNewPoint).
	 * 
	 * @param new_point - new position of the map to add to the path
	 */
	protected void addToPath(Point new_point) { 
		
		//checkar se há cicle
		if(checkCycle(new_point)) {
		//se houve redefinir o path
			breakCycle(new_point);
		}
		else {
			addNewPoint(new_point);
		}
		
		//calculo comfort de qualquer forma
		calculateComfort();			

		
		return;
	}

	
	/**
	 * Adds a new point to path if there is no cycle with its introduction, and recalculates the new cost.
	 * <p>
	 * This method should be used only after checking if there is a cycle. 
	 * 
	 * @param new_point - point to add to path
	 */
	protected void addNewPoint(Point new_point) {	
		cost+=population.map.getConnectionCost(path.get(path.size()-1),new_point);
		path.add(new_point);
	}
	
	
	/**
	 * Checks if a position already exists in the path (if there is a cycle).
	 * 
	 * @param point - point we wish to test.
	 * @return true if there will be a cycle in the path with the new point. False if not.
	 */
	private boolean checkCycle(Point point) {
		return 	path.contains(point);
	}
	
	
	/**
	 * Breaks a cycle in the path by eliminating the corresponding points until the repeated point, 
	 * and updates cost accordingly.
	 * <p>
	 * This method should be used only if a cycle was detected.
	 * 
	 * @param newPoint - repeated point in the path
	 * 
	 */
	private void breakCycle(Point newPoint) {
		
		//indice do ponto repetido 
		int lastIndex= path.indexOf(newPoint);
		//limpar a lista desde o ponto repetido até ao fim
		path.subList(lastIndex+1, path.size()).clear();
		
		cost=population.map.calculateCost(path);
	}
	
	
	/**
	 * Calculates the comfort of the individual in its current position
	 */
	protected void calculateComfort() {
		int length=path.size()-1;
		int dist=population.map.calculateDist(path.get(path.size()-1));
		comfort=Math.pow(1-(cost-length+2)*1.0/((population.map.max_cost-1)*length+3),population.sensitivity)*Math.pow(1-(dist*1.0)/(population.map.width+population.map.height+1),population.sensitivity);
	}
		
	/**
	 * Returns a string with the path of the individual.
	 * 
	 * @return String with the path of the individual
	 */
	public String pathString() {
		
		String s="{";
		for(Point pt: path)
			s+=pt.toString()+",";
		
		return s.substring(0,s.length()-1)+"}";
	}

}
