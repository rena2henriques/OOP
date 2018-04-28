/**
 * 
 */
package specific;

import java.util.ArrayList;
import java.util.List;

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
	private int max_cost = 1;
	
	private MapPoint finalpoint;

	List<MapPoint> map;
	
	/**
	 * @return the finalpoint
	 */
	public MapPoint getFinalpoint() {
		return finalpoint;
	}

	/**
	 * @param finalpoint the finalpoint to set
	 */
	public void setFinalpoint(MapPoint finalpoint) {
		this.finalpoint = finalpoint;
	}

	/**
	 * @param origin
	 * @param direction
	 * @return
	 */
	public boolean checkMove(MapPoint origin, Point destiny) {
		
		return false;
	}
	
	/**
	 * @param point
	 * @return
	 */
	public boolean isFinal(Point point) {
		
		if(map.get((point.getY()-1)*width+(point.getX()-1)).getType() == 3) {
			return true;
		} 
		
		return false;
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
		
		map = new ArrayList<MapPoint>(height*width);
		generateGrid();
	}
	
	
	/**
	 * @brief creates the initial rectangular grid uniting adjacent points in the form of an adjacency list
	 */
	public void generateGrid() {
		
		int column = 0, row = 0;
		
		// map has only one point
		if(map.size() == 1) {
			map.add(1, new MapPoint(1, 1));
			return;
		}
		
		// map has more than one point
		for(int i = 0; i < map.size(); i++) {
			
			// converting index to x and y
			column = i%width + 1;
			row = i/width + 1;
			
			// creates the point
			map.add(i, new MapPoint(column, row));
			
			// map has more than 1 point of width
			if(width > 1) {
				// when the point is left border of the map
				
				MapPoint point = map.get(i);
				
				if (column == 1) {
					// inserts the point to the right
					point.connections.add(new Connection(1,new MapPoint(column+1, row)));
				// when the point is in the right border of the map				
				} else if (column == width) {
					// inserts the point to the left
					point.connections.add(new Connection(1,new MapPoint(column-1, row)));
				// when not in the left or right border
				} else {
					// inserts the point left and right
					point.connections.add(new Connection(1,new MapPoint(column-1, row)));
					point.connections.add(new Connection(1,new MapPoint(column+1, row)));
				}
				
				map.set(i, point);
			}
			
			// map has more than 1 point of height
			if(height > 1) {
				
				MapPoint point = map.get(i);
				
				// when the point is in the lower border of the map
				if (row == 1) {
					// inserts the point above
					point.connections.add(new Connection(1,new MapPoint(column, row+1)));
				// when the point is in the upper border of the map				
				} else if (row == height) {
					// inserts the point under
					point.connections.add(new Connection(1,new MapPoint(column, row-1)));
				// when not in upper or lower border
				} else {
					// inserts the point above and under
					point.connections.add(new Connection(1,new MapPoint(column, row-1)));
					point.connections.add(new Connection(1,new MapPoint(column, row+1)));
				}
				
				map.set(i, point);
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
	 * Calculate the total cost of a path
	 * @param path -> an array of points
	 * @return the total cost
	 */
	public int calculateCost(ArrayList<Point> path) {
		
		
		return 0;
	}
	
	
	
	
	
	/**
	 * returns the dist value (smallest nº of hops) between the param point and the final point
	 * @param point we want to calculate the dist from
	 * @return dist
	 */
	public int calculateDist(Point point) {
		// returns the distance (number of hops) between point and the final point
		return Math.abs(point.getY() - finalpoint.getY()) + Math.abs(point.getX() - finalpoint.getY());
	}
	
	
	/**
	 * @brief sets the point as an obstacle (type = 1)
	 * @param x
	 * @param y
	 */
	public void addObstacle(int x, int y) {
		
		// set this point as an obstacle
		// get it from the array
		MapPoint point = map.get((y-1)*width+(x-1));
		
		// change its type to obstacle
		point.setType(MapPoint.OBSTACLE);
		
		// increments the number of obstacles
		n_obst++;
		
		
		/*
		// eliminate connections with the adjacent points
		
		// gets the list of connections
		List<Connection> pointConnections= point.getConnections();
		
		for(int i = 0; i < pointConnections.size(); i++) {
			
			// gets the connection i of the argument point
			Connection connection = pointConnections.get(i);
			
			// gets the index of the connected point
			int index = CoordsToIndex(connection.getPoint().getY(), connection.getPoint().getX(), width);
			
			// get the point which is connected
			MapPoint pointConnected = map.get(index);
			
			// removed the argument point from its list of connected points
			pointConnected.connections.remove(point);
			
			point.connections.remove(pointConnected);
			
		}*/
		
		
		// add the point again to the array
		map.set((y-1)*width+(x-1), point);
	}
	
	/**
	 * @brief sets the point as an initial point (type = 2)
	 * @param x
	 * @param y
	 */
	public void addInitialPoint(int x, int y) {
		// set this point as an initial point
		
		MapPoint point = map.get((y-1)*width+(x-1));
		
		point.setType(MapPoint.INITIALPOINT);
		
		// add the point again to the array
		map.set((y-1)*width+(x-1), point);
	}
	
	/**
	 * @brief sets the point as an final point (type = 3)
	 * @param x
	 * @param y
	 */
	public void addFinalPoint(int x, int y) {
		// set this point as an final point
		
		MapPoint point = map.get((y-1)*width+(x-1));
		
		point.setType(MapPoint.FINALPOINT);
		
		finalpoint = new MapPoint(x, y, MapPoint.FINALPOINT);
		
		// add the point again to the array
		map.set((y-1)*width+(x-1), point);
	}
	
	
	/**
	 * @param xinitial
	 * @param yinitial
	 * @param xfinal
	 * @param yfinal
	 * @param cost
	 */
	public void addSpecialZone(int xinitial, int yinitial, int xfinal, int yfinal, int cost) {
		
		// PENSAR SE RECEBEMOS PONTOS OU COORDENADAS
		
		// update max_cost
		if (cost > max_cost)
			max_cost = cost;
		
		// considering that the initial is the above and left of the final
		
		int pointA = CoordsToIndex(yinitial, xinitial, width);
		int pointB = CoordsToIndex(yfinal, xfinal, width);
		
		for(int i = 0; i < yfinal - yinitial; i++) {
			
			
			
		}
		
		
		
		
	}
	
	
	//TODO
	/**
	 * Returns the cost of any edge between 2 connected points of the map
	 * @param p1 first point (vertice) of the edge
	 * @param p2 first point (vertice) of the edge
	 * @return the cost of the specified edge
	 */
	public int getConnectionCost(Point p1, Point p2) {
		
		// get the correspondent MapPoints of the argument Points
		MapPoint pointA = map.get(CoordsToIndex(p1.getY(), p1.getX(), width));
		MapPoint pointB = map.get(CoordsToIndex(p2.getY(), p2.getX(), width));
		
		// checks every pointA connection
		for(int i = 0; i < pointA.connections.size(); i++) {
			
			// if the point 2 is the one that point 1 is connected in the connection then return the cost 
			if(pointB.equals(pointA.connections.get(i).point)) {
				// returns the cost of the connection
				return pointA.connections.get(i).getCost();
			}
			
		}
		
		return 0;
	}
	
	
	/**
	 * @return the maximum cost of an edge
	 */
	public int getMaxCost() {
		return max_cost;
	}
	
	/**
	 * @return the width (n) of the map
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * @return the height (m) of the map
	 */
	public int getHeight() {
		return height;
	}
}
