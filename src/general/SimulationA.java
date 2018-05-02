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
	/**
	 * @return the finalTime
	 */
	public double getFinalTime() {
		return finalTime;
	}

	/**
	 * @param finalTime the finalTime to set
	 */
	public void setFinalTime(double finalTime) {
		this.finalTime = finalTime;
	}

	protected int numEvents;
	protected PEC pec;
	
	public SimulationA (double finalt, int capacity) {
		finalTime=finalt;
		pec = new PEC(capacity);
	}
	
	public SimulationA(double finalt) {
		finalTime=finalt;
		pec = new PEC();
	}
	
	public SimulationA() {
		this(0);
	}
	
	public int getNumEvents() {
		return numEvents;
	}
	
	public double getSimulationClock() {
		return currentTime;
	}
	
	public abstract void simulate(); // por isto ou n�o?
	
	public abstract void initialize();
		
	public void init() {
		currentTime=0; 
		numEvents=0;
		pec.clear();
	}
	
	protected void addNewEvents(List<Event> eventList) {
		if(eventList!=null) { //se der merda por os eventos a retornar null
			while(!eventList.isEmpty())
			pec.addEvent(eventList.remove(0));
		}
	}

}
