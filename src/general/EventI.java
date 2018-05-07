package general;

import java.util.List;

/**
 * Provides the guidelines to implement event classes to be handled by the PECI implementations.
 * Provides a method to simulate any type of events, which has to be implemented.
 * 
 * @author Grupo 6
 *
 */
public interface EventI {
	
	/**
	 * 
	 * Method to simulate the event
     * The user has to return a list of events that extend from this interface.
     * If the user doesn't wish to return any event in this method it should return either an empty list or null
	 * 
	 * @return list of events to be simulated after this event
	 */
	List<? extends EventI> simulateEvent();

}
