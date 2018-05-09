package specific;

import java.util.ArrayList;
import java.util.List;

class MapPoint extends Point {

	// type of point: 0 - normal, 1 - obstacle, 2 - initialpoint, 3 - finalpoint
	int type;
	
	static final int NORMAL = 0;
	static final int OBSTACLE = 1;
	static final int INITIALPOINT = 2;
	static final int FINALPOINT = 3;
	
	// points connected to this point
	List<Connection> connections;
	
	/**
	 * MapPoint Constructor two args
	 * @param x
	 * @param y
	 * type is 0 by default - normal position
	 */
	MapPoint(int x, int y) {
		super(x, y);
		connections = new ArrayList<Connection>();
	}
	
	/**
	 * MapPoint Constructor three args
	 * @param x height
	 * @param y width
	 * @param type - type of point in the map
	 */ 
	MapPoint(int x, int y, int type) {
		super(x, y);
		this.type = type;
		connections = new ArrayList<Connection>();
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		int result = super.hashCode();
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
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
	 * @return the connections
	 */
	 List<Connection> getConnections() {
		return connections;
	}
	
	
}
