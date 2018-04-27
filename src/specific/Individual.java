/**
 * 
 */
package specific;

import java.util.ArrayList;

/**
 * @author Group 6
 *
 */

//QUE VISIBILIDADE POR À CLASSE???
//MUDAR VISIBILIDADE DOS METODOS, ESTÁ TUDO A PUBLICO :(
public class Individual {
	
	private int cost;
	private double comfort;
	private static double sensitivity; //COMO ESTÁTICO OU A RECEBER NO CONSTRUTOR???
	private ArrayList<Point> path; 
	private Map map;
	
	public Individual(Map _map, Point initial){
		map=_map;
		path.add(initial);
		calculateComfort();
	}
	
	//when it's born from reproduction
	public Individual(Map _map, ArrayList<Point> points) {
		map=_map;
		path=points;
		cost=map.calculateCost(points);
		calculateComfort();
	}
	
	/**
	 * @return the path
	 */
	public ArrayList<Point> getPath() {
		return path;
	}

	/**
	 * @return the sensitivity
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
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}
	
	/**
	 * @param cost the cost to set
	 */
	
	public void setCost(int cost) { //NAO SEI SE VALE A PENA
		this.cost = cost;
	}
	
	/**
	 * @return the comfort
	 */
	public double getComfort() {
		return comfort;
	}
	
	/**
	 * @param confort the confort to set
	 */
	public void setComfort(double comfort) { //NAO SEI SE VALE A PENA
		this.comfort = comfort;
	}
	
	
	public void addToPath(Connection new_point) { //MUDAR PARA FAZER COM POINT
		
		//MUDAR ISTO
		//CHECKAR SE HÁ CYCLE
		if(checkCycle(new_point.connection)) {
		//SE HOUVER REDEFINIR O PATH
			breakCycle(new_point.connection);
		}
		else {
			//ADICIONAR AO PATH
			path.add(new_point.connection);
			//DAR UPDATE AO COST E AO COMFORT
			cost+=new_point.getCost();
			calculateComfort();			
		}
		
		return;
	}
	
	private boolean checkCycle(Point point) {
		return 	path.contains(point);
	}
	
	private void breakCycle(Point newPoint) {
		
		int lastIndex= path.indexOf(newPoint);
		path.subList(lastIndex+1, path.size()).clear();
		
		cost=map.calculateCost(path);
		calculateComfort();		
		
		//COMO CHECKAR QUE ELE CHEGOU AO FIM?
	}
	
	private double calculateComfort() {
		int length=path.size()-1;
		int dist=map.calculateDist(path.get(path.size()-1));
		comfort=pow(1-(cost-length+2)/(map.getMaxCost()*length+3),sensitivity)*pow(1-(dist)/(map.getWidth()+map.getHeight()+1),sensitivity);
	}
		
}
