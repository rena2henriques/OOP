package specific;

/**
 * A connection between points represented by the cost of the edge connecting the points, and by one of the points of the connection.
 * 
 * @author Group 6
 *
 */
public class Connection {
	
	/**
	 * The cost of the connection
	 */
	private int cost;
	
	/**
	 * The end point of the connection
	 */
	MapPoint point;

	/**
	 * Constructor.
	 * Creates a Connection with the specified cost and connected point.
	 * 
	 * @param cost of the link
	 * @param connection - point which has a connection
	 */
	public Connection(int cost, MapPoint point) {
		this.cost = cost;
		this.point = point;
	}

	/**
	 * @return the cost
	 */
	public int getCost() {
		return cost;
	}

	/**
	 * @param cost the cost to set
	 */
	public void setCost(int cost) {
		this.cost = cost;
	}


	/**
	 * @return the point connected to
	 */
	public MapPoint getPoint() {
		return point;
	}


	/**
	 * @param point to be connected to
	 */
	public void setPoint(MapPoint point) {
		this.point = point;
	}
	
}
