package specific;

/**
 * A connection between points represented by the cost of the edge connecting the points, and by one of the points of the connection.
 * 
 * @author Group 6
 *
 */
class Connection {
	
	/**
	 * The cost of the connection
	 */
	int cost;
	
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
	Connection(int cost, MapPoint point) {
		this.cost = cost;
		this.point = point;
	}
	
}
