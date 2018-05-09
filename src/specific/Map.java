package specific;

import java.util.ArrayList;
import java.util.List;

/**
 * This class creates a map, checks validity of a path and has the info about the topology of this specific map.
 * <p> Class that deals with the creation of a grid, setting obstacles, special zones and, initial and final points. 
 * It's also where you can check the validity of paths and points.
 *
 */
public class Map {

	/***
	 *  dimension n of the map, the number of rows.
	 */
	protected int height;
	
	/***
	 *  dimension m of the map, the number of columns.
	 */
	protected int width;
	/** 
	 * nº of obstacles in the map.
	 */
	protected int n_obst;
	/**
	 *  maximum cost of an edge of the map
	 */
	protected int max_cost = 1;
	/**
	 *  final point of the map
	 */
	protected MapPoint finalpoint;
	/**
	 *  list of Points to use to define the grid
	 */
	protected List<MapPoint> map;

	/**
	 * Constructor of the class Map
	 * <p> Receives the width and height, and creates an ArrayList of MapPoints with initial capacity of height*width 
	 * because that's the area of the rectangle. This List is used to define the grid of the map as an adjacency list.
	 * The data structure ArrayList is used because there isn't going to be changes to the capacity, as the number of elements
	 * in the list is the number of points, no more no less. In the constructor is also called the function to generate the grid
	 * where each point is connected to others as necessary. 
	 *  
	 * @param height of the map
	 * @param width of the map
	 */
	public Map(int width, int height) {
		this.height = height;
		this.width = width;
		
		map = new ArrayList<MapPoint>(height*width);
		
		generateGrid();
	}
	

	/**
	 * Returns the possible adjacent points of @param to move ordered clockwise and starting north 
	 * <p> Receives a point whose possible moves are needed. If the point is not valid or it's outside of the limits of the
	 * map null is returned and a error message is printed. The function tests every point that is possibly adjacent to p1 and 
	 * calls the function checkMove to check if moving to that position is a possible move. Every direction that the checkMove
	 * returns true (possible move) the point of that direction is inserted in a List. This List is an ArrayList initialized with
	 * a capacity of 4 because a point of the map has in the worst case 4 adjacent points. The insertion has an order. The 
	 * returned list has the possible points to move according to a clockwise direction, starting from the point above itself.
	 * 
	 * @param point that we want to the possible moves
	 * @return list of possible positions to move ordered clockwise starting with north or null if p1 is not valid or out of boundaries
	 * 
	 */
	public List<Point> getPossibleMoves(Point p1){
		
		List<Point> list = new ArrayList<Point>(4);
		
		if(p1 == null) {
			System.err.println("Error: Point received at getPossibleMoves is null!");
			return null;
		}
		
		if(p1.getY() > height || p1.getY() <= 0 || p1.getX() > width || p1.getX() <= 0) {
			System.err.println("Error: Point " + p1 + " received at getPossibleMoves doesn't belong to the Map!");
			return null;
		}
		
		// point above p1
		if(p1.getY()+1 <= height) {
			if(checkMove(p1, new Point(p1.getX(),p1.getY()+1)) == true) {
				list.add(new Point(p1.getX(),p1.getY()+1));
			}
		}

		// point right to p1
		if(p1.getX()+1 <= width) {
			if(checkMove(p1, new Point(p1.getX()+1,p1.getY())) == true) {
				list.add(new Point(p1.getX()+1,p1.getY()));
			}
		}
		
		// point under p1
		if(p1.getY()-1 > 0) {
			if(checkMove(p1, new Point(p1.getX(),p1.getY()-1)) == true) {
				list.add(new Point(p1.getX(),p1.getY()-1));
			}
		}
		
		// point left to p1
		if(p1.getX()-1 > 0) {
			if(checkMove(p1, new Point(p1.getX()-1,p1.getY())) == true) {
				list.add(new Point(p1.getX()-1,p1.getY()));
			}
		}
		
		return list;
	}
	

	/**
	 * Checks if the individual going from point origin to point destiny is a permitted move 
	 * <p> A move is permitted if the point it wants to move to is not an obstacle, if is out of the boundaries of the map or
	 * if p1 is not adjacent to p2.
	 * 
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
			if(origin.connections.get(i).point.equals(destiny)) {
				int type = map.get(CoordsToIndex(origin.connections.get(i).point.getX(),origin.connections.get(i).point.getY(), width)).type;
				
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
	 * Converts a point of class Point to a point of class MapPoint.
	 * <p> Uses the method CoordsToIndex to convert the coordinates of the point to the index of the correspondent point in
	 * the arrayList of the adjacencyList. After getting that point it is returned. In case of the point being out of boundaries 
	 * of the map, when getting the MapPoint of that index an Exception will be thrown which prints an error message and null is
	 * returned. 
	 * 
	 * @param point - point of class Point
	 * @return point - point of class MapPoint, returns null if point not available in the map
	 */
	private MapPoint PointToMapPoint(Point point) {
		
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
	 * Checks if the received point corresponds to the finalpoint of the map.
	 * 
	 * @param point - point of the class Point
	 * @return true if the type of the correspondent MapPoint or false if it doesn't or if the map
	 * doesn't include the received point
	 */
	public boolean isFinal(Point point) {
		
		try {
			if(map.get((point.getY()-1)*width+(point.getX()-1)).type == 3) {
				return true;
			} 
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in isFinal:" + e.getMessage());
			return false;
		}

		return false;
	}
	

	
	/**
	 * Creates the initial rectangular grid uniting adjacent points in the form of an adjacency list.
	 * <p> It also includes the case of the grid being only one column or only one row. Looping through which point, 
	 * it creates the object MapPoint and inserts the points it is connected to in its list of connections. The cost of every
	 * connection is 1 per default.
	 * 
	 */
	protected void generateGrid() {
		
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
	 * Converts coordinates (starting in (1,1)) to a index of a point in an array.
	 * 
	 * @param row - or y of the grid
	 * @param column - or x of the grid
	 * @param width - or n of the map
	 * @return the respective conversion to an array index
	 */
	protected static int CoordsToIndex(int column, int row, int width) {
		return (row-1)*width+(column-1);
	}
	
	/**
	 * Calculate the total cost of a path
	 * <p> The calculation of the cost of an entire path consists in checking the connection cost between each two consecutive
	 * points of the path. To check the connection cost of two points the method getConnectionCost is called. This method returns
	 * 0 if the path is null or its size if 0 or 1. 
	 * 
	 * @param path - a List of points
	 * @return the total cost or 0 if the the argument is null or has size 0 or 1 
	 */
	public int calculateCost(List<Point> path) {
		
		int cost = 0;
		
		// path is empty
		if (path == null || path.size() == 0 || path.size() == 1) {
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
	 * <p> The smallest nº of hops to get from point to the final point consists in the sum of the absolute value
	 * of the difference between each coordinate x and y
	 * 
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
	 * Sets the point as an obstacle (type = 1).
	 * <p> Sets the point as an obstacle (type = 1). It prints an error message if the received coordinates don't address 
	 * a valid point of the map and the obstacle is not inserted.
	 * 
	 * @param x - coordinate x
	 * @param y - coordinate y
	 */
	public void addObstacle(int x, int y) {
		
		try {
			// set this point as an obstacle
			map.get((y-1)*width+(x-1)).type=MapPoint.OBSTACLE;
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addObstacle, wrong coordinates:" + e.getMessage());
			return;
		}
		
		// increments the number of obstacles
		n_obst++;
	}
	
	/**
	 * sets the point as an initial point (type = 2)
	 * <p> Sets the point as an initial point (type = 2). It prints an error message if the received coordinates don't address 
	 * a valid point of the map and the initial point is not inserted.
	 * 
	 * @param x - coordinate x
	 * @param y - coordinate y
	 */
	public void addInitialPoint(int x, int y) {
		try {
			// set this point as an initial point
			map.get((y-1)*width+(x-1)).type=MapPoint.INITIALPOINT;
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addInitialPoint:" + e.getMessage());
			return;
		}
	}

	/**
	 * sets the point as an final point (type = 3)
	 * <p> Sets the point as an final point (type = 3). It prints an error message if the received coordinates don't address 
	 * a valid point of the map and the final point is not inserted.
	 * 
	 * @param x - coordinate x
	 * @param y - coordinate y
	 */
	public void addFinalPoint(int x, int y) {
		try {
			// set this point as an final point
			map.get((y-1)*width+(x-1)).type=MapPoint.FINALPOINT;
		} catch(IndexOutOfBoundsException e) {
			System.err.println("Error in addFinalPoint:" + e.getMessage());
			return;
		}
		
		finalpoint = new MapPoint(x, y, MapPoint.FINALPOINT);
	}
	
	/**
	 * Receives the coordinates of two points and adds an rectangular shape special cost zone
	 * <p> If the initial coordinates doesn't correspond to a point under and left to the final one then a conversion is done in
	 * order to them to be. If the cost received in the parameter is higher than the max cost of the map then a new max cost is
	 * found and the variable updated. In the specific case of the two points given correspond to a line (horizontal or vertical) 
	 * then the edges between these two points are updated. In the general case of the special zone being a rectangle then the 
	 * cost of the row of final and initial is updated at the same time, and the same for the columns after that. In case of the
	 * coordinates received not being valid, then an error message is printed and the special cost zone is not inserted.
	 * 
	 * 
	 * @param xinitial - coordinate with positive value
	 * @param yinitial - coordinate with positive value
	 * @param xfinal - coordinate with positive value
	 * @param yfinal - coordinate with positive value
	 * @param cost - cost of the special cost zone
	 */
	public void addSpecialZone(int xinitial, int yinitial, int xfinal, int yfinal, int cost) {
		
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
		
		// testing if the given points correspond to a horizontal (lineh) or vertical (linev)
		boolean lineh = false;
		boolean linev = false;

		// Testing situations that the special cost zone isn't a rectangle 
		if (xinitial == xfinal) {
			
			// case 1: the special cost zone is a vertical line or just a point (invalid)
			if (yinitial != yfinal) {
				
				linev = true;
				
			// case 2: the special cost zone is just a point (invalid)
			} else {
				System.err.println("Special Cost Zone is just a point, situation invalid. Not Inserted!");
				return;
			}
			
		// testing if the special cost zone is a horizontal line
		} else if (yinitial == yfinal) {
			
			// there's no need to test if the zone is a point because it was tested in the previous if statement
			
			lineh = true;
		}
		
		// getting the MapPoint corresponding to the coordinates recv from the arguments
		MapPoint pinitial;
		MapPoint pfinal;
		
		try {
			pinitial = map.get(CoordsToIndex(xinitial, yinitial, width));
			pfinal = map.get(CoordsToIndex(xfinal,yfinal, width));
		} catch(IndexOutOfBoundsException e) {
			// coordinates not correct
			System.err.println("Error in addSpecialZone, Coordinates not valid:" + e.getMessage());
			System.err.println("Zone not inserted");
			return;
		}
		
		
		// adds a vertical line as a special zone
		if (linev) {
			
			MapPoint pauxinit = pinitial;
			// no need to test for exceptions because the if statements in the beginning of the function prevent this excp
			MapPoint pauxfinal = map.get(CoordsToIndex(pauxinit.getX(), pauxinit.getY()+1, width));
			
			while(pauxinit.getY() != yfinal) {
				
				if(getConnectionCost(pauxinit, pauxfinal) < cost) {
					// connects two points from the yinitial row
					setConnectionCost(pauxinit, pauxfinal, cost);
				}
				
				pauxinit = pauxfinal;
				
				try {
					pauxfinal = map.get(CoordsToIndex(pauxfinal.getX(), pauxfinal.getY()+1, width));
				} catch(IndexOutOfBoundsException e) {
					return;
				}
			}
			
			// update max_cost
			if (cost > max_cost)
				max_cost = cost;
			
			return;
			
		// adds a horizontal line as a special zone
		} else if (lineh) {
			
			MapPoint pauxinit = pinitial;
			// no need to test for exceptions because the if statements in the beginning of the function prevent this excp 
			MapPoint pauxfinal = map.get(CoordsToIndex(pauxinit.getX()+1, pauxinit.getY(), width));
			
			while(pauxinit.getX() != xfinal) {
				
				if(getConnectionCost(pauxinit, pauxfinal) < cost) {
					// connects two points from the yinitial row
					setConnectionCost(pauxinit, pauxfinal, cost);
				}
				
				pauxinit = pauxfinal;
				
				try {
					pauxfinal = map.get(CoordsToIndex(pauxfinal.getX()+1, pauxfinal.getY(), width));
				} catch(IndexOutOfBoundsException e) {
					return;
				}
			}
			
			// update max_cost
			if (cost > max_cost)
				max_cost = cost;
			
			return;
		}
		
		// Cases that the special cost zone is indeed a rectangle 
		
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
				
				// in the case Xs exceeds the boundaries of the special zone
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
			pauxinit2 = map.get(CoordsToIndex(pinitial.getX(), pinitial.getY()+1, width));
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
			pauxinit2 = map.get(CoordsToIndex(pauxinit2.getX(), pauxinit2.getY()+1, width));
			pauxfinal2 = map.get(CoordsToIndex(pauxfinal2.getX(), pauxfinal2.getY()-1, width));
		}
		
		// update max_cost
		if (cost > max_cost)
			max_cost = cost;
		
	}
	
	/**
	 * Associates a cost to a connection between two points.
	 * <p> Associates a cost to a connection between two points. This association is done bidirectionaly, in other words,
	 * the it changes the cost in the connection of p1 to p2 and p2 to p1. 
	 * 
	 * @param p1 - point1
	 * @param p2 - point2 
	 * @param cost - integer value to be inserted
	 */
	protected void setConnectionCost(MapPoint p1, MapPoint p2, int cost) {
		
		// checks every point1 connection
		for(int i = 0; i < p1.connections.size(); i++) {
			
			// if the point 2 is the one that point 1 is connected in the connection 
			if(p2.equals(p1.connections.get(i).point)) {
				// sets the cost of the connection
				p1.connections.get(i).cost=cost;
			}
		}
		
		// checks every point2 connection
		for(int i = 0; i < p2.connections.size(); i++) {
			
			// if the point 1 is the one that point 2 is connected in the connection 
			if(p1.equals(p2.connections.get(i).point)) {
				// sets the cost of the connection
				p2.connections.get(i).cost=cost;
			}
		}
		
		return;
	}

	
	/**
	 * Returns the cost of any edge between 2 connected points of the map.
	 * 
	 * @param p1 first point (vertice) of the edge
	 * @param p2 first point (vertice) of the edge
	 * @return the cost of the specified edge or 0 if one of the points is not available in the map, and -1 if there isn't
	 * a direct connection between the two points (they aren't adjacent)
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
				return pointA.connections.get(i).cost;
			}
		}
		
		// no connection between the points
		return -1;
	}
	
}
