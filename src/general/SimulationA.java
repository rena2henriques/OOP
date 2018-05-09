package general;

import java.util.List;

/**
 *  
 * This class provides an abstract implementation of the SimulationI interface to
 * minimize the effort required to implement it.
 * <p>
 * It's a simulation driven by time, so it already has the attributes currentTime, finalTime, numEvents 
 * and a PEC to contain the events of class Event waiting to be simulated.
 * <p>
 * It provides the method init, where it resets the dynamic variables, and a method to add a list of new events to the PEC.
 * The method simulate depends on the specifics of each simulation, so it has to be implemented in a subclass that extends this class.
 * 
 * @see SimulationI
 */
public abstract class SimulationA implements SimulationI {
	
	/**
	 * Number generators (random or deterministic) that command how the simulation evolves. 
	 * <p>
	 * They may be related to the the generation of the time between the events or to any other 
	 * generation of numbers in which the events of this simulation are based. 
	 */
	protected SimulationCommands simComms;
	
	/**
	 * Current instant of the simulation.
	 */
	protected double currentTime;
		
	/**
	 * Final instant of the simulation.
	 */
	protected double finalTime;
	
	/**
	 * Current number of events of the simulation.
	 */
	protected int numEvents;
	
	/**
	 * Pending Event Container with the events to be simulated.
	 */
	protected PEC pec;
	
	
	/**
	 * @return the final time of this simulation
	 */
	public double getFinalTime() {
		return finalTime;
	}

	/**
	 * @param finalTime - the final time to set
	 */
	public void setFinalTime(double finalTime) {
		this.finalTime = finalTime;
	}
	
	/**
	 * @return number of realized events
	 */
	public int getNumEvents() {
		return numEvents;
	}
	
	/**
	 * @param nevents - number of realized events to set to this simulation
	 */
	public void setNumEvents(int nevents) {
		numEvents=nevents;
	}

	/**
	 * Resets the variables that change with the simulation of the system is performed. 
	 * <p>
	 * Sets the current time and the number of events to 0, and clears the PEC.
	 */
	public void init() {
		currentTime=0; 
		numEvents=0;
		pec.clear();
	}
	
	/**
	 * Adds a list of new events to the PEC.
	 * <p> Additionally, the events are only added to the PEC if it's time is less than the final time of the simulation.
	 * 
	 * @param eventList - list with the events to be added to the PEC
	 */
	protected void addNewEvents(List<Event> eventList) {
		if(eventList!=null) { 
			for(Event e: eventList) {
				//we only add the event to the PEC if its time is less than the final time of the simulation
				if(e.time <= this.finalTime)
					pec.addEvent(e);
			}
		}
	}

}
