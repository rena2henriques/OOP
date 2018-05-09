package general;

/**
 *  A container that provides operations made to a collection (it can be any kind of collection) 
 *  of event classes that implement the interface EventI.
 * 
 * @param <E> event that necessarily implements the interface EventI
 */
public interface PECI<E extends EventI>{
	
	/**
	 * Appends the specified event to the container.
	 * <p>
	 * There may exist limitations on adding some events to the container, depending on the type of collection used.
	 * 
	 * @param event - event to be added to the container
	 */
	void addEvent(E event);
	
	
	/**
	 * Retrieves and removes the next event in the container. The order of removal depends on the type of collection used 
	 * 
	 * @return next event in the container. 
	 */
	E nextEvent(); 
	
	
	/**
	 * If the event is present in the container, it removes occurrences of the specified event. 
	 * If the event is not present, an error message should be issued.
	 * 
	 * @param event - event to be removed from the event container
	 */
	void removeEvent(E event);
	
	
	/**
	 * Returns the next event in the same way as in the method nextEvent(), but does not remove it from container.
	 * 
	 * @return next event in the container
	 */
	E getEvent();
	
	
	/**
	 * Removes all of the events from the container. The container will be empty after this call returns.
	 * 
	 */
	void clear();
	
	
	/**
	 * Returns true if this list contains no events, and false otherwise.
	 * 
	 * @return true if this list contains no events, and false otherwise
	 */
	boolean isEmpty();
}
