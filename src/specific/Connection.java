package specific;

public class Connection {
	
	private int cost;
	
	MapPoint connection;

	/**
	 * @param cost of the link
	 * @param connection - point which has a connection
	 */
	public Connection(int cost, MapPoint connection) {
		this.cost = cost;
		this.connection = connection;
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
	 * @return the connection
	 */
	public MapPoint getConnection() {
		return connection;
	}

	/**
	 * @param connection the connection to set
	 */
	public void setConnection(MapPoint connection) {
		this.connection = connection;
	}
	
}
