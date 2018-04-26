/**
 * 
 */
package specific;

/**
 * @author renato
 *
 */
public class Map {

	// dimensions of the map
	private int height, width;
	// nº of obstacles in the map
	private int n_obst;
	// maximum cost of an edge of the map
	private int max_cost;
	
	MapPoint[] map;
	
	/**
	 * @param origin
	 * @param direction
	 * @return
	 */
	public boolean checkMove(MapPoint origin, int direction) {
		
	}
	
	/**
	 * @param point
	 * @return
	 */
	public boolean isFinal(Point point) {
		
	}
	
	/**
	 * @param max_cost
	 */
	public void updateMaxCost(int max_cost) {
		
	}
	
	/**
	 * @param current_point
	 * @param direction
	 * @return
	 */
	public Point newPosition(Point current_point, int direction) {
		
		
	}

	/**
	 * @param height
	 * @param width
	 * @param n_obst
	 * @param max_cost
	 */
	public Map(int height, int width, int n_obst, int max_cost) {
		this.height = height;
		this.width = width;
		this.n_obst = n_obst;
		this.max_cost = max_cost;
		
		map = new MapPoint[height*width];
	}
	
	
	/**
	 * @param path
	 * @return
	 */
	public double calculateCost(Point[] path) {
		
	}
	
	
	/**
	 * @param point
	 * @return
	 */
	public int calculateDist(Point point) {
		
		
	}
	
	
	
	
	
	
	
}
