package specific;

import general.Event;
import general.SimulationCommands;

/**
 * This abstract class extends Event. Specifies events related to the evolution 
 * of an individual and already provides some common attributes and methods.
 * 
 * All individuals will have their events associated in order to facilitate cleaning
 * the PEC (on epidemics).
 * 
 * All events created will be added to the PEC and associated to it's 
 * individual only if their time is smaller than the individual's associated death time.
 * 
 * @see Event
 */
public abstract class IndividualEvent extends Event{
	
	
	/**
	 * Individual associated to the event.
	 */
	protected Individual individual;
	
	/**
	 * Set of commands to rule movements and event creations.
	 */
	protected SimulationCommands sNC;
	
	
	/**
	 * Constructor
	 * @see Event
	 * 
	 * @param time - time of the event
	 * @param individual - individual associated to the event
	 * @param simNumCom - set of commands to rule movements and event creations
	 */
	public IndividualEvent(double time,Individual individual, SimulationCommands simNumCom){
		super(time);
		this.individual = individual;
		this.sNC = simNumCom;
	}
	

	/**
	 * Receives the next event generated time and checks if its after the individual death
	 * 
	 * @param time - newly created event's time
	 * @return boolean, true if generated time is before death, false otherwise
	 */
	public static boolean checkDeathTime(double time, Individual ind) {
		double deathTime = 0;
		try{
			deathTime = ind.myDeath.getTime();
		} catch (NullPointerException e) { //case has no death event associated
			return true; //means that death event will not happen before simulation ends
		}				 
		if (time > deathTime) {
			return false;
		}
		return true;
	}
	
}
