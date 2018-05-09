package specific;

import java.util.ArrayList;
import java.util.List;


/**
 * Class that defines a point of the map in this specific case
 * <p> Class that defines a point of the map in this specific case. The MapPoint inherits the class Point in order to have 
 * 2D coordinates but also has a type which can be NORMAL, OBSTACLE, INITIALPOINT or FINALPOINT. These types are defined constants
 * just for the code be more readable. A MapPoint also has a List of Objects of Connection that corresponds to the points it is
 * adjacent to and also the cost associated to that connection.
 *
 */
public class MapPoint extends Point {

	/**
	 *  type of point, according to this specific map
	 */
	protected int type;
	
	/**
	 * Default point
	 * constant associated to the type in order to improve the readability of the code
	 */
	protected static final int NORMAL = 0;
	
	/**
	 * Obstacle point
	 * constant associated to the type in order to improve the readability of the code
	 */
	protected static final int OBSTACLE = 1;
	
	/**
	 * Initial point
	 * constant associated to the type in order to improve the readability of the code
	 */
	protected static final int INITIALPOINT = 2;
	
	/**
	 * Final point
	 * constant associated to the type in order to improve the readability of the code
	 */
	protected static final int FINALPOINT = 3;
	
	/**
	 *  points connected to this point and correspondent cost
	 */
	protected List<Connection> connections;
	
	/**
	 * MapPoint Constructor two arguments
	 * <p> Type is 0 by default - normal position. The constructor of the class Point is called to set the coordinates and 
	 * the object of the class ArrayList is created and initialized with a capacity of 4 because in the worst case a point 
	 * only has 4 connections.
	 * 
	 * @param x
	 * @param y
	 * 
	 */
	public MapPoint(int x, int y) {
		super(x, y);
		connections = new ArrayList<Connection>(4);
	}

	/**
	 * MapPoint Constructor three arguments
	 * <p> In this case the type is received as an argument. The constructor of the class Point is called to set the coordinates 
	 * and the object of the class ArrayList is created and initialized with a capacity of 4 because in the worst case a point 
	 * only has 4 connections.
	 * 
	 * @param x height
	 * @param y width
	 * @param type - type of point in the map
	 */ 
	public MapPoint(int x, int y, int type) {
		super(x, y);
		this.type = type;
		connections = new ArrayList<Connection>(4);
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	/**
	 * This equals redefinion only compares the coordinates of the two objects, not the type or the list of connections
	 * 
	 * @param obj - The object we want to compare to
	 * @return boolean true if equal, false if not
	 *
	 */
	/* (non-Javadoc)
	 * @see specific.Point#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (!(obj instanceof MapPoint))
			return false;
		MapPoint other = (MapPoint) obj;
		if (this.getX() != other.getX())
			return false;
		if (this.getY() != other.getY())
			return false;
		return true;
	}

	/**
	 * Getter of the list of connections
	 * 
	 * @return the connections
	 */
	public List<Connection> getConnections() {
		return connections;
	}
	
	
}
