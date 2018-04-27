package general;

import java.util.LinkedList;


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
		return numEvents;
	}
	public void setNum_events(int num_events) {
		this.numEvents = num_events;
	}
	
	public double getSimulationClock() {
		return currentTime;
	}
	
	public SimulationA(double finalt) {
		finalTime=finalt;
	}
	
	//public abstract void simulate();
	
	//PROTECTED???
	
	protected void init() {
		currentTime=0;
		numEvents=0;
		pec= new PEC();
	}
	
	protected void addNewEvents(LinkedList<Event> eventList) {
		while(!eventList.isEmpty())
			pec.add(eventList.remove());
	}


}
