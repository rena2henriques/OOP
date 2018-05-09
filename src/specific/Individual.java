
package specific;

import java.util.List;
import java.util.LinkedList;

/**
 * This Class provides an Individual which is associated with a path of points in the map, 
 * and therefore with the correspondent cost and comfort.
 * 
 * @author Group 6
 */

// TODO
//QUE VISIBILIDADE POR � CLASSE???
//MUDAR VISIBILIDADE DOS METODOS, EST� TUDO A PUBLICO :(
public class Individual {
	
	/**
	 * The cost of the path of the individual
	 */
	private int cost;
	
	/**
	 * The comfort of the individual
	 */
	private double comfort;
	
	/**
	 * The list of points that make the path of the Individual
	 */
	private List<Point> path; //array of points
	
	/**
	 * The population (with the simulation info) in which the indiviudal lives
	 */
	private Population population; //population
	
	/**
	 * The death event related to this individual
	 */
	private Death myDeath; //death event related to this individual
	
	/**
	 * The move event related to this individual
	 */
	private Move nextMove;
	
	/**
	 * The reproduction event related to this individual
	 */
	private Reproduction nextRep;
	
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
		
	public Individual getPathIndividual() {
		Individual newInd= new Individual(path);
		newInd.cost=this.cost;
		newInd.comfort=this.comfort;

		return newInd;
	}
	 
	 /**
	 * @return death
	 */
	public Death getIndDeath() {
		 return myDeath;
	 }
	 
	 /**
	 * @param death
	 */
	public void setIndDeath(Death death) {
		 myDeath=death;
	 }
	 
	 /**
	 * @return population
	 */
	public Population getPopulation() {
		 return population;
	 }
	 
	 /**
	 * @param population
	 */
	public void setPopulation(Population population) {
		 this.population=population;
	 }
	
	/**
	 * @return the current path of the individual
	 */
	public List<Point> getPath() {
		return path;
	}
	
	/**
	 * Sets the path of the individual and recalculates its comfort and cost
	 * 
	 * @param path path to set
	 */
	public void setPath(List<Point> path) {
		this.path=path;
		cost=population.map.calculateCost(this.path);
		calculateComfort();
	}
 	
	/**
	 * @return the current cost of the individual
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * @return the current comfort of the individual
	 */
	public double getComfort() {
		return comfort;
	}
	
	/**
	 * @return the next move event of the individual
	 */
	public Move getNextMove() {
		return nextMove;
	}


	/**
	 * @param nextMove sets the next move event of the individual
	 */
	public void setNextMove(Move nextMove) {
		this.nextMove = nextMove;
	}

	
	/**
	 * @return the next reproduction event of the individual
	 */
	public Reproduction getNextRep() {
		return nextRep;
	}


	/**
	 * @param nextRep sets the next reproduction event of the individual
	 */
	public void setNextRep(Reproduction nextRep) {
		this.nextRep = nextRep;
	}
	
	/**
	 * Add a new point to the path and updates the cost and comfort accordingly.	
	 * 
	 * This method first checks if the new position introduces a cycle in the path. 
	 * If there is a cycle, the corresponding points are deleted from the path.
	 * 
	 * @param new_point new position of the individual
	 */
	public void addToPath(Point new_point) { 
		
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
	private void addNewPoint(Point new_point) {	
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
	private void calculateComfort() {
		int length=path.size()-1;
		int dist=population.map.calculateDist(path.get(path.size()-1));
		comfort=Math.pow(1-(cost-length+2)*1.0/((population.map.getMaxCost()-1)*length+3),population.sensitivity)*Math.pow(1-(dist*1.0)/(population.map.getWidth()+population.map.getHeight()+1),population.sensitivity);
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
	
	public static void main(String[] args) throws CloneNotSupportedException {

		/*Map mymap = new Map(5,4,4);		
		mymap.addFinalPoint(5, 4);
		mymap.addInitialPoint(1, 1);
		mymap.addObstacle(2, 1);
		//mymap.addObstacle(2, 3);
		mymap.addObstacle(2, 4);
		mymap.addObstacle(4, 2);
		
		mymap.addSpecialZone(2, 2, 3, 3, 4);*/
		/*Point initial = new Point(1,1);
		Individual i1= new Individual(mymap, initial,1.0);
		i1.addToPath(new Point(1,2));
		i1.addToPath(new Point(2,2));
		i1.addToPath(new Point(2,3));
		System.out.println(i1);
		System.out.println("cost:"+i1.getCost());
		System.out.println("comfort:"+i1.getComfort());*/
		/*List<Point> p= new LinkedList<Point>();
		p.add(new Point(3,2));
		p.add(new Point(3,3));
		p.add(new Point(4,3));
		p.add(new Point(4,4));
		p.add(new Point(3,4));
		Population pop= new Population(1,1,1,1,mymap);
		Individual i1= new Individual(pop,p);
		System.out.println(i1);
		System.out.println("cost:"+i1.getCost());
		System.out.println("comfort:"+i1.getComfort());
		Individual i4= (Individual) i1.clone();

		//testar check cycle
		i1.addToPath(new Point(3,3));
		System.out.println(i1);
		System.out.println("cost:"+i1.getCost());
		System.out.println("comfort:"+i1.getComfort());
		
		//testar clone
		Individual i2= (Individual) i1.clone();
		System.out.println("i2:"+i2);
		System.out.println("cost:"+i2.getCost());
		System.out.println("comfort:"+i2.getComfort());
		System.out.println("identity:"+(i2.path==i1.path));
		
		//testar sort
		Individual i3= new Individual(pop,p);
		List<Individual> inds= new LinkedList<Individual>();
		inds.add(i4);
		inds.add(i3);
		inds.sort(new IndividualComfortComparator());
		for(int i=0; i<inds.size();i++)
			System.out.println("comfort:"+inds.get(i).getComfort());*/
	}

}
