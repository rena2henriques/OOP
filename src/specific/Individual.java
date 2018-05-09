
package specific;

import java.util.List;
import java.util.LinkedList;

/**
 * This Class provides an Individual which is associated with a path of points in the map, 
 * and therefore with the correspondent cost and comfort.
 * 
 * @author Group 6
 */

public class Individual {
	
	/**
	 * The cost of the path of the individual
	 */
	protected int cost;
	
	/**
	 * The comfort of the individual
	 */
	protected double comfort;
	
	/**
	 * The list of points that make the path of the Individual
	 */
	protected List<Point> path; //array of points
	
	/**
	 * The population (with the simulation info) in which the indiviudal lives
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
	 * Creates an Individual with the specified Population and initial point.
	 * 
	 * @param population population with the parameters of the simulation
	 * @param initial initial point, where the path starts
	 */
	public Individual(Population population, Point initial){
		this.population=population;
		path = new LinkedList<Point>();
		path.add(initial);
		calculateComfort();		
	}


	/**
	 * Constructor. To use when the individual already has a path.
	 * Creates an Individual with the specified Population and list of points.
	 * 
	 * @param population population with the parameters of the simulation
	 * @param points initial path of the new individual
	 */
	public Individual(Population population, List<Point> points) {
		this.population=population;
		path=points;
		cost=population.map.calculateCost(points);
		calculateComfort();
	}
	
	public Individual(List<Point> points) {
		path= new LinkedList<Point>(points);
	}
		
	protected Individual getPathIndividual() {
		Individual newInd= new Individual(path);
		newInd.cost=this.cost;
		newInd.comfort=this.comfort;

		return newInd;
	}
	
	/**
	 * Add a new point to the path and updates the cost and comfort accordingly.	
	 * 
	 * This method first checks if the new position introduces a cycle in the path. 
	 * If there is a cycle, the corresponding points are deleted from the path.
	 * 
	 * @param new_point new position of the individual
	 */
	protected void addToPath(Point new_point) { 
		
		//checkar se h� cicle
		if(checkCycle(new_point)) {
		//se houve redefinir o path
			breakCycle(new_point);
		}
		else {
			addNewPoint(new_point);
		}
		
		calculateComfort();			

		
		return;
	}

	/**
	 * Adds new point to path and throws exception when there is a cycle in the path
	 * 
	 * @param new_point point to add to path
	 * @throws Exception if there is a cycle in the path with the new point
	 */
	protected void addNewPoint(Point new_point) {	
		cost+=population.map.getConnectionCost(path.get(path.size()-1),new_point);
		path.add(new_point);
	}
	
	
	/**
	 * Checks if a position already exists in the path (if there is a cycle).
	 * 
	 * @param point point we wish to test.
	 * @return true if there will be a cycle in the path with the new point. False if not.
	 */
	private boolean checkCycle(Point point) {
		return 	path.contains(point);
	}
	
	
	/**
	 * Breaks a cycle in the path by eliminating the corresponding points until the repeated point, 
	 * and updates cost and comfort accordingly.
	 * 
	 * @param newPoint point repeated in the path
	 * 
	 */
	private void breakCycle(Point newPoint) {
		
		int lastIndex= path.indexOf(newPoint);
		path.subList(lastIndex+1, path.size()).clear();
		
		cost=population.map.calculateCost(path);
		//calculateComfort();				
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
