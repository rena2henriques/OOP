/**
 * 
 */
package general;

/**
 * Point representing a location in the 2D coordinate space, with coordinates (x,y).
 * The coordinates are specified in integer precision.
 * It overrides the method equals, hashCode in order to compare points accordingly to their coordinates. 
 * The method toString is also overridden in order to print the point coordinates: (x,y);
 * 
 * @author Group 6
 *
 */
public class Point {
	
	/**
	 * The x coordinate of this Point.
	 */
	private int x;
	/**
	 * The y coordinate of this Point.
	 */
	private int y;

	/**
	 * @param x coordinate along x axis
	 * @param y coordinate along y axis
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Point))
			return false;
		Point other = (Point) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	/**
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this.y = y;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 *  
	 */
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
