package general;

public abstract class SimulationA implements SimulationI {
	
	protected double current_time, final_time;
	protected int num_events;
	
	public double getFinal_time() {
		return final_time;
	}
	public void setFinal_time(double final_time) {
		this.final_time = final_time;
	}
	public int getNum_events() {
		return num_events;
	}
	public void setNum_events(int num_events) {
		this.num_events = num_events;
	}
	
	public abstract void simulate();
	
	

}
