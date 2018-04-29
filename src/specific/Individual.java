/**
 * DESCREVER A CLASSE INDIVIDUALS MAIS PORMENORIZADAMENTE....
 */
package specific;

import java.util.ArrayList;

/**
 * @author Group 6
 * POR OS NOSSOS NUMEROS, AQUI E EM TODAS AS CLASSES :)
 *DIZER QUE AS EXCEPCOES QUE SAO THROW E MERDAS ASSIM NOS COMMENTS, VER AS TAGS
 */

//QUE VISIBILIDADE POR � CLASSE???
//MUDAR VISIBILIDADE DOS METODOS, EST� TUDO A PUBLICO :(
public class Individual implements Cloneable {
	
	private int cost;
	private double comfort;
	private static double sensitivity; //comfort sensitivity to small variations//COMO EST�TICO OU A RECEBER NO CONSTRUTOR???
	private ArrayList<Point> path; //array of points
	private Map map; //map in which the individual lives
	
	/**
	 * Constructor
	 * 
	 * @param map map in which the individual lives
	 * @param initial initial point, where the path starts
	 */
	public Individual(Map map, Point initial){
		this.map=map;
		//DAR NEW AO PATH, VER SE DEVIA SER LIST OU ARRAYLIST
		path.add(initial);
		calculateComfort();
	}
	

	/**
	 * Constructor. To use when the individual already has a path 
	 * 
	 * @param map map in which the individual lives
	 * @param points initial path of the new individual
	 */
	public Individual(Map map, ArrayList<Point> points) {
		this.map=map;
		path=points;
		cost=map.calculateCost(points);
		calculateComfort();
	}
	
	//VER SE CLONE � PUBLIC OU PROTECTED
	
	 @Override
	 public Object clone() throws CloneNotSupportedException {
		 Individual cloned = (Individual)super.clone();
		 cloned.setPath(new ArrayList<Point>(cloned.getPath()));
		 //cloned.setPath((ArrayList<Point>)cloned.getPath().clone());
		 return cloned;
	    }
	
	/**
	 * @return the current path of the individual
	 */
	public ArrayList<Point> getPath() {
		return path;
	}
	
	/**
	 * @param p path to set
	 */
	public void setPath(ArrayList<Point> p) {
		path=p;
	}

	/**
	 * @return the sensitivity to small variations
	 */
	public static double getSensitivity() {
		return sensitivity;
	}

	/**
	 * @param sensitivity the sensitivity to set
	 */
	public static void setSensitivity(double sensitivity) {
		Individual.sensitivity = sensitivity;
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
	 * Add a new point to the path and updates the cost and comfort accordingly.	
	 * 
	 * This method first checks if the new position introduces a cycle in the path. 
	 * If there is a cycle, the corresponding points are deleted from the path.
	 * 
	 * @param new_point new position of the individual
	 */
	/*public void addToPath(Point new_point) { 
		
		//checkar se h� cicle
		if(checkCycle(new_point)) {
		//se houve redefinir o path
			breakCycle(new_point);
		}
		else {
			//dar update ao cost 
			cost+=map.getConnectionCost(path.get(path.size()-1),new_point);
			//adicionar novo ponto ao path e dar update ao comfort
			path.add(new_point);
			calculateComfort();			
		}
		
		return;
	}
	
	*/
	//VER SE � MELHOR ASSIM OU COMO ESTAVA ANTES <-- NAO SEI SE VALE A PENA TER UMA EXCEPTION NESTE CASO
	//NAO TENHO QUE CRIAR UMA CLASSE QUE DESCENDA DE EXCEPTION
	public void addToPath(Point newPoint) {
		
		try {
			addNewPoint(newPoint);
		} catch (CycleException e){
			 breakCycle(newPoint);		
		}
		finally {
			calculateComfort();
		}
	}
	
	
	
	/**
	 * Adds new point to path and throws exception when there is a cycle in the path
	 * 
	 * @param new_point point to add to path
	 * @throws Exception if there is a cycle in the path with the new point
	 */
	private void addNewPoint(Point new_point) throws CycleException{
		
		if(checkCycle(new_point)) throw new CycleException();
		
		cost+=map.getConnectionCost(path.get(path.size()-1),new_point);
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
		
		cost=map.calculateCost(path);
		//calculateComfort();				
	}
	
	
	/**
	 * Calculates the comfort of the individual in its current position
	 */
	private void calculateComfort() {
		int length=path.size()-1;
		int dist=map.calculateDist(path.get(path.size()-1));
		comfort=Math.pow(1-(cost-length+2)/(map.getMaxCost()*length+3),sensitivity)*Math.pow(1-(dist)/(map.getWidth()+map.getHeight()+1),sensitivity);
	}
		
	/**
	 * Override of the lang.Object method toString.
	 * Returns a string with the path of the individual.
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		
		String s="{";
		for(Point pt: path)
			s+=pt.toString()+",";
		
		return s.substring(0,s.length()-1)+"}";
	}

}
