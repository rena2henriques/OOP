package specific;

public class Connection {
	
	private int cost;
	
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
