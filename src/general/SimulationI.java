package general;

/**
 * A simulator that provides methods to simulate events, with methods to simulate and initialize the simulation.
 * 
 */

public interface SimulationI {
	
	/**
	 * Method to run the simulation.
	 * <p>
	 * It should contain all the necessary operations to simulate the system, such as initialization, simulation loops and printing of results. 
	 */
	void simulate();
	
	
	/**
	 * Provides the initialization needed to start the simulation and reseting of variables that change when the simulation is done.
	 * <p>
	 * It should be called before or in the beginning of the method simulate() so that the same simulation object can be simulated multiple times.
	 */
	void init();
}
