package general;

public abstract class SimulationA implements SimulationI {
	
	protected double currentTime, finalTime;
	protected int numEvents;
	
	protected PEC pec;
	
	public double getFinal_time() {
		return finalTime;
	}
	public void setFinal_time(double final_time) {
		this.finalTime = final_time;
	}
	public int getNum_events() {
		return numevents;
	}
	public void setNum_events(int num_events) {
		this.numEvents = num_events;
	}
	
	public double getSimulationClock() {
		return currentRime;
	}
	
	/*public SimulationA(double finalt) {
		final_time=finalt;
	}*/
	
	public abstract void simulate();
	
	public void init() {
		currentTime=0;
		numEvents=0;
		pec= new PEC();
	}


}
