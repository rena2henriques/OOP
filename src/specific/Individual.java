/**
 * 
 */
package specific;

/**
 * @author Group 6
 *
 */
class Individual {
	
	private int cost;
	private double comfort;
	private static double sensitivity;
	private Point[] path; // MUDAR PARA LISTAAAA
	
	/**
	 * @return the path
	 */
	public Point[] getPath() {
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
	
	public void setCost(int cost) {
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
	public void setComfort(double comfort) {
		this.comfort = comfort;
	}
	
	// TODO
	public void addToPath(Point new_point) {
		
		return;
	}
	
	// TODO
	public void updatePath(Point[] path) {
		
		return;
	}
	
	/*
	 * 
	 * @param TODO
	 *  
	 */
	public double calculateComfort(int cost, int length, double k, int cmax, int dist, int n, int m) {
		
		// escrever expressão
		// testar excepções
		
		return comfort;
	}
	
	// TODO
	public void updateCost(int new_cost) {
		
		return;
	}
	
	// TODO
	Individual(){
		
	}
	
	
}
