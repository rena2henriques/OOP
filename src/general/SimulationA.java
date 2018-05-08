package general;

import java.util.List;



/**
 * @author Group 6
 * 
 * This class provides a primitive implementation of the Simulation interface to
 * minimize the effort required to implement this interface.
 * 
 * This simulation is driven by time, so it already has the attributes currentTime, finalTime, numEvents 
 * and a PEC to contain the events of class Event waiting to be simulated.
 * 
 * It provides the method init, where it resets the dynamic variables, and a method to add a list of new events to the PEC.
 * The methods simulate and initialize, depend on the specifics of each simulation, so they have to be implemented in a subclass that extends this class.
 */
public abstract class SimulationA implements SimulationI {
	
	protected SimulationCommands simComms;
	
	/**
	 * Current instant of the simulation
	 */
	protected double currentTime;
		
	/**
	 * Final instant of the simulation
	 */
	protected double finalTime;
	
	
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

	/**
	 * current number of events of the simulation
	 */
	protected int numEvents;
	
	
	/**
	 * Pending Event Container with the events to be simulated
	 */
	protected PEC pec;
	
	/**
	 * @return number of realized events
	 */
	public int getNumEvents() {
		return numEvents;
	}
	
	/**
	 * @return the simulation current time
	 */
	public double getSimulationClock() {
		return currentTime;
	}
	
	//TODO POR ISTO OU NAO? DUVIDA
	public abstract void simulate(); // por isto ou n�o????
		
	/**
	 * Resets the variables that change with the simulation of the system. 
	 * Sets the current time and the number of events to 0, and clears the PEC
	 */
	public void init() {
		currentTime=0; 
		numEvents=0;
		pec.clear();
	}
	
	/**
	 * Adds a list of new events to the PEC
	 * 
	 * @param eventList list with the events to be added to the PEC
	 */
	protected void addNewEvents(List<Event> eventList) {
		if(eventList!=null) { //se der merda por os eventos a retornar null
			for(Event e: eventList) {
				if(e.getTime() <= this.finalTime)
					pec.addEvent(e);
			}
		}
	}

}
