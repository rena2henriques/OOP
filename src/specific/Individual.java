/**
 * 
 */
package specific;

import java.util.ArrayList;

/**
 * @author Group 6
 *
 */

//QUE VISIBILIDADE POR ¿ CLASSE???
//MUDAR VISIBILIDADE DOS METODOS, EST¡ TUDO A PUBLICO :(
public class Individual {
	
	private int cost;
	private double comfort;
	private static double sensitivity; //COMO EST¡TICO OU A RECEBER NO CONSTRUTOR???
	private ArrayList<Point> path; 
	private Map map;
	
	public Individual(Map _map, Point initial){
		map=_map;
		path.add(initial);
		calculateComfort();
		//comfort=calculateComfort(cost,0,sensitivity,map.getMaxCost(), map.calculateDist(initial), map.getWidth(), map.getHeight()); //ADICIONAR ESTAS FUNCOES DE GET AO MAP
	}
	
	//when it's born from reproduction
	public Individual(Map _map, ArrayList<Point> points) {
		map=_map;
		path=points;
		cost=map.calculateCost(points);
		calculateComfort();
		//ATENCAO: LENGTH … O NR DE EDGES E POR ISSO O NR DE PONTOS -1
		//comfort=calculateComfort(cost,path.size()-1,sensitivity,map.getMaxCost(), map.calculateDist(path.get(path.size()-1)), map.getWidth(), map.getHeight()); 
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
	
	
	public void addToPath(Connection new_point) { //VER SE FAZEMOS COM CONNECTION OU COM POINT
		
		//ADICIONAR AO PATH
		path.add(new_point.connection);
		
		//CHECKAR SE H¡ CYCLE
		if(checkCycle()) {
		//SE HOUVER REDEFINIR O PATH
			breakCycle();
		}
		else {
			//DAR UPDATE AO COST E AO COMFORT
			cost+=new_point.getCost();
			calculateComfort();			
		}
		
		return;
	}
	
	// TODO
	public void updatePath(Point[] path) {
		
		return;
	}
	
	
	/*public double calculateComfort(int cost, int length, double k, int cmax, int dist, int n, int m) {
		
		// escrever express√£o
		// testar excep√ß√µes
		
		return comfort;
	}*/
	
	private double calculateComfort() {
		comfort=pow(,sensitivity)
	}
	
	// TODO
	public void updateCost(int new_cost) {
		
		return;
	}
		
}
