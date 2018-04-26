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
	private int height, width; // n and m, respectively
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
		
		return false;
	}
	
	/**
	 * @param point
	 * @return
	 */
	public boolean isFinal(Point point) {
		
		return false;
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
	public MapPoint newPosition(MapPoint current_point, int direction) {
		
		return new MapPoint(0,0);
	}

	/**
	 * @param height
	 * @param width
	 * @param n_obst
	 * @param max_cost
	 */
	public Map(int height, int width, int n_obst) {
		this.height = height;
		this.width = width;
		this.n_obst = n_obst;
		
		map = new MapPoint[height*width];
	}
	
	
	/**
	 * @brief creates the initial grid in the form of an adjacency list
	 */
	public void generateGrid() {
		
		int column = 0, row = 0;
		
		// map has only one point
		if(map.length == 1) {
			map[1] = new MapPoint(1, 1);
			return;
		}
		
		// map has more than one point
		for(int i = 0; i < map.length; i++) {
			
			// converting index to x and y
			column = i%width + 1;
			row = i%width + 1;
			
			// creates the point
			map[i] = new MapPoint(column, row);
			
			// map has more than 1 point of width
			if(width > 1) {
				// when the point is left border of the map
				if (column == 1) {
					// inserts the point to the right
					map[i].connections.add(new MapPoint(column+1, row));
				// when the point is in the right border of the map				
				} else if (column == width) {
					// inserts the point to the left
					map[i].connections.add(new MapPoint(column-1, row));
				// when not in the left or right border
				} else {
					// inserts the point left and right
					map[i].connections.add(new MapPoint(column-1, row));
					map[i].connections.add(new MapPoint(column+1, row));
				}
			}
			
			// map has more than 1 point of height
			if(height > 1) {
				// when the point is in the lower border of the map
				if (row == 1) {
					// inserts the point above
					map[i].connections.add(new MapPoint(column, row+1));
				// when the point is in the upper border of the map				
				} else if (row == height) {
					// inserts the point under
					map[i].connections.add(new MapPoint(column, row-1));
				// when not in upper or lower border
				} else {
					// inserts the point above and under
					map[i].connections.add(new MapPoint(column, row-1));
					map[i].connections.add(new MapPoint(column, row+1));
				}
			}
			
		}
		
	}
	
	/**
	 * @param row or y of the grid
	 * @param column or x of the grid
	 * @param width or n of the map
	 * @return the respective conversion to an array index
	 */
	public static int CoordsToIndex(int row, int column, int width) {
		return (row-1)*width+(column-1);
	}
	
	
	/**
	 * @param path
	 * @return
	 */
	public double calculateCost(Point[] path) {
		
		return 0.0;
	}
	
	
	/**
	 * @param point
	 * @return
	 */
	public int calculateDist(Point point) {
		
		
		return 0;
	}
	
	
	
	
	
	
	
}
