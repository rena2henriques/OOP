package specific;

import java.util.ArrayList;
import java.util.List;

public class MapPoint extends Point {

	// type of point: 0 - normal, 1 - obstacle, 2 - initialpoint, 3 - finalpoint
	protected int type;
	
	static final int NORMAL = 0;
	static final int OBSTACLE = 1;
	static final int INITIALPOINT = 2;
	static final int FINALPOINT = 3;
	
	// points connected to this point
	List<Connection> connections;
	
	/**
	 * @brief MapPoint Constructor two args
	 * @param x
	 * @param y
	 * type is 0 by default - normal position
	 */
	public MapPoint(int x, int y) {
		super(x, y);
		connections = new ArrayList<Connection>();
	}
	
	/**
	 * @brief MapPoint Constructor three args
	 * @param x height
	 * @param y width
	 * @param type - type of point in the map
	 */ 
	public MapPoint(int x, int y, int type) {
		super(x, y);
		this.type = type;
		connections = new ArrayList<Connection>();
	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + type;
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
		if (type != other.type)
			return false;
		if (this.getX() != other.getX())
			return false;
		if (this.getY() != other.getY())
			return false;
		return true;
	}

	/**
	 * @return the connections
	 */
	public List<Connection> getConnections() {
		return connections;
	}
	
	
}
