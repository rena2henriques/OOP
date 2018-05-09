/**
 * 
 */
package specific;

/**
 * Point representing a location in the 2D coordinate space, with coordinates (x,y).
 * The coordinates are specified in integer precision.
 * It overrides the method equals, hashCode in order to compare points accordingly to their coordinates. 
 * The method toString is also overridden in order to print the point coordinates: (x,y) 
 *
 */
public class Point {
	
	/**
	 * The x coordinate of this Point.
	 */
	protected int x;
	/**
	 * The y coordinate of this Point.
	 */
	protected int y;

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
	/**
	 * Redefinition of the equals method. It compares the instance of the objects but also their coordinates x and y
	 * 
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
	 * Getter of the coordinate x of the point
	 * 
	 * @return the x
	 */
	public int getX() {
		return x;
	}

	/**
	 * Getter of the coordinate y of the point
	 * 
	 * @return the y
	 */
	public int getY() {
		return y;
	}

	
	/** 
	 * Redefinition of the toString Method
	 * <p> The toString method of an object Point returns a string like (x,y) 
	 * 
	 * @return string like (x,y)
	 *  
	 */
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "(" + this.x + "," + this.y + ")";
	}
	
}
