package general;

/**
 *  A container that provides operations made to a collection (it can be any kind of collection) 
 *  of event classes that implement the interface EventI
 *
 * @author Group 6
 *
 * @param <E> event that necessarily implements the interface EventI
 */
public interface PECI<E extends EventI>{
	
	/**
	 * Appends the specified element to the container.
	 * There may exist limitations on adding some events to the container, depending on the type of collection used.
	 * 
	 * @param e  event to be added to the container
	 */
	void addEvent(E e);
	
	
	/**
	 * Retrieves and removes the next event in the container. The order of removal depends on the collection used 
	 * 
	 * @return next event in the container. 
	 */
	E nextEvent(); 
	
	
	/**
	 * Removes occurrences of the specified event from the container, if it is present 
	 * 
	 * @param e event to be removed
	 */
	void removeEvent(E e);
	
	
	/**
	 * Returns the next event accordingly to the method nextEvent(), but does not remove it.
	 * 
	 * @return next event in the container
	 */
	E getEvent();
	
	
	/**
	 * Removes all of the events from the container. The result is an empty container
	 */
	void clear();
	
	
	/**
	 * Returns true if this list contains no events, and false otherwise
	 * 
	 * @return true if this list contains no events, and false otherwise
	 */
	boolean isEmpty();
}
