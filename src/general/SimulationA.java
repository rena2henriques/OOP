package general;

import java.util.List;



/**
 * @author mariana
 * 
 * This class provides a skeletal implementation of the Simulation interface to
 * minimize the effort required to implement this interface.
 * 
 */
public abstract class SimulationA implements SimulationI {
	
	protected double currentTime, finalTime;
	protected int numEvents;
	protected PEC pec;
	
	public SimulationA(double finalt) {
		finalTime=finalt;
		pec = new PEC();
	}
	//mudar
	public SimulationA() {	
		this(0);
	}
	/*
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
	*/
	public abstract void simulate(); // por isto ou n�o?
	
	//PROTECTED???
	
	protected void init() {
		currentTime=0; 
		numEvents=0;
		pec.clear();
	}
	
	protected void addNewEvents(List<Event> eventList) {
		if(eventList!=null) {
			while(!eventList.isEmpty())
			pec.addEvent(eventList.remove(0));
		}
	}

	//VALE A PENA TER GETRESULT QUE RETORNA OBJETO?
}
