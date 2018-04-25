package general;
public abstract class Event implements EventI {

	private double time;
	
	public double getTime() {
		return time;		
	}
	
	public void setTime(double _time) {
		time=_time;
	}
	
	
	public abstract void simulateEvent();

}
