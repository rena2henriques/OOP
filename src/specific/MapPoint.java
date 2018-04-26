package specific;

import java.util.ArrayList;
import java.util.List;

public class MapPoint extends Point {

	// type of point: 0 - normal, 1 - obstacle, 2 - initialpoint, 3 - finalpoint
	protected int type;
	
	List<MapPoint> connections;
	
	/**
	 * @param x
	 * @param y
	 * type is 0 by default - normal position
	 */
	public MapPoint(int x, int y) {
		super(x, y);
		connections = new ArrayList<MapPoint>();
	}
	
	/**
	 * @param x height
	 * @param y width
	 * @param type - type of point in the map
	 */ 
	public MapPoint(int x, int y, int type) {
		super(x, y);
		this.type = type;
		connections = new ArrayList<MapPoint>();
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
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}
	
	
}