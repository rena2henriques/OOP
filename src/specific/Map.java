/**
 * 
 */
package specific;

import java.util.ArrayList;
import java.util.List;

import general.Point;

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
	 * Constructor
	 * @param height
	 * @param width
	 * @param max_cost
	 */
	public Map(int width, int height) {
		this.height = height;
		this.width = width;
		
		map = new ArrayList<MapPoint>(height*width);
		
		generateGrid();
	}
	
	/**
	 * Get the final point
	 * @return the finalpoint
	 */
	public MapPoint getFinalpoint() {
		return finalpoint;
	}

	/**
	 * Returns the possible adjacent points of @param to move ordered clockwise and starting north 
	 * 
	 * @param point that we want to the possible moves
	 * @return list of possible positions to move ordered clockwise starting with north
	 * 
	 */
	public List<Point> getPossibleMoves(Point p1){
		
		List<Point> list = new ArrayList<Point>(4);
		
		// point above p1
		if(checkMove(p1, new Point(p1.getX(),p1.getY()+1)) == true) {
			list.add(new Point(p1.getX(),p1.getY()+1));
		}
		
		// point right to p1
		if(checkMove(p1, new Point(p1.getX()+1,p1.getY())) == true) {
			list.add(new Point(p1.getX()+1,p1.getY()));
		}
		
		// point under p1
		if(checkMove(p1, new Point(p1.getX(),p1.getY()-1)) == true) {
			list.add(new Point(p1.getX(),p1.getY()-1));
		}
		
		// point left to p1
		if(checkMove(p1, new Point(p1.getX()-1,p1.getY())) == true) {
			list.add(new Point(p1.getX()-1,p1.getY()));
		}
		
		return list;
	}
	
	
	/**
	 * Checks if the individual going from point origin to point destiny is a permited move 
	 * @param origin is the point we are
	 * @param destiny is the point we want to go to
	 * @return true if the move is possible, false if the destiny is an obstacle 
	 * or if there is no connection between origin and destiny
	 */
	public boolean checkMove(Point p1, Point p2) {
		
		MapPoint origin = PointToMapPoint(p1);
		MapPoint destiny = PointToMapPoint(p2);
		
		// if any of the points are null then there's no connection for sure
		if(origin == null || destiny == null) {
			return false;
		}
		
		for(int i = 0; i < origin.connections.size(); i++) {
			// if the point the origin is connected to is the destiny
			if(origin.connections.get(i).getPoint().equals(destiny)) {
				int type = map.get(CoordsToIndex(origin.connections.get(i).getPoint().getX(),origin.connections.get(i).getPoint().getY(), width)).getType();
				
				// checks if it's an obstacle
				if(type == MapPoint.OBSTACLE) {
					// move not possible
					return false;
				} else {
					// move possible
					return true;
				}
			}
			
		}
		
		// there is no connection between origin and destiny
		return false;
	}
	
	/**
	 * Converts a point of class Point to a point of class MapPoint
	 * @param point of class Point
	 * @return point of class MapPoint, returns null if point not available in the map
	 */
	public MapPoint PointToMapPoint(Point point) {
		
		MapPoint mappoint;
		
		try {
			mappoint = map.get(CoordsToIndex(point.getX(), point.getY(), width));
			
		} catch (IndexOutOfBoundsException e) {
			System.err.println("Error in PointToMapPoint:" + e.getMessage());
			return null;
		}
		
		return mappoint;
	}
	
	/**
	 * Checks if the received point corresponds to the finalpoint of the map
	 * @param point
	 * @return true if the type of the correspondent MapPoint or false if it doesn't or if the map
	 * doesn't include that point
	 */
	public boolean isFinal(Point point) {
		
		try {
			if(map.get((point.getY()-1)*width+(point.getX()-1)).getType() == 3) {
				return true;
			} 
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in isFinal:" + e.getMessage());
			return false;
		}

		
		return false;
	}
	

	
	/**
	 * creates the initial rectangular grid uniting adjacent points in the form of an adjacency list
	 */
	private void generateGrid() {
		
		int column = 0, row = 0;
		
		// map has only one point
		if(width*height == 1) {
			map.add(1, new MapPoint(1, 1));
			return;
		}
		
		// map has more than one point
		for(int i = 0; i < width*height; i++) {
			
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
	 * Converts coordinates (starting in (1,1)) to a index of a point in an array
	 * @param row or y of the grid
	 * @param column or x of the grid
	 * @param width or n of the map
	 * @return the respective conversion to an array index
	 */
	public static int CoordsToIndex(int column, int row, int width) {
		return (row-1)*width+(column-1);
	}
	
	/**
	 * Calculate the total cost of a path
	 * @param path -> an array of points
	 * @return the total cost
	 */
	public int calculateCost(List<Point> path) {
		
		int cost = 0;
		
		// path is empty
		if (path == null || path.size() == 0) {
			return 0;
		}
		
		// total cost is the sum of all the connections within the point path 
		for (int i = 0; i < path.size() - 1; i++) {
			cost += getConnectionCost(path.get(i), path.get(i+1));
		}
		
		return cost;
	}
	
	/**
	 * returns the dist value (smallest nº of hops) between the param point and the final point
	 * @param point we want to calculate the dist from
	 * @return dist or 0 if the point received is null or the finalpoint isn't defined 
	 */
	public int calculateDist(Point point) {
		
		// preventing accessing in the case of these points being null 
		if(point == null || finalpoint == null) {
			return 0;
		}
		
		// returns the distance (number of hops) between point and the final point
		return Math.abs(point.getY() - finalpoint.getY()) + Math.abs(point.getX() - finalpoint.getX());
	}
	
	/**
	 * sets the point as an obstacle (type = 1)
	 * @param x
	 * @param y
	 */
	public void addObstacle(int x, int y) {
		
		try {
			// set this point as an obstacle
			map.get((y-1)*width+(x-1)).setType(MapPoint.OBSTACLE);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addObstacle:" + e.getMessage());
			return;
		}
		
		// increments the number of obstacles
		n_obst++;
	}
	
	/**
	 * sets the point as an initial point (type = 2)
	 * @param x
	 * @param y
	 */
	public void addInitialPoint(int x, int y) {
		try {
			// set this point as an initial point
			map.get((y-1)*width+(x-1)).setType(MapPoint.INITIALPOINT);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addInitialPoint:" + e.getMessage());
			return;
		}
	}
	
	/**
	 * @return the n_obst
	 */
	public int getN_obst() {
		return n_obst;
	}

	/**
	 * @param n_obst the n_obst to set
	 */
	public void setN_obst(int n_obst) {
		this.n_obst = n_obst;
	}

	/**
	 * sets the point as an final point (type = 3)
	 * @param x
	 * @param y
	 */
	public void addFinalPoint(int x, int y) {
		try {
			// set this point as an final point
			map.get((y-1)*width+(x-1)).setType(MapPoint.FINALPOINT);
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addFinalPoint:" + e.getMessage());
			return;
		}
		
		finalpoint = new MapPoint(x, y, MapPoint.FINALPOINT);
	}
	
	/**
	 * Receives the coordinates of two points and adds an rectangular shape special cost zone of the 
	 * @param xinitial
	 * @param yinitial
	 * @param xfinal
	 * @param yfinal
	 * @param cost
	 */
	public void addSpecialZone(int xinitial, int yinitial, int xfinal, int yfinal, int cost) {
		
		// update max_cost
		if (cost > max_cost)
			max_cost = cost;
		
		// Converting received points in the case of pinitial not being from the lower left and
		// the final not the upper right of the rectangle
		if(yinitial > yfinal) {
			int temp = yinitial;
			yinitial = yfinal;
			yfinal = temp;
			
			if(xinitial > xfinal) {
				temp = xinitial;
				xinitial = xfinal;
				xfinal = temp;
			}		
		} else {
			if( xinitial > xfinal) {
				int temp = xinitial;
				xinitial = xfinal;
				xfinal = temp;
			}
		}

		MapPoint pinitial;
		MapPoint pfinal;
		
		try {
			pinitial = map.get(CoordsToIndex(xinitial, yinitial, width));
			pfinal = map.get(CoordsToIndex(xfinal,yfinal, width));
		} catch(IndexOutOfBoundsException e) {
			// coordinates not correct
			System.err.println("Points not correct-> Error in addSpecialZone:" + e.getMessage());
			return;
		}
		
		// starts with the initial point
		MapPoint pauxinit1 = pinitial;
		// starts with the final point
		MapPoint pauxfinal1 = pfinal;
		
		MapPoint pauxinit2, pauxfinal2;
		
		try {
			// get the point right to the initial point
			pauxinit2 = map.get(CoordsToIndex(pinitial.getX()+1, pinitial.getY(), width));
			// get the point left to the final point
			pauxfinal2 = map.get(CoordsToIndex(pfinal.getX()-1, pfinal.getY(), width));
		} catch(IndexOutOfBoundsException e) {
			return;
		}
		
		
		// connecting points from the rows
		for(int i = 0; i < yfinal - yinitial; i++) {
		
			if(getConnectionCost(pauxinit1, pauxinit2) < cost) {
				// connects two points from the yinitial row
				setConnectionCost(pauxinit1, pauxinit2, cost);
			}
			
			if(getConnectionCost(pauxfinal1, pauxfinal2) < cost) {
				// connects two points from the yfinal row
				setConnectionCost(pauxfinal1, pauxfinal2, cost);
			}
			
			// in the case Xs exceeds the boundaries of the specialzone
			if (pauxinit2.getX()+1 > xfinal || pauxfinal2.getX()-1 < xinitial) {
				break;
			}
			
			// moves to the next two points
			pauxinit1 = pauxinit2;
			pauxfinal1 = pauxfinal2;
			
			// no need to test exceptions because of the previous if statement
			pauxinit2 = map.get(CoordsToIndex(pauxinit2.getX()+1,pauxinit2.getY(),  width));
			pauxfinal2 = map.get(CoordsToIndex(pauxfinal2.getX()-1, pauxfinal2.getY(), width));
			
		}
		
		// starts with the initial point
		pauxinit1 = pinitial;
		// starts the final point
		pauxfinal1 = pfinal;
		try {
			// get the point above to the initial point
			pauxinit2 = map.get(CoordsToIndex(pinitial.getX(), pinitial.getY()-1, width));
			// get the point under the final point
			pauxfinal2 = map.get(CoordsToIndex(pfinal.getX(), pfinal.getY()-1, width));
		} catch(IndexOutOfBoundsException e) {
			return;
		}
		
		// connecting points from the rows
		for(int i = 0; i < xfinal - xinitial; i++) {
			
			if(getConnectionCost(pauxinit1, pauxinit2) < cost) {
				// connects two points from the xinitial column
				setConnectionCost(pauxinit1, pauxinit2, cost);
			}
			
			if(getConnectionCost(pauxfinal1, pauxfinal2) < cost) {
				// connects two points from the xfinal column
				setConnectionCost(pauxfinal1, pauxfinal2, cost);
			}
			
			// in the case Y exceeds the boundaries of the specialzone
			if (pauxinit2.getY()+1 > yfinal || pauxfinal2.getY()-1 < yinitial) {
				break;
			}
			
			// moves to the next two points
			pauxinit1 = pauxinit2;
			pauxfinal1 = pauxfinal2;
			
			// no need to test exceptions because of the previous if statement
			pauxinit2 = map.get(CoordsToIndex(pinitial.getX(), pinitial.getY()+1, width));
			pauxfinal2 = map.get(CoordsToIndex(pfinal.getX(), pfinal.getY()-1, width));
		}
		
	}
	
	/**
	 * inserts the cost in the connections
	 * @param p1 point1
	 * @param p2 point2 
	 * @param cost value to be inserted
	 */
	public void setConnectionCost(MapPoint p1, MapPoint p2, int cost) {
		
		// checks every point1 connection
		for(int i = 0; i < p1.connections.size(); i++) {
			
			// if the point 2 is the one that point 1 is connected in the connection 
			if(p2.equals(p1.connections.get(i).point)) {
				// sets the cost of the connection
				p1.connections.get(i).setCost(cost);
			}
		}
		
		// checks every point2 connection
		for(int i = 0; i < p2.connections.size(); i++) {
			
			// if the point 1 is the one that point 2 is connected in the connection 
			if(p1.equals(p2.connections.get(i).point)) {
				// sets the cost of the connection
				p2.connections.get(i).setCost(cost);
			}
		}
		
		return;
	}

	
	/**
	 * Returns the cost of any edge between 2 connected points of the map
	 * @param p1 first point (vertice) of the edge
	 * @param p2 first point (vertice) of the edge
	 * @return the cost of the specified edge
	 */
	public int getConnectionCost(Point p1, Point p2) {
		
		MapPoint pointA, pointB;
		
		try {
			// get the correspondent MapPoints of the argument Points
			pointA = map.get(CoordsToIndex(p1.getX(), p1.getY(), width));
			pointB = map.get(CoordsToIndex(p2.getX(), p2.getY(), width));
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Points not available in map: " + e.getMessage());
			return 0;
		}
			
		// checks every pointA connection
		for(int i = 0; i < pointA.connections.size(); i++) {
			// if the point 2 is the one that point 1 is connected in the connection then return the cost 
			if(pointB.equals(pointA.connections.get(i).point)) {
				// returns the cost of the connection
				return pointA.connections.get(i).getCost();
			}
		}
		
		// no connection between the points
		return -1;
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		Map mymap = new Map(5,4);
		
		System.out.println("Area of the map is: " + mymap.map.size());
		
		mymap.setN_obst(4);
		
		mymap.addFinalPoint(5, 4);
		mymap.addInitialPoint(1, 1);
		mymap.addObstacle(2, 1);
		mymap.addObstacle(2, 3);
		mymap.addObstacle(2, 4);
		mymap.addObstacle(4, 2);
		
		mymap.addSpecialZone(2, 2, 3, 3, 4);
		mymap.addSpecialZone(2, 3, 3, 4, 5);
		
		System.out.println("Cost of connection is: " + mymap.getConnectionCost(new Point(2,3), new Point(3,3)));
		System.out.println("Cost of connection is: " + mymap.getConnectionCost(new Point(2,2), new Point(3,2)));
		
		System.out.println("Max cost of the map is: " + mymap.getMaxCost());
		 
		System.out.println("Point " + mymap.getFinalpoint() + " is the final point? " + mymap.isFinal(new Point(5,4)));
		
		// test of CalculateDist
		System.out.println("Dist from init to final is: " + mymap.calculateDist(new Point(1,1)));

		List<Point> lista = new ArrayList<Point>();
		
		// best path
		lista.add(new Point(1,1));
		lista.add(new Point(1,2));
		lista.add(new Point(2,2));
		lista.add(new Point(3,2));
		lista.add(new Point(3,3));
		lista.add(new Point(4,3));
		lista.add(new Point(4,4));
		lista.add(new Point(5,4));

		
		System.out.println("Cost of path is: " + mymap.calculateCost(lista));
		
		// test of getPossibleMoves function
		List<Point> possmoves = mymap.getPossibleMoves(new Point(3,2));
		
		System.out.println("The possible moves are: " + possmoves);
		
	}
	
}
