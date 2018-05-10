package specific;

/**
 * 
 * A connection between points represented by the cost of the edge connecting the points, 
 * and by one of the points of the connection.
 * 
 *
 */
class Connection {
	
	/**
	 * The cost of the connection
	 */
	protected int cost;
	
	/**
	 * The end point of the connection
	 */
	protected MapPoint point;

	/**
	 * Constructor.
	 * Creates a Connection with the specified cost and connected point.
	 * 
	 * @param cost - cost of the link
	 * @param point - point to which has a connection
	 */
	public Connection(int cost, MapPoint point) {
		this.cost = cost;
		this.point = point;
	}
	
}
