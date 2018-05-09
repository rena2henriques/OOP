package general;

import java.util.List;

/**
 * Provides a method (guidelines) to be implemented in order to simulate events that are handled by the PECI implementations.
 * @see PECI
 *
 */
public interface EventI {
	
	/**
	 * 
	 * Method to simulate the event.
	 * <p>
     * The user has to return a list of next events that extend from this interface.
     * If the user doesn't wish to return any event in this method it should return either an empty list or null.
	 * 
	 * @return list of next events
	 */
	List<? extends EventI> simulateEvent();

}
