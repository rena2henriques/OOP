package general;

import java.util.LinkedList;


public abstract class SimulationA implements SimulationI {
	
	//VER SE ESTES SÃO PROTECTED OU SE SAO PRIVATE E TEM SETTER E GETTERS!!!
	protected double currentTime, finalTime;
	protected int numEvents;
	protected PEC pec;
	
	public SimulationA(double finalt) {
		finalTime=finalt;
		pec = new PEC();
	}
	
	public SimulationA() {
		this(0);
	}
	
	public double getFinalTime() {
		return finalTime;
	}
	public void setFinalTime(double final_time) {
		this.finalTime = final_time;
	}
	public int getNumEvents() {
		return numEvents;
	}
	public void setNumEvents(int num_events) {
		this.numEvents = num_events;
	}
	
	public double getSimulationClock() {
		return currentTime;
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
			pec.addEvent(eventList.remove());
	}


}
